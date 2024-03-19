package cityparking.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.hamcrest.core.StringEndsWith;
import org.junit.runners.model.FrameworkField;

import cityparking.model.Tariffa;


public class MyTariffaReader implements TariffaReader {
	
	private NumberFormat numFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
	
	
	// *** DA REALIZZARE ***
	@Override
	public Tariffa leggiTariffa(Reader reader) throws IOException, BadFileFormatException {
		
		if (reader == null)
			throw new IllegalArgumentException("reader nullo");
		
		var rdr = new BufferedReader(reader);
		
		Duration durata = leggiDurata(rdr, "Durata unità");
		double costoIniziale 			= leggiCosto(rdr, "Costo iniziale");
		double costoUnitaSuccessive  	= leggiCosto(rdr, "Unità successive");
		double capGiornaliero 			= leggiCosto(rdr, "Cap giornaliero");
		double oltre 					= leggiCosto(rdr, "12h successive");

		return new Tariffa (durata,
				costoIniziale,
				costoUnitaSuccessive,
				capGiornaliero,
				oltre);	
		
	}
	
	
	private double leggiCosto (BufferedReader rdr, String descrizione) throws IOException, BadFileFormatException{
		
		String line 	= rdr.readLine().trim();
		String[] tokens = line.split("=");
		
		if (tokens.length != 2)
			throw new BadFileFormatException("non sono 2 tokens");
		if (!tokens[0].trim().equalsIgnoreCase(descrizione))
			throw new BadFileFormatException("il file non inizia per durata unità");

		String costoAsString = tokens[1].trim();
		Number costo;
		try {
			 return numFormatter.parse(costoAsString).doubleValue();
		} catch (ParseException e) {
			throw new BadFileFormatException("costo formattato male");
		}
	}
	
	private Duration leggiDurata (BufferedReader rdr, String descrizione) throws IOException, BadFileFormatException{
		
		String line 	= rdr.readLine().trim();
		String[] tokens = line.split("=");
		
		if (tokens.length != 2)
			throw new BadFileFormatException("non sono 2 tokens");
		if (!tokens[0].trim().equalsIgnoreCase(descrizione))
			throw new BadFileFormatException("il file non inizia per durata unità");
		
		String durataAsString = tokens[1].trim();
		Duration durata;
		try {
			durata = Duration.parse("PT" + durataAsString.toUpperCase());
		} catch (DateTimeParseException e) {
			throw new BadFileFormatException("formato durata sbagliato");
		}
		if (durata.isNegative() || durata.isZero())
			throw new BadFileFormatException("durata formattata male");
		
		return durata;
	}

	
	
}
