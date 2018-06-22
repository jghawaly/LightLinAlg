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

public class Matrix {
	
	// This is the 2D double array that the Matrix class is wrapped around
	private double[][] array;
	
	/**
	 * Initialize a Matrix object with a 2D double array
	 * @param arr 2D double array
	 */
	public Matrix(double[][] arr) {
		this.array = arr;
		
		// We want to check if every row has the same number of columns. If it doesn't, some
		// errors can occur, the severity of which depends on the methods called on the Matrix
		int l = 0;
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				if(i==0) {
					l = arr[i].length;
				}
				else {
					if(arr[i].length != l) {
						throw new IllegalArgumentException("Every row must be of the same length!");
					}
				}
			}
		}
	}
	
	/**
	 * Initialize a Matrix object with a 1D double array, which will be turned into a 2D double array
	 * @param arr 1D double array
	 */
	public Matrix(double[] arr) {
		this.array = new double[][] {arr};
	}
	
	/**
	 * Get the number of rows in the Matrix
	 * @return number of rows
	 */
	public int numRows() {
		return array.length;
	}
	
	/**
	 * Get the number of columns in the Matrix
	 * @return number of columns
	 */
	public int numColumns() {
		return array[0].length;
	}
	
	/**
	 * Return the double at the requested location
	 * @param i row index
	 * @param j column index
	 * @return double at index
	 */
	public double get(int i, int j) {
		return array[i][j];
	}
	
	/**
	 * Set the double at the requested location
	 * @param i row index
	 * @param j column index
	 * @param value the double to set at that location
	 */
	public void set(int i, int j, double value) {
		array[i][j] = value;
	}
	
	/**
	 * Get the row at the given index
	 * @param rowIndex Index of the row
	 * @return the row
	 */
	public double[] getRow(int rowIndex) {
		return array[rowIndex];
	}
	
	/** 
	 * Generates a copy of the requested column. Because the Matrix is stored as a 2D double array,
	 * there is no true "column" object like there is for rows
	 * @param columnIndex index of the column
	 * @return Copy of the column requested
	 */
	public double[] getColumnCopy(int columnIndex) {
		var columnCopy = new double[numRows()];
		for(int i=0; i<numRows(); i++) {
			columnCopy[i] = array[i][columnIndex];
		}
		return columnCopy;
	}
	
	/**
	 * Transpose the matrix, this changes the object permanently
	 */
	public void transpose() {
		double[][] arr = new double[numColumns()][numRows()];
		
		for(int i=0; i<numRows(); i++) {
			for(int j=0; j<numColumns(); j++) {
				arr[j][i] = array[i][j];
			}
		}
		
		this.array = arr;
	}
	
	/**
	 * Determines if this matrix is a square matrix
	 * @return true if square, false otherwise
	 */
	public boolean isSquare() {
		if(numRows() != numColumns()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Calculate the trace of this matrix, will throw an ArithmeticException if the matrix is not square
	 * @return trace of this matrix
	 */
	public double trace() {
		double trc = 0.0;
		if(!isSquare()) {
			throw new ArithmeticException("Matrix must be square in order to calculate trace()");
		}
		else {
			for(int i=0; i<numRows(); i++) {
				trc += get(i, i);
			} 
		}
		return trc;
	}
	
	/**
	 * Fills the diagonal of this matrix with the given value, will throw ArithmeticException if the matrix is not square
	 * @param x the value to fill into the diagonal
	 */
	public void fillDiagonal(double x) {
		if(!isSquare()) {
			throw new ArithmeticException("Matrix must be square in order to fill a diagonal");
		}
		else {
			for(int i=0; i<numRows(); i++) {
				set(i, i, x);
			} 
		}
	}
	
	/**
	 * Set every element in the matrix to the given value
	 * @param x value to fill the entire matrix with
	 */
	public void fillAll(double x) {
		for(int i=0; i<numRows(); i++) {
			for(int j=0; j<numColumns(); j++) {
				set(i, j, x);
			}
		}
	}
	
	/**
	 * Multiplies every element in this matrix by the given value
	 * @param s scalar value
	 */
	public void scaleAll(double s) {
		for(int i=0; i<numRows(); i++) {
			for(int j=0; j<numColumns(); j++) {
				array[i][j] = s*array[i][j];
			}
		}
	}
	
	/**
	 * Outputs a visual representation of a matrix to standard output
	 * @param mat the input matrix to visualize
	 */
	public void prettyPrint() {
		System.out.println("\n");
		for(int i=0;i<numRows();i++) {
			System.out.print("|    ");
			for(int j=0;j<numColumns();j++) {
				System.out.print(String.valueOf(this.array[i][j]) + "    ");
			}
			System.out.print("|\n");
		}
	}
	
	/**
	 * Get the Matrix as a 2D array
	 * @return 2D double array
	 */
	public double[][] getArray() {
		return array.clone();
	}

}
