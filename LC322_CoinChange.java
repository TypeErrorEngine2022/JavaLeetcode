import java.util.Arrays;

public class LC322_CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int min = Integer.MAX_VALUE;
        for (int coin: coins)
            min = Math.min(min, coin);

        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        memo[0] = 0;
        return dp(min, coins, amount, memo);
    }

    private static int dp(int minCoin, int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (amount < minCoin) return -1;
        if (memo[amount] != -2) return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin: coins) {
            int sub = dp(minCoin, coins, amount - coin, memo);
            if (sub == 0) return memo[amount] = ++sub;
            if (sub < 0) continue;
            res = Math.min(res, ++sub);
        }

        if (res == Integer.MAX_VALUE){
            return memo[amount] = -1;
        }

        return memo[amount] = res;
    }

    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        System.out.println(coinChange(coins, 6249));
    }
}
