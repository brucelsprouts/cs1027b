
public class LinkedListItemBag<T> implements ItemBag<T> {

	private LinearNode<T> front;
	private int num;
	
	public void addItem(T item) {
		LinearNode<T> newNode = new LinearNode<T>(item);
		
		// Empty list.
		if (num == 0) front = newNode;
				
		// One or more items in list.
		else {
			newNode.setNext(front);
			front = newNode;
		}
		num++;
	}

	public T removeItem() {
		
		// Empty list.
		if (num == 0) return null;
		
		// Single item in list.
		if (num == 1) {
			T res = front.getElement();
			front = null;
			num--;
			return res;
		}
		
		// Multiple items in list.
		int r = (int)(Math.random() * (num-1));
		LinearNode<T> curr = front, prev = null;
		for (int i = 0; i < r; i++) {
			prev = curr;
			curr = curr.getNext();
		}
		
		if (curr == front) {
			// Random node is at the front.
			front = front.getNext();
			
		} else {
			// Random node is at the rear or in the middle of the list.
			prev.setNext(curr.getNext());
		}
		
		num--;
		return curr.getElement();
	}

	
	
	public int getSize() {
		return num;
	}
	

}