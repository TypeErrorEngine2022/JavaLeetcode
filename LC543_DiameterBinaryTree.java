public class LC543_DiameterBinaryTree {
    public int maxDiff;
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return 0;

        int maxDiff = 0;

        int leftHeight = 1 + diameterOfBinaryTree(root.left);
        int rightHeight = 1 + diameterOfBinaryTree(root.right);

        int diff = Math.abs(leftHeight + rightHeight);

        return Math.max(leftHeight + rightHeight, maxDiff);
    }

    public int depth(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return 0;

        int leftHeight = 1 + depth(root.left);
        int rightHeight = 1 + depth(root.right);

        int diff = Math.abs(leftHeight + rightHeight);
        maxDiff = Math.max(maxDiff, diff);

        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.newTree(1,2);
        System.out.println(diameterOfBinaryTree(root));
    }
}
