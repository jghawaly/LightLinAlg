/**
Copyright 2017 James Ghawaly Jr.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
IN THE SOFTWARE.
 **/
package lightlinalg.linalg;

import java.lang.Math;

public final class LinearAlgebra {

	public static Matrix matrixAdd(Matrix a, Matrix b) {
		if (a.numRows() != b.numRows()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of rows!");
		}
		if (a.numColumns() != b.numColumns()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of columns!");
		}

		var c = new Matrix(new double[a.numRows()][a.numColumns()]);

		for (int i = 0; i < a.numRows(); i++) {
			for (int j = 0; j < a.numColumns(); j++) {
				c.set(i, j, a.get(i, j) + b.get(i, j));
			}
		}

		return c;
	}

	public static Matrix matrixSubtract(Matrix a, Matrix b) {
		if (a.numRows() != b.numRows()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of rows!");
		}
		if (a.numColumns() != b.numColumns()) {
			throw new IllegalArgumentException("Matrices A and B must have the same number of columns!");
		}

		var c = new Matrix(new double[a.numRows()][a.numColumns()]);

		for (int i = 0; i < a.numRows(); i++) {
			for (int j = 0; j < a.numColumns(); j++) {
				c.set(i, j, a.get(i, j) - b.get(i, j));
			}
		}

		return c;
	}

	/**
	 * Multiply two matrices A and B to form a new Matrix
	 * 
	 * @param a
	 *            Matrix A
	 * @param b
	 *            Matrix B
	 * @return AB
	 */
	public static Matrix matrixMultiply(Matrix a, Matrix b) {
		if (a.numColumns() != b.numRows()) {
			throw new IllegalArgumentException(
					"The number of columns in Matrix A must be equal to the number of rows in matrix B!");
		}

		var c = new Matrix(new double[a.numRows()][b.numColumns()]);

		// for row in A
		for (int iA = 0; iA < a.numRows(); iA++) {
			// for column in B
			for (int jB = 0; jB < b.numColumns(); jB++) {
				double productSum = 0.0;
				// for row in B
				for (int iB = 0; iB < b.numRows(); iB++) {
					productSum += b.get(iB, jB) * a.get(iA, iB);
				}
				c.set(iA, jB, productSum);
			}
		}

		return c;
	}

	/**
	 * Scale every element in a specified row by a given scalar. An identity matrix
	 * is first generated and the element along the diagonal in the identity matrix
	 * at the specified row index is set to the scalar value. This identity matrix
	 * is then multiplied by the given Matrix a, the result of which is returned.
	 * 
	 * @param a
	 *            Matrix to scale
	 * @param scalar
	 *            Value to scale row with
	 * @param rowIndex
	 *            the index of the row in which to scale
	 * @return matrix with scaled row
	 */
	public static Matrix rowScale(Matrix a, double scalar, int rowIndex) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(rowIndex, rowIndex, scalar);

		return matrixMultiply(identity, a);
	}

	/**
	 * Scale every element in a specified column by a given scalar. An identity
	 * matrix is first generated and the element along the diagonal in the identity
	 * matrix at the specified column index is set to the scalar value. Matrix a is
	 * then multiplied by this identity matrix, the result of which is returned.
	 * 
	 * @param a
	 *            Matrix to scale
	 * @param scalar
	 *            Value to scale column with
	 * @param columnIndex
	 *            the index of the column in which to scale
	 * @return matrix with scaled column
	 */
	public static Matrix columnScale(Matrix a, double scalar, int columnIndex) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(columnIndex, columnIndex, scalar);

		return matrixMultiply(a, identity);
	}

	/**
	 * Interchange rows in a given matrix. An identity matrix is first generated for
	 * Matrix a with the rows switched as supplied by the two indices. This identity
	 * matrix is then multiplied by Matrix A, the result of which is returned.
	 * 
	 * @param a
	 *            Matrix to interchange rows in
	 * @param rowIndex1
	 *            index of first row
	 * @param rowIndex2
	 *            index of second row
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
	 * Interchange columns in a given matrix. An identity matrix is first generated
	 * for Matrix a with the columns switched as supplied by the two indices. This
	 * identity matrix is then multiplied by Matrix A, the result of which is
	 * returned.
	 * 
	 * @param a
	 *            Matrix to interchange columns in
	 * @param rowIndex1
	 *            index of first column
	 * @param rowIndex2
	 *            index of second column
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

	/**
	 * Perform a linear transformation between two rows in a matrix, for example in
	 * order to replace row_1 with row_1 - 3.0*row_5, you would set rowIndex1 to 0,
	 * rowIndex2 to 4, and scalar to -3.0. An identity matrix I is formed and
	 * I[rowIndex1, rowIndex2] is set to the scalar value, which is then multiplied
	 * by the matrix A, the result of which is returned.
	 * 
	 * @param a
	 *            the matrix to perform the transformation on
	 * @param rowIndex1
	 *            the index of the row to transform
	 * @param rowIndex2
	 *            the index of the row that is used to change the row at rowIndex1
	 *            by some scale
	 * @param scalar
	 *            The value to multiply that row at row_index2 with
	 * @return matrix with transformed rows
	 */
	public static Matrix linearRowTransform(Matrix a, int rowIndex1, int rowIndex2, double scalar) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(rowIndex1, rowIndex2, scalar);

		return matrixMultiply(identity, a);
	}

	/**
	 * Perform a linear transformation between two columns in a matrix, for example
	 * in order to replace column_1 with column_1 - 3.0*column_5, you would set
	 * columnIndex1 to 0, columnIndex2 to 4, and scalar to -3.0. An identity matrix
	 * I is formed and I[columnIndex2, columnIndex1] is set to the scalar value. The
	 * matrix A is then multiplied by this identity matrix, the result of which is
	 * returned.
	 * 
	 * @param a
	 *            the matrix to perform the transformation on
	 * @param columnIndex1
	 *            the index of the column to transform
	 * @param columnIndex2
	 *            the index of the column that is used to change teh column at
	 *            columnIndex1 by some scale
	 * @param scalar
	 *            The value to multiply the column at column_index2 with
	 * @return matrix with transformed columns
	 */
	public static Matrix linearColumnTransform(Matrix a, int columnIndex1, int columnIndex2, double scalar) {
		var identity = new Matrix(new double[a.numRows()][a.numColumns()]);
		identity.fillDiagonal(1.0);
		identity.set(columnIndex2, columnIndex1, scalar);

		return matrixMultiply(a, identity);
	}
	
	/**
	 * Calculate the determinant of a 2x2 matrix
	 * @param a the Matrix
	 * @return the determinant
	 */
	private static double det2x2(Matrix a) {
		return a.get(0, 0)*a.get(1, 1) - a.get(0, 1)*a.get(1, 0);
	}
	
	/**
	 * Calculate the determinant of an NxN matrix based off of the minor expansion formula
	 * @param a the Matrix
	 * @return the determinant
	 */
	private static double detNxN(Matrix a) {
		double det = 0.0;
		for(int j=0; j<a.numColumns(); j++) {
			// In this case, Math.pow(-1.0, i+j)*det(removeRowAndColumn(a, i, j)) is the cofactor of item a.get(i, j)
			det += a.get(0, j)*Math.pow(-1.0, 0+j)*det(removeRowAndColumn(a, 0, j));
		}
		return det;
	}
	
	/**
	 * Calculate the determinant of an N x N matrix
	 * @param a the Matrix
	 * @return determinant of the matrix
	 */
	public static double det(Matrix a) {
		double det = 0;
		if(!a.isSquare()) {
			throw new ArithmeticException("Matrix must be square in order to calculate a determinant!");
		}
		else {
			if (a.numRows() == 1) {
				det = a.get(0, 0);
			}
			else if(a.numRows() == 2) {
				det = det2x2(a);
			}
			else {
				det = detNxN(a);
			}
		}
		
		return det;
	}
	
	/**
	 * Remove an entire row and column from a Matrix
	 * @param a the Matrix to remove the row and column from
	 * @param rowIndex the index of the row to remove
	 * @param columnIndex the index of the column to remove
	 * @return the new Matrix
	 */
	public static Matrix removeRowAndColumn(Matrix a, int rowIndex, int columnIndex) {
		var arr = new double[a.numRows()-1][a.numColumns()-1];
		
		// declare row and column iterators for the new array
		int k = 0;
		int l = 0;
		
		// assign values to new array
		for(int i=0; i<a.numRows(); i++) {
			l=0;
			if(i != rowIndex) {
				for(int j=0; j<a.numColumns(); j++) {
					if(j != columnIndex) {
						arr[k][l] = a.get(i, j);
						++l;
					}
				}
				++k;
			} 
		}
		var m = new Matrix(arr);
		//m.prettyPrint();
		return m;
	}
	
	/**
	 * Remove an entire column from a Matrix
	 * @param a the Matrix to remove the column from
	 * @param columnIndex the index of the column to remove
	 * @return the new Matrix
	 */
	public static Matrix removeColumn(Matrix a, int columnIndex) {
		var arr = new double[a.numRows()][a.numColumns()-1];
		
		// declare column iterator for the new array
		int l = 0;
		
		// assign values to new array
		for(int i=0; i<a.numRows(); i++) {
			l=0;
			for(int j=0; j<a.numColumns(); j++) {
				if(j != columnIndex) {
					arr[i][l] = a.get(i, j);
					++l;
				}
			}
			
		}
		
		return new Matrix(arr);
	}
	
	/**
	 * Remove an entire row from a Matrix
	 * @param a the Matrix to remove the row from
	 * @param rowIndex the index of the row to remove
	 * @return new Matrix
	 */
	public static Matrix removeRow(Matrix a, int rowIndex) {
		var arr = new double[a.numRows()-1][a.numColumns()];
		
		// declare row iterator for the new array
		int k = 0;
		
		// assign values to new array
		for(int i=0; i<a.numRows(); i++) {
			if(i != rowIndex) {
				for(int j=0; j<a.numColumns(); j++) {
					arr[k][j] = a.get(i, j);
				}
				++k;
			} 
		}
		
		return new Matrix(arr);
	}

	public static Matrix power(Matrix a, int p) {
		Matrix b = new Matrix(a.getArray());
		for (int i = 0; i < p - 1; i++) {
			b = matrixMultiply(b, a);
		}
		return b;
	}
}
