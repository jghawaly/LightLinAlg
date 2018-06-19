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
	
	/**
	 * Scale every element in a specified row by a given scalar. An identity matrix is first generated and the element along the diagonal in the identity
	 * matrix at the specified row index is set to the scalar value. This identity matrix is then multiplied by the given Matrix a, the result of which is returned.
	 * @param a Matrix to scale
	 * @param scalar Value to scale row with
	 * @param rowIndex the index of the row in which to scale
	 * @return matrix with scaled row
	 */
	public static Matrix rowScale(Matrix a, double scalar, int rowIndex) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(rowIndex, rowIndex, scalar);
		
		return matrixMultiply(identity, a);
	}
	
	/**
	 * Scale every element in a specified column by a given scalar. An identity matrix is first generated and the element along the diagonal in the identity
	 * matrix at the specified column index is set to the scalar value. Matrix a is then multiplied by this identity matrix, the result of which is returned.
	 * @param a Matrix to scale
	 * @param scalar Value to scale column with
	 * @param columnIndex the index of the column in which to scale
	 * @return matrix with scaled column
	 */
	public static Matrix columnScale(Matrix a, double scalar, int columnIndex) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(columnIndex, columnIndex, scalar);
		
		return matrixMultiply(a, identity);
	}
	
	/**
	 * Interchange rows in a given matrix. An identity matrix is first generated for Matrix a with the rows switched as supplied by the two indices. This identity
	 * matrix is then multiplied by Matrix A, the result of which is returned.
	 * @param a Matrix to interchange rows in
	 * @param rowIndex1 index of first row
	 * @param rowIndex2 index of second row
	 * @return Matrix with rows interchanged
	 */
	public static Matrix rowInterchange(Matrix a, int rowIndex1, int rowIndex2) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		
		// interchange row 2 into row 1
		identity.set(rowIndex1, rowIndex1, 0.0);
		identity.set(rowIndex1, rowIndex2, 1.0);
		
		// interchange row 1 into row 2
		identity.set(rowIndex2, rowIndex2, 0.0);
		identity.set(rowIndex2, rowIndex1, 1.0);
		
		return matrixMultiply(identity, a);
	}
	
	/**
	 * Interchange columns in a given matrix. An identity matrix is first generated for Matrix a with the columns switched as supplied by the two indices. This identity
	 * matrix is then multiplied by Matrix A, the result of which is returned.
	 * @param a Matrix to interchange columns in
	 * @param rowIndex1 index of first column
	 * @param rowIndex2 index of second column
	 * @return Matrix with columns interchanged
	 */
	public static Matrix columnInterchange(Matrix a, int columnIndex1, int columnIndex2) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		
		// interchange row 2 into row 1
		identity.set(columnIndex1, columnIndex1, 0.0);
		identity.set(columnIndex1, columnIndex2, 1.0);
		
		// interchange row 1 into row 2
		identity.set(columnIndex2, columnIndex2, 0.0);
		identity.set(columnIndex2, columnIndex1, 1.0);
		
		return matrixMultiply(a, identity);
	}
}
