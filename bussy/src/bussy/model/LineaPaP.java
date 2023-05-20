package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaPaP extends Linea{

	public LineaPaP(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if(isCapolineaCircolare())
			throw new IllegalArgumentException("è circolare ma dovrebbe essere PaP");
		
	}

	@Override
	public Optional<Percorso> getPercorso(String start, String end) {
		try {
			int orarioStart = getOrarioPassaggioAllaFermata(start);
			int orarioEnd= getOrarioPassaggioAllaFermata(end);
			int diff = orarioEnd - orarioStart;
	
			if (orarioStart > orarioEnd)
				return Optional.empty();
			
			return Optional.of(new Percorso(start, end, this, diff));
		
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	
	
}
