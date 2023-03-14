package frazioni;

public class MyMath
{
	
	public static int mcd(int a, int b) {
		
		int resto;
		if(b > a){
			int temp = a;
			a = b;
			b = temp;
		}
		
		do {
			resto = a%b;
			a = b;
			b = resto;
			
		}while(resto != 0);
		
		return a;
	}
	
	public static int mcm(int a, int b) {
		return (a*b)/mcd(a, b);
	}

	public static int fact(int n) {
			
		if(n < 0) 		return -1;
		else if(n == 0) return 1;
		
		return n*fact(n-1);
	}
	
}
