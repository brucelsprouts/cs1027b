public class Abo {
    
    public static int rabo(int n) {
        if (n <= 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n % 2 == 0)
            return 1 + rabo(n / 2);
        else
            return 2 + rabo((n + 1) / 2);
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.print(rabo(i) + ", ");
        }
    }
}
