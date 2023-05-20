package agenda.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Agenda {
	private SortedSet<Contact> contactSet;
	
	public Agenda () {
		this.contactSet = new TreeSet<Contact>();
	}
	
	public Agenda(Collection<Contact> contacts) {
		if(contacts == null)
			throw new IllegalArgumentException("contacts null");
		
		this.contactSet = new TreeSet<Contact>(contacts);
	}

	public SortedSet<Contact> getContacts () {
		return new TreeSet<>(this.contactSet);
	}
	
	public Optional<Contact> getContact (int index) {
		var contactsList = new ArrayList<Contact>(this.contactSet);
		return Optional.ofNullable(contactsList.get(index));
	}
	
	public Optional<Contact> getContact (String name, String surname) {
		return contactSet
			.stream()
			.filter(c -> c.getName().equals(name) && 
					c.getSurname().equals(surname))
			.findFirst();
	}
	
	public void addContact (Contact c) {
		this.contactSet.add(c);
	}
	
	public void removeContact (Contact c) {
		this.contactSet.remove(c);
	}
	
	public SortedSet<Contact> searchContacts (String surname) {
		return contactSet
				.stream()
				.filter(c -> c.getSurname().equals(surname))
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
}
