package ticketsosta;

import java.time.Duration;
import java.time.LocalTime;

public class Parcometro {
	
	private Tariffa tariffa;
	
	public Parcometro(Tariffa tariffa) {
		this.tariffa = tariffa;
	}
	
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a) {
		
		Duration tempo;
		if(a.isBefore(da) || LocalTime.of(0, 0).equals(a)) {
			tempo = Duration.between(da, LocalTime.of(23,59).plusMinutes(1));
		}
		else {
			tempo = Duration.between(da, a);
		}
		
		return costoOrario * tempo.toMinutes()/60.0;
	}
	
	public Ticket emettiTicket(LocalTime inizio, LocalTime fine) {
		
		LocalTime inizioEffettivo = inizio.plusMinutes(tariffa.getMinutiFranchigia());
		
		double costo;
		
		if(Duration.between(inizioEffettivo, fine).toMinutes() < tariffa.getDurataMinima()) {
			costo = tariffa.getTariffaOraria() * tariffa.getDurataMinima();
		}
		else {
			costo = calcolaCosto(tariffa.getTariffaOraria(), inizio, fine);
		}
		
		return new Ticket(inizio, fine, costo);	
	}
	
}
