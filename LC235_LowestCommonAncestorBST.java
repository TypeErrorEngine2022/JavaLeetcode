import java.util.Deque;
import java.util.LinkedList;

public class LC235_LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // both nodes are in one subtree of BST
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        // nodes in two sides, eg. two grandchildren of root
        // OR root equals to one of the node
        return root;
    }
}
