package frazioni;

import util.MyMath;


public class Frazione {
	private int num, den;
	
	// getter
	public int getNum() {
		return num;
	}
	public int getDen() {
		return den;
	}
	
	// costruttori
	public Frazione (int n, int d) {
		this.num = n;
		this.den = d;
	}
	public Frazione (int n) {
		this(n, 1);
	}
	
	
	public boolean equals (Frazione other) {
		return num * other.getDen() == den * other.getNum();
	}
		
	
	// metodo che riduce una frazione ai minimi termini
	public Frazione minTerm() {
		
		int num = this.num/MyMath.mcd(this.num, this.den);
		int den = this.den/MyMath.mcd(this.num, this.den);
		
		// se il meno è al denominatore, lo metto al numeratore
		if(num > 0 && den < 0) return new Frazione(-num, -den);
		
		// altrimenti lascio così
		return new Frazione(num, den);	
	}
	
	@Override
	public String toString() {
		String str = "";
		int num = getNum();
		int den = getDen();

		str += getDen() == 1 ? num : num + "/" + den;		
		return str;	   
	}
	
	
	public Frazione sum(Frazione other) {
		
		int den = MyMath.mcm(getDen(), other.getDen());
		int num = den*getNum()/getDen() + den*other.getNum()/other.getDen();
		
		Frazione res = new Frazione(num, den);
		return res.minTerm();
	}
	
	public Frazione sub(Frazione other) {
		Frazione res = new Frazione(-other.getNum(), other.getDen());
		Frazione myF = new Frazione(getNum(), getDen());
		return myF.sum(res);
	}
	
	public Frazione mul(Frazione other) {
		int n = getNum()*other.getNum();
		int d = getDen()*other.getDen();
		return new Frazione(n,d).minTerm();	
	}
	
	public Frazione div(Frazione other) {
		int n = getNum()*other.getDen();
		int d = getDen()*other.getNum();
		return new Frazione(n, d);
	}	
	
	public Frazione reciprocal() {
		return new Frazione(getDen(), getNum());
	}
	
	public double convertToDouble() {
		return (double)getNum()/getDen();
	}
	
	
	
}
