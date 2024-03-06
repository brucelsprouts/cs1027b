
public class Snail {

    public static final char icon = '@';
    private int position;
    private QueueADT<Integer> movePattern;

    public Snail(int[] pattern) {
        position = 0;
        movePattern = new DLQueue<>();

        for (int step : pattern) {
            movePattern.enqueue(step);
        }
    }

    public void move() {
        int currentMove = movePattern.dequeue();

        movePattern.enqueue(currentMove);

        position += currentMove;

        if (position > SnailRace.raceLength) {
            position = SnailRace.raceLength;
        }
    }

    public int getPosition() {
        return position;
    }

    public void display() {
        int dashesBefore = SnailRace.raceLength - position;
        int dashesAfter = position;

        for (int i = 0; i < dashesBefore; i++) {
            System.out.print("-");
        }

        System.out.print(icon);

        for (int i = 0; i < dashesAfter; i++) {
            System.out.print("-");
        }

        System.out.print("\n");
    }
}
