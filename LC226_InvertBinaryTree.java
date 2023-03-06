import java.util.Deque;
import java.util.Stack;

public class LC226_InvertBinaryTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // leaf node
        if (root.left == null && root.right == null)
            return root;
        else if (root.left == null) {
            root.left = root.right;
            root.right = null;
            root.left = invertTree(root.left);
        }
        else if (root.right == null) {
            root.right = root.left;
            root.left = null;
            root.right = invertTree(root.right);
        }
        // both left and right non-null
        else {
            //swap
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            root.left = invertTree(root.left);
            root.right = invertTree(root.right);
        }
        return root;
    }

    public static TreeNode invertTreeStack(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            if (cur == null) continue;
            TreeNode l = cur.left;
            cur.left = cur.right;
            cur.right = l;
            stack.push(cur.left);
            stack.push(cur.right);
        }
        return root;
    }
}
