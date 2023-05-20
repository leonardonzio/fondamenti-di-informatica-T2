package bussy.model;

import java.util.Map;
import java.util.Optional;
import java.util.PrimitiveIterator.OfDouble;
import java.util.zip.ZipEntry;

import javax.imageio.event.IIOReadUpdateListener;

import org.junit.runners.model.FrameworkField;

public class LineaCircolare extends Linea{
	
	public LineaCircolare(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if(!isCapolineaCircolare())
			throw new IllegalArgumentException("Non è circolare");
	}

	@Override
	public Optional<Percorso> getPercorso(String start, String end) {
		try {
			int orarioStart = getOrarioPassaggioAllaFermata(start);
			int orarioEnd = getOrarioPassaggioAllaFermata(end);
			int orarioCapFinale = getCapolineaFinale().getKey();
			
			int diff = orarioEnd - orarioStart;
			
			if (isCapolinea(start) && isCapolinea(end))
				return Optional.of(new Percorso(start, end, this, orarioCapFinale));
			
			if (isCapolinea(start))
				return Optional.of(new Percorso(start, end, this, orarioEnd));

			if (isCapolinea(end)) {
				int durata = orarioCapFinale - orarioStart;
				return Optional.of(new Percorso(start, end, this, durata));
			}

			if (diff < 0) {
				int durata = orarioCapFinale + diff;
				return Optional.of(new Percorso(start, end, this, durata));
			}
			return Optional.of(new Percorso(start, end, this, diff));
			
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
	
	private boolean isCapolinea(String nomeFermata) {
		return getCapolineaFinale().getValue().getNome().equals(nomeFermata);	
	}

}
