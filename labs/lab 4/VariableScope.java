public class VariableScope {

    private static int a, b;

    public static void m1(int a) {
        a = 5;
        b = 3;
    }

    public static void m2() {
        a = 2;

        for (int b = 0; b < 3; b++) {
            int a = b * 2;
        }
    }

    public static void main(String[] args) {
        a = 4;
        int b = 9;

        m1(a);

        m2();

        System.out.println(a); // Print statement 1
        System.out.println(b); // Print statement 2
        System.out.println(VariableScope.b); // Print statement 3
    }

}
