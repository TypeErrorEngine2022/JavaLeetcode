import java.util.*;

public class LC77_Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> used = new ArrayList<>();
        dfs(n, k, 0, 1, res, used);
        return res;
    }

    public static void dfs(int n, int k, int size, int start,
                           List<List<Integer>> res, List<Integer> used) {
        if (size == k) {
            res.add(new ArrayList<>(used));
            return;
        }

        // if you are at (2,?), don't want (2,1), so start at (2,3)
        // know that all elements before start is visited (by order of number)
        // so no need bitset
        for (int i = start; i <= n; i++) {
            used.add(i);
            dfs(n, k, size + 1, i + 1, res, used);
            used.remove(used.size() - 1);
        }
    }

    public static void main(String[] args) {
        // compare to sample output
        int[][] arr = {{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
        List<List<Integer>> lists = new ArrayList<>();
        for (int[] a: arr) {
            List<Integer> l = new ArrayList<>();
            for (int i: a) {
                l.add(i);
            }
            lists.add(l);
        }
        System.out.println("sample output: " + lists);

        // real code
        lists = combine(4, 2);
        System.out.println("       output: " + lists);
    }
}
