public class Line {
    private int[] start;
    private int[] end;

    public Line(int row, int col, boolean horizontal, int length) {
        start = new int[]{row, col};

        // Calculates the end point based on the length and the direction (Horizontal or Vertical)
        if (horizontal) {
            end = new int[]{row, col + length - 1};
        } else {
            end = new int[]{row + length - 1, col};
        }
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }

    public int length() {
        if (start[0] == end[0]) { // Horizontal line
            return end[1] - start[1] + 1;
        } else if (start[1] == end[1]) { // Vertical line
            return end[0] - start[0] + 1;
        } else {
            return 0; // Exceptions
        }
    }

    public boolean isHorizontal() {
        return start[0] == end[0];
    }

    public boolean inLine(int row, int col) {
        if (isHorizontal()) {
            // Check if the column is within the range of the line
            return start[0] == row && start[1] <= col && col <= end[1];
        } else {
            // Check if the row is within the range of the line
            return start[1] == col && start[0] <= row && row <= end[0];
        }
    }

    public String toString() {
        return String.format("Line:[%d,%d]->[%d,%d]", start[0], start[1], end[0], end[1]);
    }
}
