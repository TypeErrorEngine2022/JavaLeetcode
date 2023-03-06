import java.util.Arrays;

public class LC238_ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);

        // prefix sum for [0, i)
        int product = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            product *= nums[i];
            res[i + 1] *= product;
        }

        // suffix sum for [0, n - 1)
        product = 1;
        for (int i = nums.length - 1; i >= 1; i--) {
            product *= nums[i];
            res[i - 1] *= product;
        }

        return res;
    }
}
