
public class QueueTests {

	public static void main(String[] args) {

		QueueADT<String> Q = new LinkedQueue<String>();
		
		Q.enqueue("Apple");
		Q.enqueue("Orange");
		Q.enqueue("Lemon");
		Q.enqueue("Mango");
		Q.enqueue("Watermelon");

		System.out.println(Q);
		// Which fruit is at the front of the queue and which is at the rear?
		
		String res = Q.dequeue();
		System.out.println(Q);
		System.out.println("Removed " + res);
		
		res = Q.first();
		System.out.println(Q);
		System.out.println("First is " + res);
		
		Q.enqueue("Grapes");
		System.out.println(Q);
		
		while (!Q.isEmpty()) {
			System.out.println("- Dequeuing " + Q.dequeue());
		}
		
		System.out.println("Queue is now empty.");
		
		Q.dequeue(); // This will cause an exception - why?
		
	}

}
