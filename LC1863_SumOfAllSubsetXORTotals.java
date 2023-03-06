import java.util.BitSet;
import java.util.LinkedList;
import java.util.ListIterator;

public class LC1863_SumOfAllSubsetXORTotals {
    public static int subsetXORSum(int[] nums) {
        int sz = nums.length;
        int subsetNumber = 1 << sz;

        int count = 0;
        LinkedList<Integer> newSub = new LinkedList<>();
        for (int i = 0; i < subsetNumber; i++) {
            int tmp = i;
            int tmpSum = 0;
            newSub.clear();
            for (int num: nums) {
                if ( (tmp & 1) == 1)
                    newSub.add(num);
                tmp = tmp >> 1;
            }
            if (newSub.size() == 0) continue;

            tmpSum = newSub.peek();
            ListIterator<Integer> listIterator = newSub.listIterator(1);
            while (listIterator.hasNext()) {
                tmpSum ^= listIterator.next();
            }
            count += tmpSum;
        }
        return count;
    }

    public static int backtrackSubsetXORSum(int[] nums) {
        return backtrack(nums, 0, 0);
    }

    public static int backtrack(int[] nums, int index, int currentXor) {
        if (index == nums.length) return currentXor;

        return backtrack(nums, index + 1, currentXor ^ nums[index]) +
                backtrack(nums, index + 1, currentXor);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(backtrackSubsetXORSum(nums));
    }
}
