# LightLinAlg
LightLinAlg is a lightweight library for performing basic linear algebra operations

Example Usage:

public class Test {

	public static void main(String[] args) {
		var a = new Matrix(new double[][] {
			{2, 3, 4, 1},
			{0, 1, 1, -3},
			{0, 0, 4, 5},
			{1, 0, -1, 0}
		});
		
		var b = new Matrix(new double[][] {
			{1, 3, 3, 0},
			{6, 1, 11, 5},
			{1, 0, 2, -5},
			{8, 0, -4, 1}
		});
		
		var c = LinearAlgebra.matrixMultiply(a, b);
		
		a.prettyPrint();
		b.prettyPrint();
		c.prettyPrint();
		System.out.println(LinearAlgebra.det(a));
		System.out.println(LinearAlgebra.det(b));
		System.out.println(LinearAlgebra.det(c));
		
	}

}
