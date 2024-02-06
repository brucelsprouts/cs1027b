/**
 * Class that represents the grid of LetterCrush, its grids dimensions, gravity for letters, as well as other methods.
 * 
 * @author Bruce lin
 * Created on 2024/02/06
 */

public class LetterCrush {
    private char[][] grid;
    public static final char EMPTY = ' ';   

    /**
     * Constructor to initialize a grid's dimensions with given letters in it.
     * 
     * @param   width   Width of grid.
     * @param   height  Height of grid.
     * @param   initial Initial list of letters being put into grid.
     */
    public LetterCrush(int width, int height, String initial) {
        grid = new char[height][width]; //Initialize grid.
        int index = 0;                  //Index of letter in initial

        //Places letters in grid from the top left of the grid to right,repeating layer by layer till there are no more letters in initial.
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (index < initial.length()) { //Places letter in grid if the initial string contains more letters.
                    grid[row][col] = initial.charAt(index++);
                } else {    //Creates empty space on grid when letters run out.
                    grid[row][col] = EMPTY; 
                }
            }
        }
    }   

    /**
     * Returns a string of letterCrushes grid and letters within it.
     * 
     * @return String representation of letterCrushes grid and letters within it.
     */
    public String toString() {
        String returnString = "LetterCrush\n";  //Return String
    
        for (int row = 0; row < grid.length; row++) {   //Iterates through each row.
            returnString += "|";
            for (int col = 0; col < grid[row].length; col++) {  //Displays letter for each column in row.
                returnString += grid[row][col];
            }
            returnString += "|" + row + "\n"; //Adds row numbers.
        }

        //Format column numbers.
        returnString += "+";
        for (int col = 0; col < grid[0].length; col++) {
            returnString += col;
        }
        returnString += "+";

        return returnString;
    }
    
    /**
     * Determines wether the grid of letterCrush is stable or not.
     * 
     * @return True if the grid is stable and false if it isn't.
     */
    public boolean isStable() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] != EMPTY && grid[row + 1][col] == EMPTY) {   //If the grid under a grid with a letter is empty, return false.
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Moves letters down to fill empty spaces
     */
    public void applyGravity() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] != EMPTY && grid[row + 1][col] == EMPTY) {   //If the grid under a grid with a letter is empty, drop the letter.
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = EMPTY;
                }
            }
        }
    }

    /**
     * Validates that the line in in the grid then replace it with EMPTY and returns true.
     * 
     * @param theLine   Line object with start and end coordinates.
     * @return True if the line is valid which then EMPTIES it, and false if the line isn't valid.
     */
    public boolean remove(Line theLine) {
        int rows = grid.length;             //Number of rows in grid.
        int cols = grid[0].length;          //Number of columns in grid.
        int[] start = theLine.getStart();   //Start coordinates of line.
        int[] end = theLine.getEnd();       //End coordinates of line.

        //Validates that line is on grid.
        if (start[0] < 0 || start[0] >= rows || start[1] < 0 || start[1] >= cols || end[0] < 0 || end[0] >= rows || end[1] < 0 || end[1] >= cols) {
            return false;
        }

        //Removes line from grid with EMPTY space.
        for (int row = start[0]; row <= end[0]; row++) {
            for (int col = start[1]; col <= end[1]; col++) {
                grid[row][col] = EMPTY;
            }
        }
        return true;
    }

    /**
     * Returns a string of letterCrushes grid and letters within it, with theLine being in lowercase.
     * 
     * @param theLine   Line of letters that will be lowercase.
     * @return String representation of letterCrushes grid and letters within it, with theLine being in lowercase.
     */
    public String toString(Line theLine) {
        String returnString = "CrushLine\n";    //Return string.
    
        for (int row = 0; row < grid.length; row++) {   //Iterates through each row.
            returnString += "|";
            for (int col = 0; col < grid[row].length; col++) {  //Displays letter for each column in row.
                if (theLine.inLine(row, col)) {     //Make letter lower case if it is in theLine.
                    returnString += Character.toLowerCase(grid[row][col]);
                } else {
                    returnString += grid[row][col];
                }
            }
            returnString += "|" + row + "\n"; //Adds row numbers.
        }
    
        //Format column numbers.
        returnString += "+";
        for (int col = 0; col < grid[0].length; col++) {
            returnString += col;
        }
        returnString += "+";  

        return returnString;
    }

    /**
     * Returns the longest line on the grid, and null if there isn't a line longer than 3 letters.
     * If there are multiple lines with the same length, it returns the first encountered.
     * 
     * @return The longest line on the grid, and null if there isn't a line longer than 3 letters.
     */
    public Line longestLine() {
        Line longestLine = null;    //Longest line to null.
        int largest = 0;            //Counter for the length of the line.
    
        //Scan rows from bottom to top.
        for (int row = grid.length - 1; row >= 0; row--) {
            char current = grid[row][0];    //Character at the start of the row.
            int letterCount = 1;            //Counter for letters in the line.
    
            //Iterate through the columns within the row.
            for (int col = 1; col < grid[row].length; col++) {
                if (grid[row][col] == current && current != EMPTY) {
                    letterCount++;    //Increment letter count if the current character matches the previous one.
    
                    //If the current letter count is greater than the largest, make it the longest line.
                    if (letterCount > largest) {
                        largest = letterCount;    //Updates the largest length.
                        //Create a new Line object representing the longest line in the row.
                        longestLine = new Line(row, col - letterCount + 1, true, letterCount);
                    }
                } else {
                    //If the current character is different from the previous one, reset the letter count.
                    current = grid[row][col];
                    letterCount = 1;
                }
            }
        }
    
        //Scan columns from left to right.
        for (int col = 0; col < grid[0].length; col++) {
            char current = grid[grid.length - 1][col];  //Character at the bottom of the column.
            int letterCount = 1;                        //Counter for letters in the line.
    
            //Iterate through the rows within the column.
            for (int row = grid.length - 2; row >= 0; row--) {
                if (grid[row][col] == current && current != EMPTY) {
                    letterCount++;    //Increment the letter count if the current character matches the previous one.
    
                    //If the current letter count is greater than the largest, update the longest line.
                    if (letterCount > largest) {
                        largest = letterCount;    //Update the largest length.
                        //Create a new Line object representing the longest line in the column.
                        longestLine = new Line(row, col, false, letterCount);
                    }
                } else {
                    //If the current character is different from the previous one, reset the letter count.
                    current = grid[row][col];
                    letterCount = 1;
                }
            }
        }
        
        //If the longest line has at least 3 letters, return it, if not return null.
        if (largest >= 3) {
            return longestLine;
        } else {
            return null;
        }
    }

    /**
     * Cascades the longest line on the grid by removing the longest line and dropping letters, and repeating the process till there is no longest line.
     */
    public void cascade() {
        Line longestLine = longestLine();   //Longest line in the grid.

        //While there is a longest line remove it until there isn't a longest line in the grid.
        while (longestLine != null) {  
            remove(longestLine);
            while (isStable() != true) {
                applyGravity();
            }
            longestLine = longestLine();
        }
    }
}