package orologiocondisplay;

import java.time.LocalTime;

import orologio.Orologio;
import sevensegmentdisplay.Display;

public class OrologioConDisplay {

	Orologio orologio;
	Display display = new Display(8);
	
	
	public OrologioConDisplay(LocalTime orario) {	
		this.orologio = new Orologio(orario.getHour(), orario.getMinute(), orario.getSecond());
		
		int h, m, s;
		h = orologio.getOre().getValue();
		m = orologio.getMinuti().getValue();
		s = orologio.getSecondi().getValue();
		
		this.display.setValue(h*1000000 + m*1000 + s);
	}
	
	public Orologio getOrologio() {
		return orologio;
	}

	public void tic() {
		
		orologio.tic();
		int h, m, s;
		h = orologio.getOre().getValue();
		m = orologio.getMinuti().getValue();
		s = orologio.getSecondi().getValue();
		
		this.display.setValue(h*1000000 + m*1000 + s);
	}
	
	public String toString() {
		return orologio.toString();
	}
	
	
	public boolean equals(OrologioConDisplay other) {
		return orologio.equals(other.getOrologio());
	}
	
	
	
	
	
}
