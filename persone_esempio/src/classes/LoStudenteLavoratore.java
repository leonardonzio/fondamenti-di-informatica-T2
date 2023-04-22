package classes;

import java.time.LocalDateTime;

import interfaces.*;
import interfaces.StudenteLavoratore;

public class LoStudenteLavoratore extends LoStudente implements StudenteLavoratore{

	// a causa dell'ereditarieta singola, possiamo fare la extends solo di una superclasse.
	// è indifferente quale delle due facciamo.
	// in questo caso abbiamo considerato come superclasse LoStudente in modo da scrivere meno codice
	
	private String lavoro;
	private double stipendio;
	public LoStudenteLavoratore(String nome, String cognome, LocalDateTime dataDiNascita, String luogoDiNascita,
			Esame[] esami, double stipendio, String lavoro) {
		super(nome, cognome, dataDiNascita, luogoDiNascita, esami);
		this.lavoro = lavoro;
		this.stipendio = stipendio;
	}

	@Override
	public String toString() { 
		return super.toString() + ", di mestiere fa il " + lavoro() + " e guadagna € " + stipendio();
	}
	
	@Override
	public String lavoro() { 
		return lavoro;
	}
	
	@Override
	public double stipendio() {
		return stipendio;
	}
}
