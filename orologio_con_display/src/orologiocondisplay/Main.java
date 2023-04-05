package orologiocondisplay;

import java.time.LocalTime;

public class Main {
	public static void main(String[] args) {
		OrologioConDisplay o = new OrologioConDisplay(LocalTime.now());
		
		while(true) {
			try { Thread.sleep(1000); }
			catch(InterruptedException e){}
			o.tic();
		}
		
	}
}
