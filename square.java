import java.util.Scanner;

public class square {
    static int[] sq;

    public static int square(int n) {
        if (n == 0) return sq[n] = 0;
        if (n == 1) return sq[n] = 1;
        if (sq[n] != 0) return sq[n];

        sq[n] = 2 * square(n - 1) - square(n - 2) + 2;

        return sq[n];
    }

    public static void main(String[] args) {
        sq = new int[100000];
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n >= 0) {
                System.out.println(square(n));
            }
            else break;
        }
    }
}
