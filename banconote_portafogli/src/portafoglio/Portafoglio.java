package portafoglio;

import java.util.Arrays;
import java.util.StringJoiner;

import taglio.Taglio;

public class Portafoglio {

	private Taglio[] contenuto;
	int logicalSize;
	
	public Portafoglio(int n) {
		
		contenuto = new Taglio[n];
		logicalSize = 0;
	}
	
	public Portafoglio(Taglio[] tArr) {
		
		contenuto = Arrays.copyOf(tArr, tArr.length);
		logicalSize = contenuto.length;
	}

	
	// metodi
	public void add (Taglio t) {
		
		contenuto[logicalSize] = t;
		logicalSize++;
	}
	
	
	public int getValore() {
		
		int somma = 0;
		for (int i = 0; i < logicalSize; i++) {
			somma += contenuto[i].getValore();
		}
		return somma;
	}
	
	@Override
	public String toString() {
		
		StringJoiner sj = new StringJoiner(", ", "(", ")"); 
		for (int i = 0; i < logicalSize; i++) {
			sj.add(contenuto[i].getParola());
		}
		return "valore: " + sj.toString();
	}
	
	
	
	/**
	 *@return 	1	se ha valore più alto
	 *@return 	0	se hanno lo stesso valore
	 *@return 	-1	se ha valore più basso
	*/
	public int compare(Portafoglio other) {
		
		if(getValore() == other.getValore())
			return 0;
		
		return getValore() > other.getValore() ? 1 : -1;
	}
	
	
	
	
	
	
}
