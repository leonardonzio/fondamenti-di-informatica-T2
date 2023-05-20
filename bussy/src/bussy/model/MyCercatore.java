package bussy.model;

import java.lang.invoke.VarHandle;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.crypto.interfaces.DHPrivateKey;
import javax.tools.ForwardingFileObject;


public class MyCercatore implements Cercatore{
	
	private Map<String, Linea> mappaLinee;
	
	public MyCercatore(Map<String, Linea> mappaLinee ) {
		if(mappaLinee == null || mappaLinee.isEmpty())
			throw new IllegalArgumentException("linee null");
		
		this.mappaLinee = mappaLinee;
	}
	
	public Map<String, Linea> getMappaLinee() {
		return this.mappaLinee;
	}

	@Override
	public SortedSet<Percorso> cercaPercorsi(String start, String end, OptionalInt durataMax) {	
		if(start == null || end == null)
			throw new IllegalArgumentException("fermata iniziale o finale null");

		if(start.isEmpty() || end.isEmpty())
			throw new IllegalArgumentException("fermata iniziale o finale sono vuote");
		
		var durataVera = durataMax.isPresent() ? durataMax.getAsInt() : Integer.MAX_VALUE;
		
		return mappaLinee.values()
			.stream()
			.filter(linea -> linea.getPercorso(start, end).isPresent() 
					&& linea.getPercorso(start, end).get().getDurata() > 0)
			.map(linea -> linea.getPercorso(start, end))
			.flatMap(Optional::stream)
			.filter(percorso -> percorso.getDurata() <= durataVera)
			.collect(Collectors.toCollection(TreeSet::new));
		
	}
}
