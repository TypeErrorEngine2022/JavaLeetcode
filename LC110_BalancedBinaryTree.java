public class LC110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(depth(root.left) - depth(root.right)) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }


    public int depth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null)
            return 1;
        else if (root.left == null)
            return 1 + depth(root.right);
        else if (root.right == null)
            return 1 + depth(root.left);
        else
            return Math.max(1 + depth(root.right), 1 + depth(root.left));
    }

    public int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = dfs(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfs(root.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight);
    }

    public boolean isBalancedDFS(TreeNode root) {
        return dfs(root) != -1;
    }
}
