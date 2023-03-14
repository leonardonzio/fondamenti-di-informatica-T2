package matrici;

public class Main {
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
		
		Matrix m = new Matrix(temp);
		Matrix m2 = new Matrix(temp2);
		Matrix m3 = new Matrix(temp3);
		
		Matrix MtimesM3 = m.prodMatrici(m3);
		Matrix MplusM3 = m.sommaMatrici(m3);
		
		System.out.println("Matrice M x M3: " + MtimesM3.toString()); 
		System.out.println("Matrice M + M3: " + MplusM3.toString());
		
		double detM3 = m3.det();
		System.out.println("Det M3: " + detM3);

	}
}
