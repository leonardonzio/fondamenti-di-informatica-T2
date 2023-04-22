package interfaces;

import java.time.LocalDateTime;

public interface Persona {

	public String nome();
	public String cognome();
	public LocalDateTime dataDiNascita();
	public String luogoDiNascita();
}
