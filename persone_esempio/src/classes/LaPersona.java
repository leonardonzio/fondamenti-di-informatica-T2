package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import interfaces.*;

public class LaPersona implements Persona{

	private String nome, cognome, luogoDiNascita;
	private LocalDateTime dataDiNascita;
	
	public LaPersona(String nome, String cognome, String luogoDiNascita, LocalDateTime dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.luogoDiNascita = luogoDiNascita;
		this.dataDiNascita = dataDiNascita;
	}


	@Override
	public String nome() {
		return this.nome;
	}
	
	@Override
	public String cognome() {
		return this.cognome;
	}
	
	@Override
	public LocalDateTime dataDiNascita() {
		return this.dataDiNascita;
	}
	
	@Override
	public String luogoDiNascita() {
		return this.luogoDiNascita;
	}
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.ITALY);
	@Override
	public String toString() { 
		return 	cognome() + " " + nome() + " nato a " + luogoDiNascita() + " il " +
				dataDiNascita().format(formatter);
	}
	
}

