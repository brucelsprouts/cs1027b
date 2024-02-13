
public class TestItemBags {

	public static void main (String[] args) {
		
		ItemBag<String> strBag;
		
		// Un-comment one of the following two lines and comment out the other.
		strBag = new ArrayItemBag<String>();
		//strBag = new LinkedListItemBag<String>();
		
		
		
		strBag.addItem("A");
		strBag.addItem("B");
		strBag.addItem("C");
		strBag.addItem("D");
		strBag.addItem("E");
		
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		System.out.println(strBag.removeItem() + "  " + strBag.getSize());
		
	}
	
}