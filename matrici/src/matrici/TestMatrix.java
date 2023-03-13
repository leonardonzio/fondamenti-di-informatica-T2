package matrici;

import java.nio.channels.AsynchronousServerSocketChannel;


public class TestMatrix {
	public static void main(String[] args) {
		
		double[][] temp = {	{1,2,1,2},
							{3,4,1,2},
							{5,6,66,2},
							{1,4,5,3}};
		
		double[][] temp2 = {{1}};
	
		Matrix m = new Matrix(temp);
		Matrix m2 = new Matrix(temp2);
		
		// vediamo se stampa
		System.out.println(m.toString());
		
		// controllo che sia quadrata
		assert(m.isSquared()); 

		
		// controllo somma matrici
		
		// controllo prodotto

		
		
		// controllo determinante ok
		assert(m.det() == 390.0);
		assert(m2.det() == 1);
	}
}
