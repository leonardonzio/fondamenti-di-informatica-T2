package media;


import java.util.Arrays;

import media.filters.HasDuration;
import media.filters.HasGenre;
import utils.StringUtils;


public class Film extends Media implements HasGenre, HasDuration{

	private String actors[] = null;
	private String director = null;
	private int duration = -1;
	private String genre = null;
	
	
	public Film(String title, int year, String director, int duration, String[] actors, String genre) {
		super(title, year);
		this.actors = actors;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors.clone();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Film that) {
			return 	super.equals(that) &&
					StringUtils.areEquivalent(getActors(), that.getActors()) &&
					getDirector().equals(that.getDirector()) &&
					getDuration() == that.getDuration() &&
					getGenre().equals(that.getGenre());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return 	super.toString() + "regista: " + getDirector() + ", durata: " + getDuration() +
				", elenco attori: " + Arrays.deepToString(getActors()) + ", genere: " + getGenre();
	}
	
	@Override
	public Type getType() {
		return Type.FILM;
	}
	
	
}
