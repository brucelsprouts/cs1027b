public class DebuggingExercise1 {

    private static final int NUM_ITEMS = 10; // Fixed: Correctly declared as final

	public static void m1(int[] arr, int k) {
		// Populate array with random numbers.
		int r = (int) (Math.random() * 25);
		arr[k] = r; // Assign value to arr[k]
		k++; // Increment k
	}


    public static boolean continueLooping(int k) {
        // Determine if the loop should continue based on the value of k.
        return k < NUM_ITEMS; // Fixed: Added return statement
    }

    public static int findSmallest(int[] arr) {
        // Search for smallest value and return its index.
        int smallestValue = 50;
        int smallestIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallestValue) {
                smallestValue = arr[i]; // Fixed: Added curly brackets for the if statement
                smallestIndex = i;
            }
        }

        return smallestIndex;
    }

    public static void main(String[] args) {

        // NUM_ITEMS = 20; // Removed: Cannot reassign final variable

        boolean done = false;
        int[] arr = new int[NUM_ITEMS];
        int k = 0;

        // Populate array with random numbers.
        while (continueLooping(k)) {
            m1(arr, k);
            k++; // Added: Increment k after m1 call
        }

        int smallestIndex = findSmallest(arr);
        int smallestValue = arr[smallestIndex];

        // Print out smallest value and its index.
        System.out.println("Smallest value is: " + smallestValue);
        System.out.println("Found at index: " + smallestIndex);
        System.out.println();

        // Print out array values.
        for (int i = 0; i < NUM_ITEMS; i++) { // Changed variable name to avoid conflict
            System.out.print(arr[i] + "  ");
        }
    }
}