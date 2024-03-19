package mediaesami.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.http.HttpConnectTimeoutException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.print.attribute.standard.DateTimeAtCompleted;

import mediaesami.model.AttivitaFormativa;
import mediaesami.model.Carriera;
import mediaesami.model.Esame;
import mediaesami.model.Voto;


public class MyCarrieraReader implements CarrieraReader {

	/*
	 * 	27991	ANALISI MATEMATICA T-1				9,0		12/1/2020	RT
		27991	ANALISI MATEMATICA T-1				9,0		10/2/2020	22
		28004	FONDAMENTI DI INFORMATICA T-1		12,0	13/2/2020	24
		29228	GEOMETRIA E ALGEBRA T				6,0		18/1/2020	26
		26337	LINGUA INGLESE B-2					6,0
		27993	ANALISI MATEMATICA T-2				6,0		10/6/2020	RE
		27993	ANALISI MATEMATICA T-2				6,0		02/7/2020	RT
		28006	FONDAMENTI DI INFORMATICA T-2		12,0
		28011	RETI LOGICHE T						6,0
		...
	* */

	
	private final DateTimeFormatter DATE_FORMATTER 	= DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(Locale.ITALY);
	private final NumberFormat NUMBER_FORMATTER 	= NumberFormat.getInstance(Locale.ITALY);
	
	@Override
	public Carriera leggiCarriera(Reader rdr) throws IOException, BadFileFormatException {		
		
		if (rdr == null)
			throw new IllegalArgumentException("non mi hai dato il reader");
		
		BufferedReader buffReader = new BufferedReader(rdr);
		String line;
		Carriera carriera = new Carriera();
		while((line = buffReader.readLine()) != null) {
			
			if (line.trim().isEmpty())
				continue;
			
			StringTokenizer tokens = new StringTokenizer(line, "\t\r\n");
			var numTokens = tokens.countTokens();
			if (numTokens != 3 && numTokens != 5 )
				throw new BadFileFormatException("devono essere 3 o 5 token");
			
			String idAsString 	= tokens.nextToken().trim();
			String nomeAF 		= tokens.nextToken().trim();
			String cfuAsString 	= tokens.nextToken().trim();

			try {
				Long id = Long.parseLong(idAsString);
				
				ParsePosition pos = new ParsePosition(0);
				Number cfuNum = NUMBER_FORMATTER.parse(cfuAsString.replace('.',' '), pos);
				if (cfuNum == null || pos.getIndex() < cfuAsString.length() || cfuNum.doubleValue() < 1.0)
					throw new BadFileFormatException("Valore crediti mal formattato o illegale: " + line);
				
				double cfu = cfuNum.doubleValue();
				
				if (numTokens == 3)
					continue;
				
				String dateAsString = tokens.nextToken().trim();
				String votoAsString = tokens.nextToken().trim();
				
				LocalDate date = LocalDate.parse(dateAsString, DATE_FORMATTER);
				Voto voto = Voto.of(votoAsString);
			
				carriera.inserisci(new Esame(new AttivitaFormativa(id, nomeAF, cfu), date, voto));

			} catch (Exception e) {
				throw new BadFileFormatException("file non formattato in modo corretto");
			}
		}
		
		return carriera;	
	}
	
}