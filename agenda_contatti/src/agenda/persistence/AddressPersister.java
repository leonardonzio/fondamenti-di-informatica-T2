package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.Phone;

public class AddressPersister implements DetailPersister{

	private final String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		if(source.countTokens() != 6)
			throw new BadFileFormatException("errore di lettura, non abbastanza tokens");
		
		Address addr = new Address();		
		addr.setDescription(source.nextToken().trim());
		addr.setStreet(source.nextToken().trim());
		addr.setNumber(source.nextToken().trim());
		addr.setZipCode(source.nextToken().trim());
		addr.setCity(source.nextToken().trim());
		addr.setState(source.nextToken().trim());

		return addr;
	}

	@Override
	public void save (Detail d, StringBuilder sb) {
		sb.append(d.getDescription()).append(SEPARATOR);
		sb.append(d.getValues());
	}

}
