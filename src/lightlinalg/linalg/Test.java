package lightlinalg.linalg;

public class Test {

	public static void main(String[] args) {
		var a = new Matrix(new double[][] {
				{2.0, 3.0, -4.0},
				{-2.0, 1.0, 6.0}
		});
		
		a.prettyPrint();
		
		var b = new Matrix(new double[][] {
			{2.0, 4.0},
			{-2.0, 1.0},
			{3.0, 2.0}
		});
	
		b.prettyPrint();
		
		var c = LinearAlgebra.matrixMultiply(a, b);
		c.prettyPrint();
		
		var d = LinearAlgebra.matrixMultiply(b, a);
		d.prettyPrint();
	}

}
