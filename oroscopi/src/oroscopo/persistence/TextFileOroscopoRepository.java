package oroscopo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.platform.commons.util.ReflectionUtils;

import oroscopo.model.OroscopoMensile;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class TextFileOroscopoRepository implements OroscopoRepository {

	private Map<String, List<Previsione>> data;
	
	
	public TextFileOroscopoRepository(Reader baseReader) throws IOException, BadFileFormatException {
		
		if (baseReader == null)
			throw new IllegalArgumentException("reader nullo");
		
		var rdr = new BufferedReader(baseReader);
		data = new HashMap<>();
		
		List<Previsione> listPrevisioni;
		String settore;
		while ((settore = rdr.readLine()) != null){
			
			if (settore.contains(" ") || settore.contains("\t"))
				throw new BadFileFormatException("linea settore errata");
			
			listPrevisioni = caricaPrevisioni(rdr);
			if (listPrevisioni.isEmpty())
				throw new BadFileFormatException("nessuna previsione per questo settore");
			
			data.put(settore.trim().toLowerCase(), listPrevisioni);
		}
	}
	
	
	@Override
	public Set<String> getSettori() {
		return data.keySet();
	}

	@Override
	public List<Previsione> getPrevisioni(String settore) {
		return data.get(settore.toUpperCase());
	}

	
	private List<Previsione> caricaPrevisioni (BufferedReader reader) throws BadFileFormatException, IOException{
		
		List<Previsione> listPrev = new ArrayList<>();
		String line;
		while (!(line = reader.readLine()).equals("FINE")) {
			
			if (line.isEmpty()) continue;
			
			var tokens = new StringTokenizer(line, "\t\r\n");
			var numTkns = tokens.countTokens();
			
			if (numTkns != 3 && numTkns != 2)
				throw new BadFileFormatException("non sono 2 o 3 tokens");
			
			String testoPrev 				= tokens.nextToken().trim();
			String gradoFortunaAsString 	= tokens.nextToken().trim();
			
			int gradoFortuna = 0;
			try {
				
				gradoFortuna = Integer.parseInt(gradoFortunaAsString);
				if (gradoFortuna < 0)
					throw new BadFileFormatException("grado fortuna deve essere >= 0");
				
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("grado fortuna non formattato correttamente");
			}
			
			if (numTkns == 2) {
				listPrev.add(new Previsione(testoPrev, gradoFortuna));
				continue;
			}

			String segniAsString = tokens.nextToken().trim();
			listPrev.add(new Previsione(testoPrev, gradoFortuna, readSegni(segniAsString)));
			
		}
		
		return listPrev;
	}
	
	
	private Set<SegnoZodiacale> readSegni(String segniAsString) throws BadFileFormatException{
		if (segniAsString.contains(" "))
			throw new BadFileFormatException("i segni contengono spazi");

		var segni 	= new HashSet<SegnoZodiacale>();
		var tokens 	= new StringTokenizer(segniAsString, ",");
		
		boolean aggiunto;
		while (tokens.hasMoreTokens()) {
			
			String nomeSegno = tokens.nextToken();
			aggiunto = false;
			for (SegnoZodiacale s : SegnoZodiacale.values()) {
				
				if (nomeSegno.toUpperCase().equals(s.name())) {
					segni.add(s);
					aggiunto = true;
				}
			}
			if (aggiunto == false)
				throw new BadFileFormatException("segno non parsabile");
			
		}
		
		return segni;
	}
	
	
	
	
}
