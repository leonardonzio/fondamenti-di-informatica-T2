package media;

import media.filters.HasType;

public abstract class Media implements HasType{

	private String title = null;
	private int year = -1;
	
	public Media(String title, int year) {
		setTitle(title);
		setYear(year);
	}

	@Override
	public boolean equals (Object o) {
		if(o instanceof Media that) {
			return 	getTitle().equals(that.getTitle()) && 
					getYear() == that.getYear();
		}
		return false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public abstract Type getType();
	
	@Override
	public String toString() {
		return "titolo: " + getTitle() + ", anno: " + getYear() + System.lineSeparator();
	}
	
	
	
	
}
