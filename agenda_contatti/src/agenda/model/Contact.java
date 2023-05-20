package agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Contact implements Comparable<Contact>{

	private List<Detail> detailList;
	private String name;
	private String surname;
	
	@Override
	public int compareTo(Contact o) {
		var res = this.surname.compareToIgnoreCase(o.getSurname());
		return (res == 0) ? this.name.compareToIgnoreCase(o.getName()) : res;
	}
	
	public Contact(String name, String surname, List<Detail> detailList) {
		if(detailList == null)
			throw new IllegalArgumentException("detailList null");
		
		if(name == null || surname == null)
			throw new IllegalArgumentException("name or surname are null");

		if(name.isEmpty() || surname.isEmpty())
			throw new IllegalArgumentException("name or surname are empty");
		
		this.name = name;
		this.surname = surname;
		this.detailList = detailList;
	}

	public Contact(String name, String surname) {
		if(name == null || surname == null)
			throw new IllegalArgumentException("name or surname are null");

		if(name.isEmpty() || surname.isEmpty())
			throw new IllegalArgumentException("name or surname are empty");
		
		this.name = name;
		this.surname = surname;
		this.detailList = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return this.detailList
				.stream()
				.map(d -> d.toString() + "\n")
				.reduce(name + surname + "\n", String::concat);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(detailList, name, surname);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contact))
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(detailList, other.detailList) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}
	
	public List<Detail> getDetailList() {
		return this.detailList;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
