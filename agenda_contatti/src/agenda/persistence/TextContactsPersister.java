package agenda.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.swing.text.html.Option;

import org.junit.runners.model.FrameworkField;

import agenda.model.Contact;
import agenda.model.Detail;

public class TextContactsPersister implements ContactsPersister {

	private final String SEPARATOR = ";";

	@Override
	public List<Contact> load(Reader reader) throws IOException, BadFileFormatException {
		if(reader == null)
			throw new IOException("reader null");
		
		BufferedReader buffReader = new BufferedReader(reader);
		var contacts = new ArrayList<Contact>();
		var c = readContact(buffReader);
		
		while(c.isPresent()) {
			contacts.add(c.get());
			c = readContact(buffReader);
		}
		
		return contacts;
	}

	@Override
	public void save(List<Contact> contacts, Writer writer) throws IOException {
		for (Contact c : contacts) {
			saveContact(c, writer);
		}
	}

	private Optional<Contact> readContact (BufferedReader innerReader) throws IOException, BadFileFormatException{
		String riga = innerReader.readLine();
		if(!riga.equals("StartContact"))
			throw new BadFileFormatException("StartContact expected");
		
		StringTokenizer st = new StringTokenizer (riga, SEPARATOR);
		if(st.countTokens() != 2)
			throw new BadFileFormatException();
		
		String name = st.nextToken().trim();
		String surname = st.nextToken().trim();
		try {
			Contact c = new Contact(name, surname);
			readDetails(innerReader, c);
			return Optional.of(c);
			
		} catch (NoSuchElementException e) {
			throw new BadFileFormatException("Tokens not found", e);
		}
	}
	
	private void readDetails (BufferedReader innerReader, Contact c) throws IOException, BadFileFormatException{
		boolean isDetail;
		do {
			String riga = innerReader.readLine();
			if(riga == null)
				throw new BadFileFormatException("Detail or EndContact expected");
			
			isDetail = !riga.equals("EndContact");
			if(!isDetail)
				return;
			
			StringTokenizer st = new StringTokenizer(riga, SEPARATOR);
			String tipoDetail = st.nextToken().trim();
			
			DetailPersister dp = DetailPersister.of(tipoDetail);
			if(dp == null)
				throw new BadFileFormatException("Unknown Detail Type");
			
			Detail d = dp.load(st);
			c.getDetailList().add(d);

		} while (isDetail);
	}

	
	private void saveContact(Contact c, Writer innerWriter) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("StartContact" + FileUtils.NEWLINE);
		sb.append(c.getName() + SEPARATOR + c.getSurname()
				+ FileUtils.NEWLINE);
		saveDetails(c.getDetailList(), sb);
		sb.append("EndContact" + FileUtils.NEWLINE);
		innerWriter.write(sb.toString());
	}

	private void saveDetails(List<Detail> detailList, StringBuilder sb) {
		for (Detail d : detailList) {
			DetailPersister loaderSaver = DetailPersister.of(d.getName());
			loaderSaver.save(d, sb);
		}
	}
	
	
}
