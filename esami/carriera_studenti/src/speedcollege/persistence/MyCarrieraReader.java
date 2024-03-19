package speedcollege.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.sound.sampled.Line;

import speedcollege.model.AttivitaFormativa;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.model.Voto;


public class MyCarrieraReader implements CarrieraReader {

	/*
	 * 	27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT
		27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22
		28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24
		29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26
		26337	LINGUA INGLESE B-2 (cfu:6)
		27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE
		27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT
		28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)
		28011	RETI LOGICHE T (cfu:6)		
		...
	*/
	
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY);
	

	@Override
	public Carriera leggiCarriera(Reader rdr) throws IOException {

		if (rdr == null)
			throw new IllegalArgumentException("reader nullo");
		
		var reader = new BufferedReader(rdr);
		String line;
		Carriera c = new Carriera();
		while ((line = reader.readLine()) != null) {
			if (line.isBlank()) continue;
			
			String[] tokens = line.split("\\t+");
			int nTkn = tokens.length;
			if (nTkn != 2 && nTkn != 4)
				throw new BadFileFormatException("ci devono essere 2 o 4 tokens");
			
			String idAsString 		= tokens[0].trim();
			String nomeECfuAsString = tokens[1].trim();
			
			long id;
			try {
				id = Long.parseLong(idAsString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("id non formattato correttamente");
			}
			if (id < 1) throw new BadFileFormatException("id minore di 1");	
			
			String[] nomeECfu = nomeECfuAsString.split("\\(cfu:");
			if (nomeECfu.length != 2)
				throw new BadFileFormatException("formattato male cfu");
			
			String nomeAf = nomeECfu[0].trim().toUpperCase();
			String[] cfuSenzaParentesiFinale = nomeECfu[1].trim().split("\\)");
			if (cfuSenzaParentesiFinale.length != 1)
				throw new BadFileFormatException("cfu mal formtattai");
			
			int cfu;
			try {
				cfu = Integer.parseInt(cfuSenzaParentesiFinale[0]);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("cfu formattati male");
			}
			
			if (nTkn == 2) continue;
			
			String dateAsString = tokens[2];
			String votosString 	= tokens[3];
			
			LocalDate date;
			try {
				date = LocalDate.parse(dateAsString, dateFormatter);
			} catch (DateTimeParseException e) {
				throw new BadFileFormatException("data parsata male");
			}
			
			Voto voto;
			try {
				voto = Voto.of(votosString);
			} catch (IllegalArgumentException e) {
				throw new BadFileFormatException("voto parsato male");
			}
			
			try {
				c.inserisci(new Esame(new AttivitaFormativa(id, nomeAf, cfu), date, voto));	
			} catch (IllegalArgumentException e) {
				throw new BadFileFormatException("inserimento illegale");
			}
			
		}
		
		return c;
	}
	

}