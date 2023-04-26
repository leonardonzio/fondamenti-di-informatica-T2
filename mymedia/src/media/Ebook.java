package media;

import utils.StringUtils;

public class Ebook extends Media {

	private String[] authors = null;
	private String genre = null;
	
	public Ebook(String title, int year, String[] authors, String genre) {
		super(title, year);
		this.authors = authors;
		this.genre = genre;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors.clone();
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public Type getType() {
		return Type.EBOOK;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Ebook that) {
		return 	super.equals(that) && 
				StringUtils.areEquivalent(getAuthors(), that.getAuthors()) &&
				getGenre().equals(that.getGenre());
		}
		else return false;
	}
		
	
	
	
	
}
