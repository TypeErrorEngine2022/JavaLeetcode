import java.util.*;

public class LC39_CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();

        HashSet<List<Integer>> hashSet = new HashSet<>();
        backtrack(candidates, target, 0, 0, res, path, hashSet);
        return res;
    }

    private void backtrack(int[] candidates, int target, int sum, int start, List<List<Integer>> res,
                            LinkedList<Integer> path, HashSet<List<Integer>> hashSet) {
        if (sum > target) return;
        if (sum == target) {
            List<Integer> ans = new ArrayList<>(path);
            Collections.sort(ans);
            if (!hashSet.contains(ans)) {
                res.add(ans);
                hashSet.add(ans);
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            backtrack(candidates, target, sum + candidates[i], i, res, path, hashSet);
            path.removeLast();
        }
    }

    public static List<List<Integer>> combinationSumDP(int[] candidates, int target) {
        // each element stores all the combinations for that number
        ArrayList<List<List<Integer>>> dp = new ArrayList<>(target + 1);

        for (int i = 0; i <= target; i++) {
            dp.add(new LinkedList<>());
        }

        for (int candidate: candidates) {
            // combination of number i
            for (int i = 1; i <= target; i++) {
                if (candidate > i) continue;
                if (candidate == i) {
                    LinkedList<Integer> temp = new LinkedList<>();
                    temp.addLast(candidate);
                    dp.get(i).add(temp);
                }
                else {
                    for (List<Integer> prevComb: dp.get(i - candidate)) {
                        LinkedList<Integer> newList = new LinkedList<>(prevComb);
                        newList.addLast(candidate);
                        dp.get(i).add(newList);
                    }
                }
            }
        }
        return dp.get(target);
    }

    public static void main(String[] args) {
        int[] arr1 = {2,3,6,7};
        List<List<Integer>> list = combinationSumDP(arr1, 7);
    }
}
