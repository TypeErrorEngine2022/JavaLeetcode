import java.util.Arrays;

public class LC169_MajorityElement {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int ct = 1;
        for (int i = 1; i < nums.length; i++) {
            if (ct == 0) {
                major = nums[i];
            }
            if (nums[i] == major) {
                ct++;
            }
            else {
                // non-major number will cancel out each other
                // also n/2 of major number will cancel out with non-major number
                ct--;
            }
        }
        // if there are n/2 + k major number, ct will be 2k
        return major;
    }
}
