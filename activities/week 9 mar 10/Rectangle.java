
public class Rectangle implements Comparable<Rectangle> {
	
	private int width, height;
	
	public Rectangle (int w, int h) {
		width = w;
		height = h;
	}
	
	public int area () {
		return width * height;
	}
	
	public String toString () {
		return "Rect " + width + " x " + height;
	}

	public int compareTo(Rectangle other) {
		if (area() < other.area())  return -1;
		else if (area() > other.area()) return 1;
		else return 0;
	}

}
