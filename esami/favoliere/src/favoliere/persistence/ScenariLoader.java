package favoliere.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import favoliere.model.Azione;
import favoliere.model.Scenario;

public class ScenariLoader implements SectionLoader<Scenario>{

	private List<Scenario> entities;
	
	@Override
	public List getItems() {
		return entities;
	}

	@Override
	public void loadAllItems(Reader baseReader) throws IOException, BadFileFormatException {
		entities = new ArrayList<>();
		
		var buffRdr = new BufferedReader(baseReader);
		String riga;
		
		while ((riga = buffRdr.readLine()) != null) {
			
			if (riga.trim().isEmpty())
				throw new BadFileFormatException("riga vuota in azioni");

			String[] tokens = riga.split("#");
			
			if (tokens.length != 2)
				throw new BadFileFormatException("non 2 tokens");
			
			String descrizione = tokens[0].trim();
			if (descrizione.isBlank())
				throw new BadFileFormatException("decrizione vuyota");
				
			String complessitàAsString = tokens[1].trim();
			
			int complessità = 0;
			try {
				complessità = Integer.parseInt(complessitàAsString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("parsato male durezza");
			}
			
			entities.add(new Scenario(descrizione, complessità));
		}
		
		
		
	}

}
