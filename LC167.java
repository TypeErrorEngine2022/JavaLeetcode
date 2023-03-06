import java.util.Arrays;

public class LC167 {
    // O(n), worst case: the two numbers are on the last 2 positions
    // then, left has to traversed (n-1) times
    public static int[] twoSum(int[] numbers, int target) {
        int size = numbers.length;
        int left = 0;
        int right = left + 1;
        int[] res = new int[2];
        while (left < size) {
            right = left + 1;
            int diff = target - numbers[left];
            int key = Arrays.binarySearch(numbers, right, size, diff);
            if (key >= 0) {
                right = key;
                break;
            }
            left++;
        }
        res[0] = left + 1;
        res[1] = right + 1;
        return res;
    }

    public static int[] binaryTwoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            else if (sum < target) { // need bigger number
                left++; // increase lower-bound
            }
            else {
                right++;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    public static void main(String[] args) {
        int[] numbers = {2,3,4};
        int[] res = binaryTwoSum(numbers, 6);
        System.out.println(Arrays.toString(res));
    }
}
