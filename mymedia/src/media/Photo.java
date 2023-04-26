package media;

import java.util.Arrays;

import utils.StringUtils;

public class Photo extends Media{

	private String[] authors = null;

	public Photo(String title, int year, String[] authors) {
		super(title, year);
		this.authors = authors;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors.clone();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Photo that) {
			return 	super.equals(that) &&
					StringUtils.areEquivalent(getAuthors(), that.getAuthors());
		}
		return false;
	}
	
	@Override
	public Type getType() {
		return Type.PHOTO;
	}
	
	@Override
	public String toString() {
		return super.toString() + "autori: " + Arrays.deepToString(authors);
	}
	
	
}
