import java.util.Arrays;

public class LC309_BuySellStocksCooldown {
    public static int maxProfit(int[] prices) {
        // (index, buyState), buyState: allowBuying = 1
        int[][] dpTable = new int[prices.length][2];
        for (int[] arr: dpTable) {
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        return arrayDfs(prices, 0, 1, dpTable);
    }

    public static int fsmMaxProfit(int[] prices) {
        // invalid value, you cannot hold asset when day = -1;
        // also always good to have some asset (buy stocks) at day = 0;
        int hold = Integer.MIN_VALUE;
        int sold = 0;
        int rest = 0;

        // for each state, find the max value
        // local max each day => global max
        for (final int price: prices) {
            int prevSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prevSold);
        }

        // impossible for hold to be hold to be the largest
        // if holding some shares, sold it will have at least last day stock price as revenue
        // then max(hold, sold, rest) = max(sold, rest)
        return Math.max(sold, rest);
    }

    public static int arrayDfs(int[] prices, int index, int allowBuying, int[][] dpTable) {
        System.out.println("In dpTable[" + index + "][" + allowBuying + "]");

        if (index >= prices.length)
            return 0;

        if (dpTable[index][allowBuying] != Integer.MIN_VALUE)
            return dpTable[index][allowBuying];

        System.out.println("Querying coolDownProfit: dpTable[" + (index + 1) + "][" + allowBuying + "]");
        int coolDownProfit = arrayDfs(prices, index + 1, allowBuying, dpTable);
        if (allowBuying == 1) {
            System.out.println("Querying buyProfit: dpTable[" + (index + 1) + "][" + 0 + "]");
            int buyProfit = arrayDfs(prices, index + 1, 0, dpTable) -
                    prices[index];
            System.out.println("Updating dpTable[" + index + "][" + allowBuying + "]");
            dpTable[index][allowBuying] = Math.max(buyProfit, coolDownProfit);
        }
        else { // selling
            System.out.println("Querying sellProfit: dpTable[" + (index + 2) + "][" + 1 + "]");
            int sellProfit = arrayDfs(prices, index + 2, 1, dpTable)
                    + prices[index];
            System.out.println("Updating dpTable[" + index + "][" + allowBuying + "]");
            dpTable[index][allowBuying] = Math.max(sellProfit, coolDownProfit);
        }

        System.out.println("dpTable[" + index + "][" + allowBuying + "] = " + dpTable[index][allowBuying]);
        return dpTable[index][allowBuying];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(fsmMaxProfit(nums));
    }
}
