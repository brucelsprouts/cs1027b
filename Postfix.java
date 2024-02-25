import java.util.Scanner;

public class Postfix {

	private ArrayStack<String> expStack;
	private PostfixEvaluator evaluator;
	
	public Postfix () {
		// ADD CODE HERE.
		
	}

	public void run () {
		String expression, action = "e";
		int result;

		try {
			Scanner in = new Scanner(System.in);
      
			do {
				if (action.equals("e")) {
					System.out.print("Enter a valid postfix expression:  ");
					expression = in.nextLine();
	
					result = evaluator.evaluate(expression.trim());
					System.out.println("The result is " + result);
	
					// ADD CODE HERE.
					
				
				} else if (action.equals("r")) {

					// ADD CODE HERE.



				}

				System.out.println("\nWhat do you want to do?");
				System.out.println("\tType 'e' if you want to evaluate another postfix expression.");
				System.out.println("\tType 'r' if you want to see the recent expressions.");
				System.out.println("\tType any other key to quit.");
				action = in.nextLine();
				System.out.println();
			} while (action.equalsIgnoreCase("e") || action.equalsIgnoreCase("r"));
    	} catch (Exception IOException) {
    		System.out.println("Input exception reported");
    	}

	}
	
	private void showRecent (int numToShow) {

		ArrayStack<String> tmp = new ArrayStack<String>();

		System.out.println("Recent Expressions:");
		
		
		// ADD CODE HERE.
		
		
		
		
		
		
		
		

	}
	
	
	public static void main (String[] args) {
		Postfix pf = new Postfix();
		pf.run();
	}
}
