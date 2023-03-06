import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;

public class LC283 {
    public static void snowBall(int[] arr) {
        int snowBallSize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                snowBallSize++;
            }
            else if (snowBallSize > 0) {
                // [1, 0, 0, 3, 2] : before 3, there is size = 2 snowball,
                // the first 0 in snowball must be i - snowballSize
                arr[i - snowBallSize] = arr[i];
                arr[i] = 0;
            }
        }
    }

    // Two pointer approach, more more obvious snowball solution
    public static void moveZeroes(int[] nums) {
        // Base case null check and check for 0 or single element input
        if (nums == null || nums.length <= 1) return;

        int leftMostZeroIndex = 0;                      // Tracks the index of the left-most 0 in the array during iteration

        // Iterate through input array of numbers, moving all 0's to the end
        // of the array and maintaining relative order of non-zero elements
        for (int i = 0; i < nums.length; i++)
        {
            // 0's are found to the left of the current non-zero number, swap the 0 and non-zero entry in array
            if (nums[i] != 0 && leftMostZeroIndex < i)
            {
                // Swap 0 and non-zero elements
                nums[leftMostZeroIndex] = nums[i];  // Set index of left-most 0 to non-zero entry at curr index
                nums[i] = 0;                            // Set curr index value to 0
                leftMostZeroIndex++;
            }
        }
    }

    // Another two-pointer approach
    public static void moveNonZeroToBeginning(int[] arr) {
        int size = arr.length;
        int noOfCoveredZero = 0;

        for (int i = 0; i < size; i++) {
            if (arr[i] != 0)
                //push nonzero to beginning
                arr[i - noOfCoveredZero] = arr[i];
            else
                noOfCoveredZero++;
        }

        // overwrite the last few elements to 0
        for (int i = size - 1; i >= size - noOfCoveredZero; i--)
            arr[i] = 0;
    }

    public static void main(String[] args) {
            int[] arr = {0, 0, 3, 0, 1, 5};
            moveNonZeroToBeginning(arr);
            System.out.println(Arrays.toString(arr));
    }
}
