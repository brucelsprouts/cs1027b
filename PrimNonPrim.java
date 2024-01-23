
public class PrimNonPrim {

	public static void main(String[] args) {

		// Defining primitive vs. non-primitive variables.
		
		int num1;
		Integer num2;
		
		num1 = 5;
		num2 = 5; // or num2 = new Integer(5);
		
		System.out.println(num1);
		System.out.println(num2);
		
		System.out.println(); // Blank line.
		
		// Default values of primitive vs. non-primitive types.
		
		int[] numArr1 = new int[1];
		Integer[] numArr2 = new Integer[1];
		
		System.out.println(numArr1[0]);
		System.out.println(numArr2[0]);
		
		System.out.println(); // Blank line.
		
		// Comparing variables or primitive vs. non-primitive types.
		
		int a = 5;
		int b = 5;
		Integer c = new Integer(5);
		Integer d = new Integer(5);
		
		if (a == b) System.out.println("a == b");
		if (c == d) System.out.println("c == d");
		if (c.equals(d)) System.out.println("c equals d");

		if (a.equals(b)) System.out.println("c equals d");
	}

}
