import javax.swing.tree.TreeCellRenderer;

public class LC617 {
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        return dfs(root1, root2);
    }

    public static TreeNode fastMergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return copyTree(root2);
        if (root2 == null) return copyTree(root1);

        return new TreeNode(root1.val + root2.val, fastMergeTrees(root1.left, root2.left), fastMergeTrees(root1.right, root2.right));
    }

    public static TreeNode copyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = copyTree(root.left);
        res.right = copyTree(root.right);
        return res;
    }

    public static TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;

        TreeNode res = null;

        if (root1 != null && root2 == null) { // only r1
            System.out.print(root1.val + " ");
            System.out.println("null ");
            res = new TreeNode(root1.val, root1.left, root1.right);
        }
        else if (root1 == null) { // only r2
            System.out.println("null ");
            System.out.print(root2.val + "\n");
            res = new TreeNode(root2.val, root2.left, root2.right);
        }
        else { // r1 and r2
            System.out.println(root1.val + " " + root2.val);
            res = new TreeNode(root1.val + root2.val);
            res.left = dfs(root1.left, root2.left);
            res.right = dfs(root1.right, root2.right);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode r1 = TreeNode.newTree(1, 3, 2, 5);
        TreeNode r2 = TreeNode.newTree(2,1,3,null,4,null,7);

        TreeNode res = fastMergeTrees(r1, r2);
        System.out.println(res);
    }
}
