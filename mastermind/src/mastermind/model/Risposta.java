package mastermind.model;

import java.util.Arrays;
import java.util.StringJoiner;

public class Risposta {

	private int dim;
	private PioloRisposta[] risposta;
	
	public Risposta(int dim) {
		this.dim = dim;
		risposta = new PioloRisposta[dim];
		Arrays.fill(risposta, PioloRisposta.VUOTO);
	}
	
	public int dim() {
		return this.dim;
	}
	
	public PioloRisposta[] getRisposta() {
		return this.risposta;
	}
	
	public PioloRisposta getPiolo(int index) {		
		return this.risposta[index];
	}
	
	public void setPiolo(int index, PioloRisposta pioloDaMettere) {
		this.risposta[index] = pioloDaMettere;
	}
	
	public boolean equals(Risposta that) {
		return (Arrays.equals(risposta, that.getRisposta())) && 
				(that.dim() == dim());
	}
	
	public boolean vittoria() {
		for (PioloRisposta pr : risposta) {
			if(!pr.equals(PioloRisposta.NERO))
				return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",");
		for(PioloRisposta r:risposta) {
			sj.add(r.toString());
		}
		return sj.toString();
	}
}
