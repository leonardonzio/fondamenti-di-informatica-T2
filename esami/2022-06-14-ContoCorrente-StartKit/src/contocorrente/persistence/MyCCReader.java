package contocorrente.persistence;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.StringConcatFactory;
import java.net.http.HttpConnectTimeoutException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.StringTokenizer;

import contocorrente.model.Movimento;
import contocorrente.model.ContoCorrente;
import contocorrente.model.Tipologia;


public class MyCCReader implements CCReader {

	/*  CC N.123456789
	 * 	31/01/22 31/01/22	10.317,81 	SALDO INIZIALE A VOSTRO CREDITO
		02/02/22 02/02/22	- 2,90 		IMP.BOLLO CC
		07/02/22 07/02/22 	- 61,59 	SPESE GESTIONE
		...
	 * */

	@Override
	public ContoCorrente readCC (Reader rdr) throws IOException, BadFileFormatException{
		if (rdr == null)
			throw new BadFileFormatException("reader inesistente");
		
		BufferedReader reader 	= new BufferedReader(rdr);
		String primaRiga 		= reader.readLine();
		StringTokenizer tokens 	= new StringTokenizer(primaRiga);
		
		if (tokens.countTokens() != 2 && tokens.countTokens() != 3)
			throw new BadFileFormatException("il file non contiene in una riga 2 o 3 elementi");
		boolean areTwo = (tokens.countTokens() == 2);
		
		if (!tokens.nextToken().trim().equalsIgnoreCase("CC"))
			throw new BadFileFormatException("il file deve iniziare per CC");
		
		long id;
		String strId = tokens.nextToken().trim().toUpperCase();
		if (!strId.startsWith("N."))
			throw new BadFileFormatException("il file deve iniziare per N.");
		
		if(!strId.substring(2).isEmpty() && !areTwo)
			throw new BadFileFormatException("prima riga spazio extra nel numero");
		
		try {
			id = areTwo ? Long.parseLong(strId.substring(2)) : Long.parseLong(tokens.nextToken().replaceAll("\\s", ""));
			if (id < 0) 
				throw new BadFileFormatException("id è negativo");
		
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("La riga di intestazione non contiene il numero di conto corrente");
		}
		ContoCorrente cc = new ContoCorrente(id);
		// inizio a leggere i movimenti
		
		String riga;
		while ((riga = reader.readLine()) != null){
			
			if(riga.isBlank()) continue;
			
			StringTokenizer st = new StringTokenizer(riga, "\r\n\t");
			if (st.countTokens() != 4)
				throw new BadFileFormatException("il file non contiene in una riga 4 elementi");
			
			var dateFormatter 		= DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY);
			String strDataContabile = st.nextToken().trim();
			String strDataValuta 	= st.nextToken().trim();
			String strImporto 		= st.nextToken().trim();
			String causale 			= st.nextToken("\r\n\t").trim().toUpperCase();
			
			try {
				LocalDate dataContabile = LocalDate.parse(strDataContabile, dateFormatter);
				LocalDate dataValuta    = LocalDate.parse(strDataValuta, dateFormatter);
				var numFormatter 		= NumberFormat.getNumberInstance (Locale.ITALY);
				double importo 			= numFormatter.parse(strImporto.replace(" ", "")).doubleValue();
				Tipologia tip = causale.startsWith("SALDO") && !causale.startsWith("SALDO INIZIALE") ? Tipologia.SALDO : 
								importo>0 ? (Tipologia.ACCREDITO) : 
								importo<0 ? Tipologia.ADDEBITO : Tipologia.NULLO;
				cc.aggiungi(new Movimento(dataContabile, dataValuta, tip, importo, causale.toUpperCase()));
			}
			catch(DateTimeParseException | ParseException e) {
				throw new BadFileFormatException("Il movimento contiene una data o un importo non correttamente formattati'\t" + riga);
			}		
		}
		return cc;
	}
}