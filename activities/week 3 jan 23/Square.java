/**
 * Square.java: a class that models a square
 *  - an example of Inheritance
 */

 public class Square extends Rectangle {

    // no new attributes need be introduced
    private double diagonal;
  
    public Square(int s) {
      // call the 2 variable superclass constructor
      super(s, s);
      diagonal = (double) s * 1.4142;
    }
  
    public int getSide() {
      return getWidth();
    }
  
    public String toString() {
      return "Square: Side(" + getSide() + ")";
    }   

    public void main(String[] args) {
      Rectangle r = new Square (5);
      System.out.println(r.getSide());

    }
  }
  