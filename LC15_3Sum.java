import java.text.CollationElementIterator;
import java.util.*;

public class LC15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> used = new ArrayList<>();
        HashSet<List<Integer>> hashSet = new HashSet<>();

        dfs(nums,0, 0, 0, res, used, hashSet);

        return res;
    }

    private void dfs(int[] nums, int size, int start, int sum, List<List<Integer>> res,
                           List<Integer> used, HashSet<List<Integer>> hashSet) {
        if (size == 3 && sum == 0) {
            List<Integer> ans = new ArrayList<>(used);
            Collections.sort(ans);
            if (!hashSet.contains(ans)) {
                res.add(ans);
                hashSet.add(ans);
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            used.add(nums[i]);
            dfs(nums, size + 1, i + 1, sum + nums[i], res, used, hashSet);
            used.remove(used.size() - 1);
        }
    }

    public List<List<Integer>> threeSumBySort(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            int target = -nums[i];

            // if target < 0, nums[i] > 0, elements after nums[i] must be positive
            // no way for three positive numbers add up to get 3
            if (target < 0) break;

            while (start < end) {
                int sum = nums[start] + nums[end];
                if (sum == target) {
                    List<Integer> ans = List.of(nums[i], nums[start], nums[end]);
                    res.add(ans);
                    // since nums are sort, same number are put together
                    // duplicate solution forms when [-2, 0, 1, 1, 2, 2]
                    // there will be two [-2, 0, 2]

                    // Processing duplicates of Number 2
                    // Rolling the front pointer to the next different number forwards
                    while (start < end && nums[start] == ans.get(1)) start++;

                    // Processing duplicates of Number 3
                    // Rolling the back pointer to the next different number backwards
                    while (start < end && nums[end] == ans.get(2)) end--;
                }
                else if (sum > target) {
                    end--;
                }
                else {
                    start++;
                }
            }

            // Processing duplicates of Number 1
            // although the nums[i + 1] may be the second number
            // it is skipped in while loop, so no more than two [nums[i], nums[i + 1} etc.
            while (i + 1 < nums.length && nums[i + 1] == nums[i])
                i++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2,0,1,1,2};
        LC15_3Sum lc = new LC15_3Sum();
        System.out.println(lc.threeSumBySort(nums));
    }
}
