public class LC70_ClimbingStairs {
    public int climbStairs(int n) {
        int[] dpTable = new int[n + 1];
        dpTable[1] = 1;
        dpTable[2] = 1;

        for (int i = 3; i <= n; i++) {
            dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
        }

        return dpTable[n];
    }
}
