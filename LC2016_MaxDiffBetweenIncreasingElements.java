public class LC2016_MaxDiffBetweenIncreasingElements {
    public static int maximumDifference(int[] nums) {
        int minNum = Integer.MAX_VALUE;
        int currentDiff = 0;
        int maxDiff = 0;

        for (int num: nums) {
            minNum = Math.min(minNum, num);
            currentDiff = num - minNum;
            maxDiff = Math.max(maxDiff, currentDiff);
        }

        return (maxDiff > 0)? maxDiff : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,0,2};
        System.out.println(maximumDifference(nums));
    }
}
