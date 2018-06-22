# LightLinAlg
LightLinAlg is a lightweight library for performing basic linear algebra operations

Example Usage:

public class Test {

	public static void main(String[] args) {
		// creating matrices
		var a = new Matrix(new double[][] {
			{1, 4, 1, 0},
			{2, 0, 1, 0},
			{0, 1, 0, 5},
			{1, 1, -1, 0}
		});
		
		var b = new Matrix(new double[][] {
			{2, 0, 1, 0},
			{1, 1, 1, 0},
			{0, 1, 0, 3},
			{1, 0, -1, 1}
		});
		
		// Multiplying matrices
		var c = LinearAlgebra.matrixMultiply(a, b);
		a.prettyPrint();
		
		// inverting matrices
		var d = LinearAlgebra.inverse(a);
		d.prettyPrint();
	}
}
