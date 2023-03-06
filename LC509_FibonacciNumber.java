import java.util.ArrayList;
import java.util.Arrays;

public class LC509_FibonacciNumber {
    public static int fib(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        return dpFib(n, memo);
    }

    public static int dpFib(int n, int[] memo) {
        if (n == 0) return 0;
        if (memo[n] != 0) return memo[n];

        return memo[n] = dpFib(n - 1, memo) + dpFib(n - 2, memo);
    }

    public static void main(String[] args) {
        System.out.println(fib(30));
    }
}
