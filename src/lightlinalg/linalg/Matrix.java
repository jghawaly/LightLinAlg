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
	
	public void scale(double s) {
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

}
