package favoliere.model;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import favoliere.model.test.MySintetizzatoreBasicTest;
import javafx.collections.transformation.SortedList;

public class MySintetizzatore implements Sintetizzatore {

	
	private List<Azione> 		azioni;
	private List<Conclusione> 	conclusioni;
	private List<Personaggio> 	personaggi;
	private List<Scenario> 		scenari;
	private final int NUM_PERS_POS = 2;
	private final int NUM_PERS_NEG = 1;

	public MySintetizzatore(List<Personaggio> personaggi, List<Scenario> scenari, List<Azione> azioni,
			List<Conclusione> conclusioni) {
		
		this.personaggi = personaggi;
		this.scenari = scenari;
		this.azioni = azioni;
		this.conclusioni = conclusioni;
	}

	@Override
	public Favola generaFavola(FasciaEta eta, Impressionabilita livelloImpressionabilita) throws NoSuchTaleException {

		var complexMax = eta.getGradoComplessita();
		var durezzaMax = livelloImpressionabilita.getGradoDurezza();
		
		var esordio = new Esordio(
				sorteggia(personaggi, NUM_PERS_POS, Tipologia.POSITIVO), 
				sorteggia(personaggi, NUM_PERS_NEG, Tipologia.NEGATIVO)
			);
		
		if (esordio.getPersonaggiBuoni().size() 		< NUM_PERS_POS ||
				esordio.getPersonaggiCattivi().size() 	< NUM_PERS_NEG) {
			
			throw new NoSuchTaleException("personaggi errati");
		}
		
		var optScenario  	= sorteggia(scenari, complexMax);
		if (optScenario.isEmpty())
			throw new NoSuchTaleException("Non esistono scenari con il grado di complessità richiesto");
		
		var optAzione		= sorteggia(azioni,  durezzaMax);
		if (optAzione.isEmpty())
			throw new NoSuchTaleException("Non esistono azioni con il grado di durezza richiesto");
		
		var conclusione 	= sorteggia(conclusioni);

		return new Favola(esordio, 
				optScenario.get(), 
				optAzione.get(), 
				conclusione);

	}
	
	private Set<Personaggio> sorteggia (List<Personaggio> lista, int n, Tipologia tipo) throws NoSuchTaleException {

		var personaggiTipoCorretto = lista.stream()
				.filter(p -> p.getTipologia().equals(tipo))
				.toList();
		
		if (personaggiTipoCorretto .size() < n)
			throw new NoSuchTaleException("Non ci sono sufficienti personaggi del tipo richiesto");

		var personaggiCasuali = new HashSet<Personaggio>();
		 do {
			
			var randomIndex = new Random().nextInt(personaggiTipoCorretto.size());
			personaggiCasuali.add(personaggiTipoCorretto.get(randomIndex));
		
		 } while (personaggiCasuali.size() < n);
		
		return personaggiCasuali;
	}
	
	private <T> T sorteggia (List<T> lista) {
		
		int random = new Random().nextInt(lista.size());
		return lista.get(random);
	}
	
	
	private	<T extends ConIndice> Optional<T> sorteggia (List<T> lista, int upperBound) {
		
		var newList = lista.stream()
				.filter(t -> t.getIndice() <= upperBound)
				.toList();
		
		if (newList.size() == 0)
			return Optional.empty();
		
		int random = new Random().nextInt(newList.size());
		return Optional.of(lista.get(random));
	}

	@Override
	public List<Personaggio> getPersonaggi() {
		return personaggi;
	}
	
	@Override
	public List<Scenario> getScenari() {
		return scenari;
	}
	
	@Override
	public List<Azione> getAzioni() {
		return azioni;
	}
	
	@Override
	public List<Conclusione> getConclusioni() {
		return conclusioni;
	}
	
	
}
