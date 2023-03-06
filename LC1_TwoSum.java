import java.util.Arrays;

public class LC1_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int l = 0; int r = 0;
        for (l = 0; l < nums.length; l++) {
            for (r = l + 1; r < nums.length; r++) {
                if (nums[l] + nums[r] == target)
                    return new int[]{l, r};
            }
        }

        return new int[]{l, r};
    }

    public static void main(String[] args) {
        int[] nums = {3, 3};
        System.out.println(Arrays.toString(twoSum(nums, 6)));
    }
}
