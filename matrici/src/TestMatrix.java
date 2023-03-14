import matrici.Matrix;
import java.nio.channels.AsynchronousServerSocketChannel;

public class TestMatrix {
	public static void main(String[] args) {
		
		double[][] temp = {	{1,2,1,2},
							{3,4,1,2},
							{5,6,66,2},
							{1,4,5,3}};
		
		double[][] temp2 = {{1}};
		
		double[][] temp3 = {{1,3,7,4},
							{3,4,3,2},
							{5,6,9,2},
							{2,1,5,3}};
		
		double[][] tempSum = {	{2,	5,	8,	6},
								{6,	8,	4,	4},
								{10,12,	75,	4},
								{3,	5,	10,	6}};
	
		double[][] tempProd = {	{16,19,32,16},
								{24,33,52,28},
								{357,437,657,170},
								{44,52,79,31}};
		
		Matrix m = new Matrix(temp);
		Matrix m2 = new Matrix(temp2);
		Matrix m3 = new Matrix(temp3);
		Matrix mSum = new Matrix(tempSum);
		Matrix mProd = new Matrix(tempProd);
		
		// vediamo se stampa
		System.out.println(m.toString());
		
		// controllo che sia quadrata
		assert(m.isSquared()); 

		// controllo somma matrici
		Matrix sommata = m.sommaMatrici(m3);
		assert(sommata.equals(mSum));
		
		
		// controllo prodotto
		Matrix moltiplicata = m.prodMatrici(m3);
		assert(moltiplicata.equals(mProd));
		
		
		// controllo determinante ok
		assert(m.det() == 390.0);
		assert(m2.det() == 1);
		assert(m3.det() == -160);
		assert(mProd.det() == -62400);
		
	}
}
