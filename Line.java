public class Line {
    private int[] start, end;

    public Line(int row, int col, boolean horizontal, int length) {
        start = new int[]{row, col};

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
        if (start[0] == end[0]) {
            return end[1] - start[1] + 1;
        } else if (start[1] == end[1]) {
            return end[0] - start[0] + 1;
        } else {
            return 0;
        }
    }

    public boolean isHorizontal() {
        return start[0] == end[0];
    }

    public boolean inLine(int row, int col) {
        if (isHorizontal()) {
            return start[0] == row && start[1] <= col && col <= end[1];
        } else {
            return start[1] == col && start[0] <= row && row <= end[0];
        }
    }

    public String toString() {
        return "Line:[" + start[0] + "," + start[1] + "]->[" + end[0] + "," + end[1] + "]";
    }
}