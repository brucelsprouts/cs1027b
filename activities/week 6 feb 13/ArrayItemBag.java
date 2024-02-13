
public class ArrayItemBag<T> implements ItemBag<T> {
	
	private T[] arr;
	private int num;
	
	public ArrayItemBag () {
		arr = (T[])(new Object[10]);
	}
	
	public void addItem (T item) {
		if (num == arr.length) expandCapacity();
		arr[num] = item;
		num++;
	}
	
	public T removeItem () {
		// Empty array.
		if (num == 0) return null;
		
		// Single item in list.
		if (num == 1) {
			T res = arr[0];
			num--;
			return res;
		}
		
		// Multiple items in list.
		int r = (int)(Math.random() * (num-1));		
		T item = arr[r];
		arr[r] = arr[num-1]; // Move last item into place where item is removed.
		num--;
		return item;
	}
	
	public int getSize () {
		return num;
	}
	
	private void expandCapacity () {
		T[] largerArr = (T[])(new Object[arr.length + 10]);
		for (int i = 0; i < num; i++) {
			largerArr[i] = arr[i];
		}
		arr = largerArr;
	}

}