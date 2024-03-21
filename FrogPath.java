public class FrogPath {
    private Pond pond;

    public FrogPath(String filename) {
        try {
            pond = new Pond(filename);
        } catch (InvalidMapException e) {
            System.out.println("Invalid map file: " + e.getMessage());
        }
    }

    public Hexagon findBest(Hexagon currCell) {
        ArrayUniquePriorityQueue<Hexagon> pq = new ArrayUniquePriorityQueue<>();
        Hexagon[] neighbors = pond.getNeighbors(currCell);

        for (Hexagon neighbor : neighbors) {
            if (neighbor != null && !neighbor.isMarked()) {
                double priority = computePriority(currCell, neighbor);
                pq.add(neighbor, priority);
            }
        }

        return pq.isEmpty() ? null : pq.peek();
    }

    private double computePriority(Hexagon currCell, Hexagon neighbor) {
        double priority = neighbor.getPriority();

        if (pond.isStraightLine(currCell, neighbor)) {
            priority += 0.5;
        } else {
            priority += 1.0;
        }

        return priority;
    }

    public String findPath() {
        Hexagon startCell = pond.getStart();
        Hexagon endCell = pond.getEnd();
        ArrayStack<Hexagon> stack = new ArrayStack<>();
        StringBuilder path = new StringBuilder();
        int fliesEaten = 0;

        stack.push(startCell);
        startCell.markInStack();

        while (!stack.isEmpty()) {
            Hexagon curr = stack.peek();
            path.append(curr.getID()).append(" ");

            if (curr == endCell) {
                break;
            }

            if (curr instanceof FoodHexagon) {
                FoodHexagon foodHex = (FoodHexagon) curr;
                fliesEaten += foodHex.getNumFlies();
                foodHex.removeFlies();
            }

            Hexagon next = findBest(curr);
            if (next == null) {
                stack.pop();
                curr.markOutStack();
            } else {
                stack.push(next);
                next.markInStack();
            }
        }

        if (stack.isEmpty()) {
            return "No solution";
        } else {
            path.append("ate ").append(fliesEaten).append(" flies");
            return path.toString();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java FrogPath <filename>");
            return;
        }

        String filename = args[0];
        FrogPath frogPath = new FrogPath(filename);
        System.out.println(frogPath.findPath());
    }
}
