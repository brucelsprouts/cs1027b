public class Coin {
	
	public static void main(String[] args) {

		Coin myCoin;

		myCoin = new Coin();
		System.out.println(myCoin.toString());
	}

	private final int HEADS = 0;
	private int face;

	public Coin() {
		flip();
	}

	public void flip() {
		face = (int) (Math.random() * 2);
	}

	public boolean isHeads() {
		return (face == HEADS);
	}

	public String toString() {
		String faceName;
		if (face == HEADS) {
			faceName = "Heads";
		} else {
			faceName = "Tails";
		}
		return faceName;
	}
}