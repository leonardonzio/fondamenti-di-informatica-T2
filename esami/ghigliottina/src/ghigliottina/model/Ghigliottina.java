package ghigliottina.model;

import java.util.Iterator;
import java.util.List;

public class Ghigliottina {

	private List<Terna> terne;
	private Iterator<Terna> iterator;
	private String rispostaEsatta;
	
	public Ghigliottina(List<Terna> terne, String rispostaEsatta) {
		if (terne == null || terne.isEmpty())
			throw new IllegalArgumentException("terne non valide");
		
		if (rispostaEsatta == null || rispostaEsatta.isBlank())
			throw new IllegalArgumentException("risposta non valida");	
		
		this.terne = terne;
		this.rispostaEsatta = rispostaEsatta;
		this.iterator = terne.iterator();
	}

	public List<Terna> getTerne() {
		return terne;
	}
	
	public String getRispostaEsatta() {
		return rispostaEsatta;
	}
	
	public boolean hasNext() {
		return iterator.hasNext();
	}
	
	public Terna next() {
		return iterator.next();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		while (hasNext()) {
			sb.append(next()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
}
