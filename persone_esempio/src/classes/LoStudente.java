package classes;

import interfaces.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class LoStudente extends LaPersona implements Studente{

	private Esame[] esami;
	public LoStudente(String nome, String cognome, LocalDateTime dataDiNascita, String luogoDiNascita, Esame[] esami){
		super(nome, cognome, luogoDiNascita, dataDiNascita);
		this.esami = esami;
	}
	
	@Override
	public Esame[] esami() { 
		return esami;
	}
	
	@Override
	public String toString() {
		return super.toString() + " e ha in carriera in seguenti esami " + Arrays.toString(esami);
	}
	
}
