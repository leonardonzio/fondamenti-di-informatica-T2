package matrici;
import java.util.Arrays;

public class Matrix {
	
	private final int rows;
	private final int cols;
	private final double[][] m;

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	private double getValue(int row, int col) {
		return this.m[row][col];		
	}

	private Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.m = new double[rows][cols];
	}
	
	public Matrix(double[][] m) {
		this.rows = m.length;
		this.cols = m[0].length;
		this.m = m;
	}
	
	
	// @Override
	public boolean equals(Matrix other) {
		return Arrays.deepEquals(m, other.m);
	}
	
	public Matrix sommaMatrici(Matrix other) {
		
		double[][] res = new double[other.getRows()][other.getCols()];
		
		for (int i = 0; i < other.getRows(); i++)
			for (int j = 0; j < other.getCols(); j++)
				res[i][j] = m[i][j] + other.getValue(i, j);
			
		return new Matrix(res);
	}
	
	public Matrix prodMatrici(Matrix other){
		
		
		// se il numero colonne a != numero righe b
		// if(a[0].length != b.length) throw new Exception("n.colonne 'a' != n.righe 'b'");
		
		// altrimenti fai prodotto
		double[][] res = new double[m.length][other.getCols()];
		
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < other.getCols(); j++) {
				
				res[i][j] = 0;
				for (int k = 0; k < other.getRows(); k++) {
					
					res[i][j] += m[i][k]*other.getValue(k, j);
				}
			}
		}
		return new Matrix(res);
	}
	
	
	public boolean isSquared() {
		return getCols() == getRows();			
	}
		
	private double calcDet(double[][] m) {
		
		if (m.length == 1) {
			return m[0][0];
		}
		
		double det = 0;
		
		for (int i = 0; i < m[0].length; i++) {
			det += Math.pow(-1, i) * m[0][i] * calcDet(ExtractMinor(m, 0, i));
		}
		
		return det;
	}
	
	public double det() {
		if (m == null || m.length == 0 || m.length != m[0].length) {
			throw new IllegalArgumentException("matrice deve essere non vuota e quadrata.");
		}
		
		return calcDet(m);
	}
	
	@Override
	public String toString() {
		
		StringBuilder res = new StringBuilder();
		res.append("Matrice:\n(\n");
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++)
				res.append(getValue(i, j) + "\t");
			
			res.append("\n");
		}
		res.append(")");
		return res.toString();
	}
	
	private double[][] ExtractMinor(double[][] m, int row, int col) {
		
		if (m.length == 0 || m[0].length == 0) {
            throw new IllegalArgumentException("matrice vuota");
        }
	
        double[][] res = new double[m.length-1][m[0].length-1];
		int newI = 0, newJ = 0;
		
		for (int i = 0; i < m.length; i++) {
			if(i == row)
				continue;
			
			newJ = 0;
			for (int j = 0; j < m[0].length; j++) {
				if(j == col) 
					continue;
				
				res[newI][newJ] = m[i][j];
				newJ++;
			}
			newI++;
		}
		return res;
	}
	
}
