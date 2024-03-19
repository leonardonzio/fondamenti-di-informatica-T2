package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import ghigliottina.model.Ghigliottina;

public interface GhigliottineReader {
	
	// legge da un (buffered)reader una serie di Ghigliottina e le restituisce sotto forma di lista
	public List<Ghigliottina> getGhigliottine();
	
	// restituisce semplicemente la lista già letta (o lancia NullPointerException se la lettura non è ancora avvenuta)
	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException ;
}
