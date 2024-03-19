package rfd.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.text.NumberFormatter;

import org.w3c.dom.html.HTMLIFrameElement;
import org.xml.sax.ext.LexicalHandler;

import javafx.stage.StageStyle;
import rfd.model.RailwayLine;
import rfd.model.Station;
import rfd.ui.RailwayApp;

public class MyRailwayLineReader implements RailwayLineReader {

	private final NumberFormat kmFormatter = DecimalFormat.getNumberInstance(Locale.ITALY);

	public static void main(String[] args) throws IOException{
		
		Optional<List<String>> filenames = RailwayLineReader.getAllLineNames(Paths.get("."));
		var rdr = new MyRailwayLineReader();
		for (String filename : filenames.get()) {
			RailwayLine line = rdr.getRailwayLine(new FileReader(filename));
			System.out.println(line);
		}

	}
	
	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException{
		
		if (rdr == null)
			throw new IllegalArgumentException("reader nullo");
		
		var buffReader = new BufferedReader(rdr);
		
		var stationsMap 		= new HashMap<String, Station>();
		var nameStationsWithHUB = new TreeSet<String>();
		int lengthPreviousLine = 0;
		String line;
		while ((line = buffReader.readLine()) != null) {
			
			lengthPreviousLine = (lengthPreviousLine == 0)
					? line.length()
					: lengthPreviousLine;
			
			if (line.length() != lengthPreviousLine)
				throw new IllegalArgumentException("lunghezza righe diverse");
			
			lengthPreviousLine = line.length();
			
			String progressivaAsString = line.substring(0,8).trim();
			if (!progressivaAsString.matches("\\d+(\\,?\\d+)"))
				throw new IllegalArgumentException("la velocita non è formattata correttamente");
			
			double progressiva;
			try {
				progressiva = kmFormatter.parse(progressivaAsString).doubleValue();
			} catch (ParseException e) {
				throw new IllegalArgumentException("progressiva formattata male");
			}
			
			int indexSpace = line.lastIndexOf(" ");
			String velocitaAsString = line.substring(indexSpace, line.length()).trim();
			int velocita;
			try {
				velocita = Integer.parseInt(velocitaAsString);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("velocita parsata male");
			}
			
			String stationName = line.substring(8, indexSpace).trim();			
			if (stationName.isEmpty())
				throw new IllegalArgumentException("nome stazione vuoto");
			
			if (stationName.equalsIgnoreCase("HUB")) 
				throw new IllegalArgumentException("ha solo HUB");
			
			boolean isHUB = stationName.toUpperCase().contains("HUB");
			if (isHUB) {				
				stationName = stationName.substring(0, stationName.toUpperCase().lastIndexOf("HUB")).trim();
				nameStationsWithHUB.add(stationName);
			}
			
			/* mia soluzione anche migliore
			stationName = isHUB 
					? stationName.replaceAll("(?i)" + "HUB", "").trim() // regex che ignora la capitalizzazione
					: stationName;
			*/
			
			var station = new Station(stationName, progressiva, velocita);
			stationsMap.put(stationName, station);

		}
		return new RailwayLine(stationsMap, nameStationsWithHUB);
	}

	
	
	
	
}
