
public class ClassB {

	private int x;
	private static int y;

	public static void main(String[] args) {
		ClassB obj = new ClassB();
		ClassB obj2 = new ClassB();

		obj.x = 10;
		y = 10;
		obj2.x = 20;
		obj2.y = 30;
		System.out.println(obj.x);
		System.out.println(obj.y);
		System.out.println(obj2.x);
		System.out.println(obj2.y);

	}

}
