package mastermind.model;

import java.util.StringJoiner;
import java.util.Arrays;


public class Combinazione {

	private PioloDiGioco[] combinazione;
	private int dim;
	
	public Combinazione(int dim) {
		this.dim = dim;
		combinazione = new PioloDiGioco[dim];
		Arrays.fill(combinazione, PioloDiGioco.VUOTO);
	}
	
	public int dim() {
		return this.dim;
	}
	
	public PioloDiGioco[] getCombinazione() {
		return combinazione;
	}
	
	public PioloDiGioco getPiolo(int index) {
		return combinazione[index];
	}
	
	public void setPiolo(int index, PioloDiGioco pioloDaMettere) {
		combinazione[index] = pioloDaMettere;
	}
	
	public boolean equals(Combinazione that) {
		return (Arrays.equals(this.combinazione, that.getCombinazione())) && 
				(that.dim() == dim());
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",");		
		for (PioloDiGioco p : combinazione) {
			sj.add(p.name());
		}

		return sj.toString();
	}
	
	
}
