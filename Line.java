/**
 * Class that represents a line's start and end coordinates, with methods that can get, set, as well as other methods.
 * 
 * @author Bruce lin
 * Created on 2024/02/06
 */

public class Line {
    private int[] start, end;

    /**
     * Constructor to initialize a Line object with starting coordinates, direction of line, and its length.
     * 
     * @param row           Starting row of line.
     * @param col           Starting column of line.
     * @param horizontal    Vertical or horizontal line.
     * @param length        Length of line.
     */
    public Line(int row, int col, boolean horizontal, int length) {
        start = new int[]{row, col};    //Start coordinate.

        if (horizontal) {                               //Horizontal line.
            end = new int[]{row, col + length - 1};
        } else {                                        //Vertical line.
            end = new int[]{row + length - 1, col};
        }
    }

    /**
     * Gets the starting coordinates of the line.
     * 
     * @return Start coordinates of line.
     */
    public int[] getStart() {
        return start;
    }

    /**
     * Gets the ending coordinates of the line.
     * 
     * @return End coordinates of line.
     */
    public int[] getEnd() {
        return end;
    }

    /**
     * Returns the length of a line.
     * 
     * @return Length of a line.
     */
    public int length() {
        if (start[0] == end[0]) {           //Horizontal line.
            return end[1] - start[1] + 1;
        } else if (start[1] == end[1]) {    //Vertical Line.
            return end[0] - start[0] + 1;
        } else {                            //No line.
            return 0;
        }
    }

    /**
     * Determines and returns if the line is horizontal or vertical
     * 
     * @return True if line is horizontal and false if it is vertical.
     */
    public boolean isHorizontal() {
        return start[0] == end[0];
    }

    /**
     * Returns true if the position of the grid at the row and column is contained in the line
     * 
     * @param row   Row coordinate on grid.
     * @param col   Column coordinate on grid.
     * @return True if line is vertical or horizontal
     */
    public boolean inLine(int row, int col) {
        if (isHorizontal()) {                                               //Horizontal line.
            return start[0] == row && start[1] <= col && col <= end[1];
        } else {                                                            //Vertical line.
            return start[1] == col && start[0] <= row && row <= end[0];
        }
    }

    /**
     * Returns a string of the end points of the line.
     * 
     * @return String with the end points of the line.
     */
    public String toString() {
        return "Line:[" + start[0] + "," + start[1] + "]->[" + end[0] + "," + end[1] + "]";
    }
}