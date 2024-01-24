
public class ClassC {

	static int counter = 0;

	public ClassC() {
		counter += 1;
	}

	public static void main (String[] args) {
		
		ClassC obj = null;
		int N = (int)(Math.random() * 100);
		
		for (int i = 0; i < N; i++) {
			obj = new ClassC();
		}
		
		System.out.println(N);
		System.out.println(obj.counter);
	}
	

}
