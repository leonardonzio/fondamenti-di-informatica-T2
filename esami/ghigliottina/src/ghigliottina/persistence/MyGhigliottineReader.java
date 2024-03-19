package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hamcrest.core.StringEndsWith;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;

public class MyGhigliottineReader implements GhigliottineReader {

	private List<Ghigliottina> ghigliottine;	
	
	@Override
	public List<Ghigliottina> getGhigliottine() {
		return this.ghigliottine;
	}

	@Override
	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("reader Nullo");
		
		Ghigliottina gh;
		ghigliottine = new ArrayList<Ghigliottina>();
		while ((gh = readOne(reader)) != null) {
			ghigliottine.add(gh);
		}
		
		return ghigliottine;
	}
	
	
	private Ghigliottina readOne(BufferedReader reader) throws IOException, BadFileFormatException{
		
		String line = reader.readLine();
		if (line == null)
			return null;
		
		if (line.startsWith("----"))
			throw new BadFileFormatException("Ghigliottina vuota");
		
		var terne = new ArrayList<Terna>();
		while (line != null && !line.startsWith("Risposta esatta")) {
			
			StringTokenizer tokens = new StringTokenizer(line, "/=");
			
			if (tokens.countTokens() != 3)
				throw new BadFileFormatException("non sono 3 tokens");
			
			String word1	 		= tokens.nextToken().trim();
			String word2 			= tokens.nextToken().trim();
			String qualeDelleDue 	= tokens.nextToken().trim();
			
			try {
				Terna terna = new Terna(word1, word2, Esatta.valueOf(qualeDelleDue));
				terne.add(terna);
				line = reader.readLine().trim();
			} catch (IllegalArgumentException e) {
				throw new BadFileFormatException("mal formatatta la terna");
			}
		}
		if (!line.startsWith("Risposta esatta"))
			throw new BadFileFormatException("deve avere la riposta esatta");
		
		String[] tokensWithSplit = line.split("=");
		if (tokensWithSplit.length != 2)
			throw new BadFileFormatException("non sono 2");
		
		String esatta = tokensWithSplit[1].trim();
		// riga con ------
		line = reader.readLine();
		if (line == null || !line.startsWith("-----"))
			throw new BadFileFormatException("non inizia per ----");
		
		return new Ghigliottina(terne, esatta);
	}

	
	
	
	
}
