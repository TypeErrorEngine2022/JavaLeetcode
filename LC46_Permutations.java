import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class LC46_Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 1) {
            path.add(nums[0]);
            res.add(path);
            return res;
        }

        BitSet used = new BitSet(20);
        backtrack(nums, 0, path, used, res);

        return res;
    }

    public static void backtrack(int[] nums, int size, List<Integer> path,
                                 BitSet used, List<List<Integer>> res) {
        if (size == nums.length)
            res.add(new LinkedList<>(path));

        for (int i = 0; i < nums.length; i++) {
            int indexOfBitset = (nums[i] <= 0)? nums[i] * -1 : 10 + nums[i];
            if (used.get(indexOfBitset)) continue;

            path.add(nums[i]);
            used.set(indexOfBitset);
            backtrack(nums, size + 1, path, used, res);
            path.remove(path.size() - 1);
            used.set(indexOfBitset, false);
        }
    }

    public static List<List<Integer>> permuteSecond(int[] nums) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        BitSet bitSet = new BitSet(20);
        backtrackSecond(nums, 0, path, bitSet, res);
        return res;
    }

    public static void backtrackSecond(int[] nums, int size, List<Integer> path,
                                 BitSet used, List<List<Integer>> res) {
        if (size == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int bitIndex = nums[i] < 0 ? -nums[i] : 10 + nums[i];
            if (used.get(bitIndex)) continue;
            used.set(bitIndex);
            path.add(0, nums[i]);
            backtrackSecond(nums, size + 1, path, used, res);
            path.remove(0);
            used.clear(bitIndex);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }
}
