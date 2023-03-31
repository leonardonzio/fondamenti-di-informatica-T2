package ticketsostaevoluto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import ticketsosta.Tariffa;

public class ParcometroEvoluto {

	private Tariffa[] tariffe;
	
	public ParcometroEvoluto(Tariffa[] tariffa) {
		this.tariffe = new Tariffa[7];
		this.tariffe = Arrays.copyOf(tariffa, 7);
	}
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a) {
		
		Duration tempo;
		if(a.isBefore(da) || LocalTime.of(0, 0).equals(a)) {
			tempo = Duration.between(da, LocalTime.of(23,59)).plusMinutes(1);
		}
		else {
			tempo = Duration.between(da, a);
		}
		
		// il costo è il costo orario per per il tempo in ore;
		return costoOrario * tempo.toMinutes() / 60.0;
	}
	
	
	
	private double calcolaCostoSuPiuGiorni(LocalDateTime inizio, LocalDateTime fine) {

		LocalDate day = inizio.toLocalDate();
		LocalDate dayEnd = fine.toLocalDate();
		
		double costoTot = 0;
		while(!dayEnd.isBefore(day)) {
			LocalTime dayInizio;
			LocalTime dayFine;
			
			Tariffa tariffa = this.tariffe[day.getDayOfWeek().ordinal()];
			
			if (inizio.toLocalDate().equals(day)) {
				dayInizio = inizio.toLocalTime().plusMinutes(tariffa.getMinutiFranchigia());
			} else {
				dayInizio = LocalTime.of(0, 0);
			}
			
			if (fine.toLocalDate().equals(day)) {
				dayFine = fine.toLocalTime();
			} else {
				dayFine = LocalTime.of(0, 0);
			}
			
			costoTot += calcolaCosto(tariffa.getTariffaOraria(), dayInizio, dayFine);
			day = day.plusDays(1);
		}
		
		return costoTot;
		
	}
	
	public TicketEvoluto emettiTicket(LocalDateTime inizio, LocalDateTime fine) {

		Tariffa tariffaPrimoGiorno = this.tariffe[inizio.getDayOfWeek().ordinal()];
		LocalDateTime inizioEffettivo = inizio.plusMinutes(tariffaPrimoGiorno.getMinutiFranchigia());
		
		double costo;
		Duration durataSosta = Duration.between(inizioEffettivo, fine);
		if(durataSosta.toMinutes() < tariffaPrimoGiorno.getDurataMinima()) {
			costo = tariffaPrimoGiorno.getDurataMinima() * tariffaPrimoGiorno.getTariffaOraria() / 60.0;
		}
		else {
			costo = calcolaCostoSuPiuGiorni(inizio, fine);
		}
		
		return new TicketEvoluto(inizio, fine, costo);
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Parcometro con le seguenti tariffe:").append(System.lineSeparator());
		for (Tariffa t : this.tariffe) {
			sb.append(t).append(System.lineSeparator());	
		}
		
		return sb.toString();	
	}
	
	
}
