/**
 * @Copyright January 2017 James Michael Ghawaly Jr.
 */
package lightlinalg.linalg;

public final class LinearAlgebra {
	
	public static Matrix matrixAdd(Matrix a, Matrix b) {
		if(a.numRows() != b.numRows()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of rows!");
		}
		if(a.numColumns() != b.numColumns()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of columns!");
		}
		
		var c = new Matrix(new double[a.numRows()][a.numColumns()]);
		
		for(int i=0; i<a.numRows(); i++) {
			for(int j=0; j<a.numColumns(); j++) {
				c.set(i, j, a.get(i, j)+b.get(i, j));
			}
		}
		
		return c;
	}
	
	public static Matrix matrixSubtract(Matrix a, Matrix b) {
		if(a.numRows() != b.numRows()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of rows!");
		}
		if(a.numColumns() != b.numColumns()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of columns!");
		}
		
		var c = new Matrix(new double[a.numRows()][a.numColumns()]);
		
		for(int i=0; i<a.numRows(); i++) {
			for(int j=0; j<a.numColumns(); j++) {
				c.set(i, j, a.get(i, j)-b.get(i, j));
			}
		}
		
		return c;
	}
	
	/**
	 * Multiply two matrices A and B to form a new Matrix
	 * @param a Matrix A
	 * @param b Matrix B
	 * @return AB
	 */
	public static Matrix matrixMultiply(Matrix a, Matrix b) {
		if(a.numColumns() != b.numRows()) {
			throw new IllegalArgumentException("The number of columns in Matrix A must be equal to the number of rows in matrix B!");
		}
		
		var c = new Matrix(new double[a.numRows()][b.numColumns()]);
		
		// for row in A
		// 		for column in B
		//
		
		// for row in A
		for(int iA=0; iA<a.numRows(); iA++) {
			// for column in B
			for(int jB=0; jB<b.numColumns(); jB++) {
				double productSum = 0.0;
				// for row in B
				for(int iB=0; iB<b.numRows(); iB++) {
					productSum += b.get(iB, jB)*a.get(iA, iB);
				}
				c.set(iA, jB, productSum);
			}
		}
		
		return c;
	}
}
