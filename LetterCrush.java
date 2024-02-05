public class LetterCrush {
        private char[][] grid;
    public static final char EMPTY = ' ';   

    public LetterCrush(int width, int height, String initial) {
        grid = new char[height][width];
        int index = 0;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (index < initial.length()) {
                    grid[row][col] = initial.charAt(index++);
                } else {
                    grid[row][col] = EMPTY;
                }
            }
        }
    }   

    public String toString() {
        String returnString = "LetterCrush\n";
    
        for (int row = 0; row < grid.length; row++) {
            returnString += "|";
            for (int col = 0; col < grid[row].length; col++) {
                returnString += grid[row][col];
            }
            returnString += "|" + row + "\n";
        }
        
        returnString += "+";
        for (int col = 0; col < grid[0].length; col++) {
            returnString += col;
        }
        returnString += "+";
        return returnString;
    }
    
    public boolean isStable() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 1; row < grid.length; row++) {
                if (grid[row][col] != EMPTY && grid[row - 1][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void applyGravity() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 1; row >= 1; row--) {
                if (grid[row][col] == EMPTY && grid[row - 1][col] != EMPTY) {
                    grid[row][col] = grid[row - 1][col];
                    grid[row - 1][col] = EMPTY;
                }
            }
        }
    }


    public boolean remove(Line theLine) {
        if (!validLine(theLine)) {
            return false;
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (theLine.inLine(row, col)) {
                    grid[row][col] = EMPTY;
                }
            }
        }

        return true;
    }

    public String toString(Line theLine) {
        StringBuilder result = new StringBuilder("CrushLine\n");

        for (int row = 0; row < grid.length; row++) {
            result.append("|");
            for (int col = 0; col < grid[row].length; col++) {
                if (theLine.inLine(row, col)) {
                    result.append(Character.toLowerCase(grid[row][col]));
                } else {
                    result.append(grid[row][col]);
                }
            }
            result.append("|").append(row).append("\n");
        }
        result.append("+");

        for (int col = 0; col < grid[0].length; col++) {
            result.append(col);
        }
        result.append("+");

        return result.toString();
    }

    public Line longestLine() {
        Line longestLine = null;
        int largest = 0;

        for (int row = grid.length - 1; row >= 0; row--) {
            char letter = grid[row][0];
            int adjacent = 1;

            for (int col = 1; col < grid[row].length; col++) {
                if (grid[row][col] == letter && letter != EMPTY) {
                    adjacent++;

                    if (adjacent > largest) {
                        largest = adjacent;
                        longestLine = new Line(row, col - adjacent + 1, true, adjacent);
                    }
                } else {
                    letter = grid[row][col];
                    adjacent = 1;
                }
            }
        }

        for (int col = 0; col < grid[0].length; col++) {
            char letter = grid[grid.length - 1][col];
            int adjacent = 1;

            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] == letter && letter != EMPTY) {
                    adjacent++;

                    if (adjacent > largest) {
                        largest = adjacent;
                        longestLine = new Line(row - adjacent + 1, col, false, adjacent);
                    }
                } else {
                    letter = grid[row][col];
                    adjacent = 1;
                }
            }
        }

        if (largest >= 3) {
            return longestLine;
        } else {
            return null;
        }
    }

    public void cascade() {
        Line longest;
        
        do {
            longest = longestLine();
            if (longest != null) {
                remove(longest);
                applyGravity();
            }
        } while (longest != null && longest.length() >= 3);
    }

    private boolean validLine(Line theLine) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] start = theLine.getStart();
        int[] end = theLine.getEnd();

        return start[0] >= 0 && start[0] < rows && start[1] >= 0 && start[1] < cols &&
               end[0] >= 0 && end[0] < rows && end[1] >= 0 && end[1] < cols;
    }
}