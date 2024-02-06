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
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] != EMPTY && grid[row + 1][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void applyGravity() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] != EMPTY && grid[row + 1][col] == EMPTY) {
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = EMPTY;
                }
            }
        }
    }

    public boolean remove(Line theLine) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] start = theLine.getStart();
        int[] end = theLine.getEnd();

        if (start[0] < 0 || start[0] >= rows || start[1] < 0 || start[1] >= cols || end[0] < 0 || end[0] >= rows || end[1] < 0 || end[1] >= cols) {
            return false;
        }

        for (int row = start[0]; row <= end[0]; row++) {
            for (int col = start[1]; col <= end[1]; col++) {
                grid[row][col] = EMPTY;
            }
        }
        return true;
    }

    public String toString(Line theLine) {
        String returnString = "CrushLine\n";
    
        for (int row = 0; row < grid.length; row++) {
            returnString += "|";
            for (int col = 0; col < grid[row].length; col++) {
                if (theLine.inLine(row, col)) {
                    returnString += Character.toLowerCase(grid[row][col]);
                } else {
                    returnString += grid[row][col];
                }
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

    public Line longestLine() {
        Line longestLine = null;
        int largest = 0;

        for (int i = grid.length - 1; i >= 0; i--) {
            char letter = grid[i][0];
            int adjacent = 1;

            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j] == letter && letter != EMPTY) {
                    adjacent++;
                    if (adjacent > largest) {
                        largest = adjacent;
                        longestLine = new Line(i, j - adjacent + 1, true, adjacent);
                    }
                } else {
                    letter = grid[i][j];
                    adjacent = 1;
                }
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            char letter = grid[grid.length - 1][j];
            int adjacent = 1;

            for (int i = grid.length - 2; i >= 0; i--) {
                if (grid[i][j] == letter && letter != EMPTY) {
                    adjacent++;
                    if (adjacent > largest) {
                        largest = adjacent;
                        longestLine = new Line(i, j, false, adjacent);
                    }
                } else {
                    letter = grid[i][j];
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
        Line longestLine = longestLine();

        while (longestLine != null && longestLine.length() >= 3) {
            remove(longestLine);
            applyGravity();
            longestLine = longestLine();
        }
    }
}