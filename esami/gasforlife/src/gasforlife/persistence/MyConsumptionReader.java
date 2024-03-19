package gasforlife.persistence;

import java.awt.Component.BaselineResizeBehavior;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sound.sampled.LineEvent;

import org.junit.runners.model.FrameworkField;

public class MyConsumptionReader implements ConsumptionReader{

	private final double FATTORE_CONVERSIONE = 10.69;
	private Map<String, List<Double>> gasConsumption;
	

	public MyConsumptionReader(Reader rdr) throws IOException, BadFileFormatException {

		if (rdr == null)
			throw new IllegalArgumentException("reader inesistente");
		
		gasConsumption = new HashMap<>();
		loadAllItems(rdr);	
	}
	
	private void loadAllItems(Reader rdr) throws IOException, BadFileFormatException{
		
		// key: codice appartamento
		// value: lista delle dodoci letture mensili di consumo dell appartamento
		
		var buffReader = new BufferedReader(rdr);
		
		String line;
		while ((line = buffReader.readLine()) != null) {
			
			var tokens = new StringTokenizer(line);
			
			String id = tokens.nextToken("\s").trim();
			if (id.isBlank() || id.isEmpty())
				throw new BadFileFormatException("id vuoto");
			
			String lineWithoutId = line.replaceFirst(id, "");
			if (!lineWithoutId.startsWith("\s:\s"))
				throw new BadFileFormatException("deve avere \" : \"");
			
			String[] tokensConsumi = lineWithoutId.replaceFirst("\\:", "").trim().split("\\|");
			if (tokensConsumi.length != 12)
				throw new BadFileFormatException("devonop essere 12");
			
			List<Double> consumptions = new ArrayList<>();
			try {
				for (int i = 0; i < tokensConsumi.length; i++) {
					
					double consumptionInm3 = Double.parseDouble(tokensConsumi[i].trim())
							/ FATTORE_CONVERSIONE;
					consumptions.add(consumptionInm3);
				}
				
			} catch (Exception e) {
				throw new BadFileFormatException("consumo formattato male");	
			}				
			
			gasConsumption.put(id, consumptions);			
		}
		
	}
	
	public Map<String, List<Double>> getItems(){
		return gasConsumption;
	}
	
	
}
