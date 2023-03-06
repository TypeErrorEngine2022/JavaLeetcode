import java.lang.management.MemoryType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102_BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> curList = new ArrayList<>();
        int levelNodeMax = 1;
        int nextLevelMax = 0;

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            curList.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
                nextLevelMax++;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextLevelMax++;
            }

            levelNodeMax--;

            if (levelNodeMax == 0) {
                levelNodeMax = nextLevelMax;
                nextLevelMax = 0;
                res.add(new ArrayList<>(curList));
                curList.clear();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.newTree(1,2,null,3,null,4,null,5);
        System.out.println(levelOrder(root));
    }
}
