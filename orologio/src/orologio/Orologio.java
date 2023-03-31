package orologio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import counter.Counter;

public class Orologio {

	private Counter ore, minuti, secondi;
	
	public Orologio(int h, int m, int s) {
		this.ore = new Counter(h);
		this.minuti = new Counter(m);
		this.secondi = new Counter(s);
	}
	
	public Orologio(int h, int m) {
		this(h, m, 0);
	}
	
	public Orologio(int h) {
		this(h, 0, 0);
	}
	
	public Orologio() {
		this(0, 0, 0);
	}

	public Counter getOre() {
		return ore;
	}

	public Counter getMinuti() {
		return minuti;
	}

	public Counter getSecondi() {
		return secondi;
	}
	
	public void tic() {
		
		if(secondi.getValue() != 59) {
			secondi.inc();
		}
		
		else if (minuti.getValue() != 59) {
			minuti.inc();
			secondi.reset();
		}
		
		else {
			ore.inc();
			minuti.reset();
			secondi.reset();
		}
	}
	
	public boolean equals(Orologio other) {
		if(getOre().getValue() == other.getOre().getValue() &&
			getMinuti().getValue() == other.getMinuti().getValue() &&
			getSecondi().getValue() == other.getSecondi().getValue()) {
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter dTF = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.ITALY);
		return ("Orario: " + dTF.format(LocalTime.of(ore.getValue(), minuti.getValue(), secondi.getValue())));
	}
	
	
}
