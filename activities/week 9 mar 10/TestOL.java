
public class TestOL {

	public static void main(String[] args) {
		
		ArrayOrderedList<Rectangle> ol = new ArrayOrderedList<Rectangle>();
		
		ol.add(new Rectangle(7,4)); // area is 28
		ol.add(new Rectangle(2,9)); // area is 18
		ol.add(new Rectangle(8,7)); // area is 56
		ol.add(new Rectangle(2,5)); // area is 10
		ol.add(new Rectangle(5,6)); // area is 30
		
		System.out.println(ol);

	}

}
