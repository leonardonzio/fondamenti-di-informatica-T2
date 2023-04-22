package main;

import classes.IlLavoratore;
import interfaces.*;
import classes.*;
import classes.LoStudenteLavoratore;
import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) {
		
		Persona[] persone = {
			new LaPersona("Mario", "Rossi", "Bologna", LocalDateTime.of(1998,12,25,13,20)),
			new IlLavoratore("Giacomo", "Neri", "Bologna", LocalDateTime.of(1985,2,15,11,50), "lo chef", 50000),
			new LoStudente("Paolo", "Verdi", LocalDateTime.of(2001,3,27,14,14), "Bologna",
					new Esame[]{
						new LEsame("Analisi 1", 30, true),
						new LEsame("Fondamenti 1", 28, false)}),
			new LoStudenteLavoratore("Elvio", "Bruni", LocalDateTime.of(1999,4,25,4,51), "Bologna",
					new Esame[]{
						new LEsame("Analisi 1", 25, false),
						new LEsame("Fondamenti 1", 30, true)
					},
				5000, "il rider part time")
		};
		
		for (Persona p : persone) {
			System.out.println(p);
		}
		
	}
	
}
