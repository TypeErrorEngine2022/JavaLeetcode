import java.util.Arrays;

public class LC33_SearchRotatedArray {
    public static int search(int[] nums, int target) {
        if (nums.length == 1){
            if (nums[0] == target)
                return 0;
            else
                return -1;
        }

        // not rotated
        if (nums[0] < nums[nums.length - 1]) {
            if (target < nums[0] || target > nums[nums.length - 1])
                return -1;

            int res = Arrays.binarySearch(nums, target);
            return res >= 0? res: -1;
        }

        // after rotation, elements are distributed as
        // large, small
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        while (l < r) {
            mid = l + (r - l) / 2;
            // turning pt, from max to min
            if (nums[mid] > nums[mid + 1]) {
                max = nums[mid];
                min = nums[mid + 1];
                l = mid;
                break;
            }

            // mid is in small area, turning pt must be before mid
            if (nums[mid] < nums[0]) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        mid = l;
        if (target < min || target > max) return -1;

        int res = 0;
        if (target >= nums[0]) {
            res = Arrays.binarySearch(nums, 0, mid + 1, target);
        }
        else {
            res = Arrays.binarySearch(nums, mid + 1, nums.length, target);
        }
        return res >= 0? res: -1;
    }

    public static int rotatedSearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // left sorted portion, large area
            if (nums[l] <= nums[mid]) {
                // target
                // in small area OR
                // in right side of mid, but still in large area
                if (target < nums[l] || target > nums[mid])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            // right sorted portion, small area
            else {
                // target
                // in large area OR
                // in left side of mid, but still in small area
                if (target > nums[r] || target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {4,5,6,7,0,1,2};
        int[] arr2 = {5,1,3};
        System.out.println(search(arr1, 0));
    }
}
