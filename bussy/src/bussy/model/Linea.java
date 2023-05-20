package bussy.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runners.model.FrameworkField;

import java.util.*;

public abstract class Linea {
	public abstract Optional<Percorso> getPercorso(String nomeFermataDa, String nomeFermataA);

	public Linea (String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("nome id inesistente o vuoto");
		
		if (orariPassaggioAlleFermate == null || orariPassaggioAlleFermate.isEmpty())
			throw new IllegalArgumentException("orario inesistente o vuoto");

		this.id = id;
		this.orariPassaggioAlleFermate = orariPassaggioAlleFermate;
	}

	public String getId() {
		return id;
	}

	public Map<Integer, Fermata> getOrariPassaggioAlleFermate() {
		return this.orariPassaggioAlleFermate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Linea))
			return false;
		Linea other = (Linea) obj;
		return 	Objects.equals(id, other.id);
	}
	
	public Entry<Integer, Fermata> getCapolineaIniziale() {
		Optional<Entry<Integer, Fermata>> entryIniziale = Optional.empty();
		var setEntry = orariPassaggioAlleFermate.entrySet();
		
		for (Entry<Integer, Fermata> entry : setEntry) {
			if(entryIniziale.isEmpty() || entry.getKey() < entryIniziale.get().getKey()) {
				entryIniziale = Optional.of(entry);
			}
		}
		
		if(entryIniziale.isEmpty()) {
			throw new IllegalArgumentException("Non esiste il capolinea iniziale");
		}
		
		return entryIniziale.get();
	}
	
	public Entry<Integer, Fermata> getCapolineaFinale() {
		
		Optional<Entry<Integer, Fermata>> entryFinale = Optional.empty();
		var setEntry = orariPassaggioAlleFermate.entrySet();
		
		for (Entry<Integer, Fermata> entry : setEntry) {
			if(entryFinale.isEmpty() || entry.getKey() > entryFinale.get().getKey()) {
				entryFinale = Optional.of(entry);
			}
		}
		
		if(entryFinale.isEmpty()) {
			throw new IllegalArgumentException("Non esiste il capolinea finale");
		}
		
		return entryFinale.get();
	}
	
	public int getOrarioPassaggioAllaFermata(String nomeFermata) {
		var orari = orariPassaggioAlleFermate.entrySet()
				.stream()
				.filter(entry -> entry.getValue().getNome().equals(nomeFermata))
				.map(entry -> entry.getKey())
				.toList();
		
		if(orari.isEmpty())
			throw new IllegalArgumentException("fermata inesistente");			
		
		
		return orari.get(0);

		/* vecchia soluzione
		Optional<Entry<Integer, Fermata>> opEntry = Optional.empty();
		
		for (Entry<Integer, Fermata> entry : orariPassaggioAlleFermate.entrySet()) {
			if(entry.getValue().getNome().equals(nomeFermata)) {
				opEntry.of(entry);
			}
		}
		if(opEntry.isEmpty()) {
			throw new IllegalArgumentException("fermata inesistente");			
		}
		
		return opEntry.get().getKey();
		*/
	}
	
	
	public boolean isCapolineaIniziale(String nomeFermata){
		return getCapolineaIniziale().getValue().getNome().equals(nomeFermata);
	}

	public boolean isCapolineaFinale(String nomeFermata){
		return getCapolineaFinale().getValue().getNome().equals(nomeFermata);
	}
	
	public boolean isCapolineaCircolare(){
		return 	getCapolineaFinale().getValue().getId().equals(getCapolineaIniziale().getValue().getId());
	}
	
	private String id;
	private Map<Integer, Fermata> orariPassaggioAlleFermate;
}


