
public interface ItemBag<T> {
	
	public void addItem (T item);
	
	public T removeItem ();
	
	public int getSize ();

}