package taglio;

public enum Taglio{
	
	CINQUECENTO(500, "cinquecento"), DUECENTO(200, "duecento"), CENTO(100, "cento"), 
	CINQUANTA(50, "cinquanta"), VENTI(20, "venti"), DIECI(10, "dieci"), CINQUE(5, "cinque"),
	DUE(2, "due"), UNO(1, "uno");
	
	private String parola;
	private int valore;
	
	private Taglio(int valore, String parola){
		this.valore = valore;
		this.parola = parola;
	}
	
	public int getValore(){
		return valore;
	}
	
	public String getParola(){
		return parola;
	}
	
	
	
}