import javax.crypto.spec.PSource;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LC236_LowestCommonAncestorBT {


    // recommended, easy to implement
    public TreeNode recursiveLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val)
            return root;

        TreeNode l = recursiveLowestCommonAncestor(root.left, p, q);
        TreeNode r = recursiveLowestCommonAncestor(root.right, p, q);
        if (l != null && r != null)
            return root;

        if (l != null)
            return l;

        return r;
    }

    private enum State{
        NONE, FOUND, END;
    }

    private TreeNode res;

    // also fast
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;
        dfs(root, p, q);
        return res;
    }

    private State dfs(TreeNode root, TreeNode p, TreeNode q) {
        State cur = State.NONE;
        if (root.val == p.val) cur = State.FOUND;
        if (root.val == q.val) cur = State.FOUND;

        State l = State.NONE;
        State r = State.NONE;
        if (root.left != null) {
            l = dfs(root.left, p, q);
            if (l == State.END) return State.END;
        }

        if (root.right != null) {
            r = dfs(root.right, p, q);
            if (r == State.END) return State.END;
        }

        boolean childrenFound = (l == State.FOUND || r == State.FOUND);

        if (l == State.FOUND && r == State.FOUND ||
                cur == State.FOUND && childrenFound)  {
            res = root;
            return State.END;
        }

        if (childrenFound || cur == State.FOUND) {
            return State.FOUND;
        }

        return State.NONE;
    }

    public TreeNode lowestCommonAncestorVal(TreeNode root, int p, int q) {
        /*if (root.val == p) return p;
        if (root.val == q) return q;*/
        dfsVal(root, p, q);
        return res;
    }

    private State dfsVal(TreeNode root, int p, int q) {
        State cur = State.NONE;
        if (root.val == p) cur = State.FOUND;
        if (root.val == q) cur = State.FOUND;

        State l = State.NONE;
        State r = State.NONE;
        if (root.left != null) {
            l = dfsVal(root.left, p, q);
            if (l == State.END) return State.END;
        }

        if (root.right != null) {
            r = dfsVal(root.right, p, q);
            if (r == State.END) return State.END;
        }

        boolean childrenFound = (l == State.FOUND || r == State.FOUND);

        if (l == State.FOUND && r == State.FOUND ||
                cur == State.FOUND && childrenFound)  {
            res = root;
            return State.END;
        }

        if (childrenFound || cur == State.FOUND) {
            return State.FOUND;
        }

        return State.NONE;
    }



    public static void main(String[] args) {
        TreeNode root = TreeNode.newTree(3,5,1,6,2,0,8,null,null,7,4);
        LC236_LowestCommonAncestorBT lc = new LC236_LowestCommonAncestorBT();
        System.out.println(lc.lowestCommonAncestorVal(root, 5, 4));
    }
}