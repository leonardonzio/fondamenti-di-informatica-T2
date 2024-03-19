package bancaore.model;

import java.awt.ContainerOrderFocusTraversalPolicy;
import java.security.Identity;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.JUnit4TestCaseFacade;


public class BancaOre {

	private Cedolino cedolino;
	private Duration saldoOrePrecedente, totalePermessiAOre, totalePermessi;
	private List<VoceBancaOre> voci;
	
	public BancaOre(Cedolino cedolino) {
		if (cedolino==null) throw new IllegalArgumentException("cedolino nullo");
		this.cedolino = cedolino;
		this.saldoOrePrecedente = cedolino.getSaldoOrePrecedente();
		this.totalePermessi=Duration.ofHours(0);
		this.totalePermessiAOre=Duration.ofHours(0);
		voci = popolaLista();
	}
	
	private List<VoceBancaOre> popolaLista() {
		List<VoceBancaOre> listaVoci = new ArrayList<>();
		var primoGiornoDelMese  = cedolino.getData().withDayOfMonth(1);
		var ultimoGiornoDelMese = cedolino.getData().with(TemporalAdjusters.lastDayOfMonth());
		for (LocalDate d=primoGiornoDelMese; !d.isAfter(ultimoGiornoDelMese); d=d.plusDays(1)) {
			listaVoci.add(sintetizzaVoceBancaOre(d));
		}
		return listaVoci;
	}
	

	private VoceBancaOre sintetizzaVoceBancaOre(LocalDate d) {
		// *** DA FARE ***
		/*  Questo metodo deve: 
			- recuperare tutte le voci presenti nel cedolino per la data richiesta (NB: potrebbero non essercene, 
			  dato che non tutte le date del mese sono in generale presenti nel cedolino) 
			- calcolare le ore di lavoro globalmente effettuate nella giornata richiesta, escludendo pause 
			  pranzo e riposi di qualsiasi tipo 
			- recuperare le ore previste per quel giorno della settimana 
			- calcolare la differenza (positiva o negativa) fra le ore previste e quelle effettivamente svolte, 
			  aggiornando poi, di conseguenza, il saldo della banca ore 
			- nel caso di riposi compensativi (giornalieri o ad ore), limitarsi ad aggiornare i relativi totali,
			  in quanto il calcolo delle ore è già fatto sopra
		 * 
		 * */
		
		var listVociCedolinoData = cedolino.getVoci().stream()
				.filter(v -> v.getData().equals(d))
				.toList();
		

		Duration oreSvolte = Duration.ofHours(0);
		Duration orePreviste = cedolino.getSettimanaLavorativa().getOreLavorative(d.getDayOfWeek());
		
		Duration diff = Duration.ofHours(0).minus(orePreviste);
		saldoOrePrecedente = saldoOrePrecedente.plus(diff);
		
		for (VoceCedolino v : listVociCedolinoData) {	
			
			Duration durata = Duration.between(v.getOraEntrata(), v.getOraUscita());
			if (v.getCausale().isEmpty()) {
				
				oreSvolte = oreSvolte.plus(durata);
				saldoOrePrecedente = saldoOrePrecedente.plus(durata);
			}
			
			else switch (v.getCausale().get() ) {
				case RIPOSO_COMPENSATIVO:
					totalePermessi = totalePermessi.plus(durata);
					break;
	
				case RIPOSO_COMPENSATIVO_A_ORE:
					totalePermessiAOre = totalePermessiAOre.plus(durata);
					break;
					
				case PAUSA_PRANZO:
					continue;
			}	
		}

		return new VoceBancaOre(d, orePreviste, oreSvolte, saldoOrePrecedente);
	}

	public List<VoceCedolino> getDettagli(LocalDate d) {
		var result = new ArrayList<VoceCedolino>();
		for (VoceCedolino v : cedolino.getVoci())
			if(v.getData().equals(d)) result.add(v);
		return result;
	}
	
	public Duration getSaldoOrePrecedente() {
		return saldoOrePrecedente;
	}

	public Cedolino getCedolino() {
		return cedolino;
	}

	public List<VoceBancaOre> getVoci() {
		return Collections.unmodifiableList(voci);
	}

	@Override
	public String toString() {
		return "BancaOre [cedolino=" + cedolino + ", saldoOre=" + saldoOrePrecedente + ", voci=" + voci + "]";
	}

	public Duration getTotalePermessiAOre() {
		return totalePermessiAOre;
	}

	public Duration getTotalePermessi() {
		return totalePermessi;
	}
		
}


