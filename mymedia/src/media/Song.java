package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasDuration, HasGenre{

	private int duration = -1;
	private String genre = null;
	private String singer = null;
	

	public Song(String title, int year, String singer, int duration, String genre) {
		super(title, year);
		this.singer = singer;
		this.duration = duration;
		this.genre = genre;
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

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Song that) {
			return 	super.equals(that) &&
					getSinger().equals(that.getSinger()) &&
					getGenre().equals(that.getGenre()) &&
					getDuration() == that.getDuration();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return 	super.toString() + "cantante: " + getSinger() + ", durata: " + getDuration() +
				", genere: " + getGenre();
	}
	
	@Override
	public Type getType() {
		return Type.SONG;
	}

	
}
