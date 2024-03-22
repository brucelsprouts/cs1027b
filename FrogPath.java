/**
 * Determine the path that Freddy the Frog must follow to go from the starting cell to the end cell.
 * 
 * @author Bruce Lin
 */
public class FrogPath {
    private Pond pond;

    /**
     * Constructs a FrogPath object with the specified filename representing the pond.
     *
     * @param filename The name of the file containing the pond configuration.
     * @throws RuntimeException if initialization fails.
     */
    public FrogPath(String filename) {
        try {
            pond = new Pond(filename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize FrogPath with filename: " + filename, e);
        }
    }

    /**
     * Finds the best neighboring cell for the frog to move to from the current cell.
     *
     * @param currCell The current cell the frog is in.
     * @return The best neighboring cell for the frog to move to, or null if no viable moves are possible.
     */
    public Hexagon findBest(Hexagon currCell) {
        Hexagon[] hexagons = new Hexagon[12];
        double[] priorities = new double[12];
        int count = 0;

        // Determine the best neighboring cell options based on priority
        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbour = currCell.getNeighbour(i);
                if (neighbour != null && !neighbour.isMarked()) {
                    double priority = calculatePriority(neighbour, currCell, false, false);
                    hexagons[count] = neighbour;
                    priorities[count] = priority;
                    count++;
                }
            } catch (InvalidNeighbourIndexException e) {
                // Ignore invalid neighbor index exceptions
            }
        }

        // Check special conditions for lily pad cells
        if (currCell.isLilyPadCell()) {
            for (int i = 0; i < 6; i++) {
                try {
                    Hexagon adjacent = currCell.getNeighbour(i);
                    if (adjacent != null) {
                        Hexagon twoAway = adjacent.getNeighbour((i + 3) % 6);
                        if (twoAway != null && !twoAway.isMarked() && twoAway != currCell) {
                            double priority = calculatePriority(twoAway, currCell, true, false);
                            hexagons[count] = twoAway;
                            priorities[count] = priority;
                            count++;
                        }
                    }
                } catch (InvalidNeighbourIndexException e) {
                    // Ignore invalid neighbor index exceptions
                }
            }
        }

        // Find the cell with the lowest priority
        Hexagon bestHexagon = null;
        double lowestPriority = Double.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (priorities[i] < lowestPriority) {
                bestHexagon = hexagons[i];
                lowestPriority = priorities[i];
            }
        }

        return bestHexagon;
    }

    /**
     * Calculates the priority of moving to a specific cell based on various factors.
     *
     * @param cell      The cell to calculate priority for.
     * @param currCell  The current cell the frog is in.
     * @param isTwoAway Indicates if the cell is two hops away from the current cell.
     * @param isDiagonal Indicates if the movement to the cell is diagonal.
     * @return The priority value for moving to the specified cell.
     */
    private double calculatePriority(Hexagon cell, Hexagon currCell, boolean isTwoAway, boolean isDiagonal) {
        double priority = 10;

        // Adjust priority based on cell type and proximity
        if (cell.isMudCell() || cell.isAlligator()) {
            return Double.MAX_VALUE; // Unreachable or dangerous cell
        } else if (cell instanceof FoodHexagon) {
            FoodHexagon foodCell = (FoodHexagon) cell;
            priority = 3.0 - foodCell.getNumFlies();
        } else if (cell.isEnd()) {
            priority = 1.0; // End cell is the highest priority
        } else if (cell.isLilyPadCell()) {
            priority = 4.0;
        } else if (cell.isReedsCell()) {
            priority = 5.0;
        } else if (cell.isWaterCell()) {
            priority = 6.0;
        }

        // Adjust priority for cells that are two hops away
        if (isTwoAway) {
            priority += isDiagonal ? 1.0 : 0.5;
        }

        // Check for alligators nearby, avoid if not on reeds
        for (int i = 0; i < 6; i++) {
            try {
                Hexagon neighbor = cell.getNeighbour(i);
                if (neighbor != null && neighbor.isAlligator() && !cell.isReedsCell()) {
                    return Double.MAX_VALUE; // Avoid alligators unless on reeds
                }
            } catch (InvalidNeighbourIndexException e) {
                // Ignore invalid neighbor index exceptions
            }
        }
        return priority;
    }

    /**
     * Finds the optimal path for the frog to reach the end of the pond while eating flies.
     *
     * @return A string representing the path the frog took and the number of flies eaten.
     */
    public String findPath() {
        ArrayStack<Hexagon> pathStack = new ArrayStack<>();
        int fliesEaten = 0;
        StringBuilder path = new StringBuilder();

        Hexagon startCell = pond.getStart();
        pathStack.push(startCell);
        startCell.markInStack();

        // Traverse the pond to find the optimal path
        while (!pathStack.isEmpty()) {
            Hexagon currCell = pathStack.peek();
            path.append(currCell.getID()).append(" ");

            if (currCell.isEnd()) {
                break; // Path found, exit loop
            }

            // Handle food cells
            if (currCell instanceof FoodHexagon) {
                FoodHexagon foodCell = (FoodHexagon) currCell;
                fliesEaten += foodCell.getNumFlies();
                foodCell.removeFlies();
            }

            Hexagon nextCell = findBest(currCell);

            if (nextCell == null) {
                // No valid moves, backtrack
                pathStack.pop().markOutStack();
            } else {
                // Move to the next valid cell
                pathStack.push(nextCell);
                nextCell.markInStack();
            }
        }

        // Check if a solution was found
        if (pathStack.isEmpty()) {
            return "No solution"; // Unable to find a path to the end
        } else {
            return path.append("ate ").append(fliesEaten).append(" flies").toString(); // Path with flies eaten
        }
    }

    /**
     * Main method to run the FrogPath algorithm from command line.
     *
     * @param args Command-line arguments (expects pond_file_name).
     */
    public static void main(String[] args) {
        // Ensure exactly one command-line argument is provided
        if (args.length != 1) {
            System.out.println("Usage: java FrogPath <pond_file_name>");
            return;
        }
        String filename = args[0];
        FrogPath fp = new FrogPath(filename);
        String result = fp.findPath();
        System.out.println(result);
    }
}