import java.util.ArrayList;
import java.util.List;

public class LC78_Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(nums, 0, new ArrayList<>(), res);

        return res;
    }

    public static void dfs(int[] nums, int curIndex, List<Integer> path,
                           List<List<Integer>> res) {
        if (curIndex == nums.length) {
            res.add(path);
            return;
        }

        List<Integer> altPath = new ArrayList<>(path);
        path.add(nums[curIndex]);

        dfs(nums, curIndex + 1, path, res);
        dfs(nums, curIndex + 1, altPath, res);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
}
