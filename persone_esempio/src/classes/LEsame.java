package classes;

import interfaces.Esame;

public class LEsame implements Esame{
	private String denominazione;
	private int voto;
	private boolean lode;
	
	public LEsame (String denominazione, int voto, boolean lode){
		this.denominazione = denominazione;
		this.voto = voto;
		this.lode = lode; // solo se voto==30
	}
	public LEsame (String denominazione) { 
		this(denominazione, 0 ,false); 
	}
	
	@Override 
	public String denominazione() {
		return denominazione;
	}
	
	public int voto() { 
		return voto; 
	}
	
	public String votoAsString() { 
		return String.valueOf(voto);
	}
	
	public boolean lode() { 
		return lode;
	}
	
	@Override 
	public String toString() {
		return denominazione() + "\t" + voto() + (lode() ? "L" : "");
	}

}
