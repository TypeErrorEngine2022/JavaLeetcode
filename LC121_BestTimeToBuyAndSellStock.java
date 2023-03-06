import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC121_BestTimeToBuyAndSellStock {
    // Must TLE
    public static int CompleteSearchMaxProfit(int[] prices) {
        int profit = 0;
        int ct = 0;
        for (int buyDay = 0; buyDay < prices.length; buyDay++) {
            for (int sellDay = buyDay + 1; sellDay < prices.length; sellDay++) {
                profit = Math.max(prices[sellDay] - prices[buyDay], profit);
                System.out.println("Buying day:" + buyDay + ' ' +
                                    "Selling day:" + sellDay);
            }
        }
        return profit;
    }

    public static int kadaneMaxProfit(int[] prices) {
        int leastPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        int todayProfit = 0;

        for (int p: prices) {
            leastPrice = Math.min(leastPrice, p);
            todayProfit = p - leastPrice;
            maxProfit = Math.max(maxProfit, todayProfit);
        }


        return maxProfit;
    }

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            profit = Math.max(price - min, profit);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        System.out.println(maxProfit(prices));
    }
}
