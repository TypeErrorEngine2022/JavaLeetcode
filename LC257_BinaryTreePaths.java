import java.util.LinkedList;
import java.util.List;

public class LC257_BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;
        List<String> res = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.val);
        dfs(root, stringBuilder, res);
        return res;
    }

    public static void dfs(TreeNode root, StringBuilder str, List<String> res) {
        if (root.left == null && root.right == null)
            res.add(str.toString());

        StringBuilder rightStringBuilder = new StringBuilder(str);
        if (root.left != null) dfs(root.left, str.append("->").append(root.left.val), res);

        if (root.right != null) dfs(root.right, rightStringBuilder.append("->").append(root.right.val), res);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.newTree(1,2,3,null,5);
        System.out.println(binaryTreePaths(root));
    }
}
