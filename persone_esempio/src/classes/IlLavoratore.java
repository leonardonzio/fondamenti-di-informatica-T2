package classes;

import java.time.LocalDateTime;

import interfaces.Lavoratore;

public class IlLavoratore extends LaPersona implements Lavoratore {

	private String lavoro;
	private double stipendio;
	
	public IlLavoratore(String nome, String cognome, String luogoDiNascita, LocalDateTime dataDiNascita, String lavoro, double stipendio) {
		super(nome, cognome, luogoDiNascita, dataDiNascita);
		this.lavoro = lavoro;
		this.stipendio = stipendio;
	}
	
	@Override
	public String lavoro() {
		return this.lavoro; 
	}
	
	@Override
	public double stipendio() {
		return this.stipendio;	
	}
	
	@Override
	public String toString() { 
		return super.toString() + ", di mestiere fa " + lavoro() + " e guadagna € " + stipendio(); 
	}

	
}
