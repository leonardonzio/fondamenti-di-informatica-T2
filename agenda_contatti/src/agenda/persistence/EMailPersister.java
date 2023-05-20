package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Address;
import agenda.model.Detail;
import agenda.model.EMail;
import javafx.scene.SnapshotParameters;

public class EMailPersister implements DetailPersister{
	
	private final String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		if(source.countTokens() != 2)
			throw new BadFileFormatException("errore di lettura, non abbastanza tokenss");
		
		EMail em = new EMail();
		em.setDescription(source.nextToken().trim());
		em.setValue(source.nextToken().trim());

		return em;
	}

	@Override
	public void save (Detail d, StringBuilder sb) {
		sb.append(d.getDescription()).append(SEPARATOR);
		sb.append(d.getValues());
	}

	
	
}
