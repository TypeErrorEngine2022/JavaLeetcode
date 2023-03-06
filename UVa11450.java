import java.util.Arrays;
import java.util.Scanner;


public class UVa11450 {
    static final int MAX_gm = 30;
    static final int MAX_M = 210;

    static int[][] price;
    static boolean[][] reachable;

    public static void main(String[] args) {
        int TC;
        price = new int[MAX_gm][MAX_M];
        reachable = new boolean[2][MAX_M];

        Scanner scanner = new Scanner(System.in);
        TC = scanner.nextInt();

        while (TC-- > 0) {
            int M, C;
            M = scanner.nextInt();
            C = scanner.nextInt();

            for (int g = 0; g < C; ++g) {
                price[g][0] = scanner.nextInt();
                for (int k = 1; k <= price[g][0]; ++k) {
                    price[g][k] = scanner.nextInt();
                }
            }

            for (boolean[] r: reachable) {
                Arrays.fill(r, false);
            }

            for (int k = 1; k <= price[0][0]; ++k) {
                if (M - price[0][k] >= 0) {
                    reachable[0][M - price[0][k]] = true;
                }
            }

            int money;
            int cur = 1;
            for (int g = 1; g < C; ++g) {
                Arrays.fill(reachable[cur], false);
                for (money = 0; money < M; money++) {
                    if (reachable[cur ^ 1][money]) {
                        for (int k = 1; k <= price[g][0]; ++k) {
                            if (money - price[g][k] >= 0) {
                                reachable[cur][money - price[g][k]] = true;
                            }
                        }
                    }
                    cur ^= 1;
                }
            }

            for (money = 0; money <= M && !reachable[cur ^ 1][money]; ++money);

            if (money == M + 1) System.out.println("no solution");
            else System.out.println(M - money);
        }
    }
}
