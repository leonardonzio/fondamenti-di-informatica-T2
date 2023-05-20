package agenda.persistence;

import java.io.BufferedReader;
import java.io.FileReader;  
import java.io.IOException;
import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister{

	private final String SEPARATOR = ";";
	
	@Override
	public Detail load (StringTokenizer source) throws BadFileFormatException {	
		if(source.countTokens() != 6)
			throw new BadFileFormatException("errore di lettura, non abbastanza tokens");
		
		Phone p = new Phone();
		p.setDescription(source.nextToken().trim());
		p.setValue(source.nextToken().trim());
		
		return p;
	}
	
	@Override
	public void save (Detail d, StringBuilder sb) {
		sb.append(d.getDescription()).append(SEPARATOR);
		sb.append(d.getValues());
	}

}
