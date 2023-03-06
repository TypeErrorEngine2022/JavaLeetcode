/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class LC98_ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, int min, int max) {
        if (root.val < min || root.val > max)
            return false;
        if (root.left == null && root.right == null)
            return true;

        boolean res = true;

        if (root.left != null){
            if (root.left.val >= root.val)
                return false;
            res = dfs(root.left, min, root.val - 1);
        }

        if (!res) return false;

        if (root.right != null){
            if (root.right.val <= root.val)
                return false;
            res = dfs(root.right, root.val + 1, max);
        }

        return res;
    }
}
