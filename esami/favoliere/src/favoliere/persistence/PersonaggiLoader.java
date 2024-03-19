package favoliere.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import favoliere.model.Personaggio;
import favoliere.model.Scenario;
import favoliere.model.Tipologia;

public class PersonaggiLoader implements SectionLoader<Personaggio>{

	private List<Personaggio> personaggi;
	
	@Override
	public List getItems() {
		return personaggi;
	}

	@Override
	public void loadAllItems(Reader baseReader) throws IOException, BadFileFormatException {
		personaggi = new ArrayList<>();
		
		var buffRdr = new BufferedReader(baseReader);
		String riga;
		
		while ((riga = buffRdr.readLine()) != null) {
			
			if (riga.trim().isEmpty())
				throw new BadFileFormatException("riga vuota in azioni");

			String[] tokens = riga.split(":");
			if (tokens.length != 3)
				throw new BadFileFormatException("non 3 tokens");
			
			String tipoAsString = tokens[0];
			Tipologia tipo;
			try {
				tipo = Tipologia.valueOf(tipoAsString);
				
			} catch (IllegalArgumentException e) {
				throw new BadFileFormatException("no tipo");
			}
			
			String nome = tokens[1].trim();
			if (nome.isBlank())
				throw new BadFileFormatException("nome vuoto");
			
			String descrizione = tokens[2].trim();
			if (descrizione.isBlank())
				throw new BadFileFormatException("descrizione vuoto");
			
			personaggi.add(
					new Personaggio(
							nome,
							tipo, 
							descrizione
						)
					);
		}
		
	}
	
	
	
	
}
