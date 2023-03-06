import java.util.Deque;
import java.util.LinkedList;

public class LC116 {
    public static BinaryNode connect(BinaryNode root) {
        if (root == null) return null;

        Deque<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode prev = root.left;
        int count = 0;
        int level = 0;
        int start = (int)Math.pow(2, level);
        int end = start; // at level 0
        while (!queue.isEmpty()) {
            BinaryNode p = queue.pollFirst();
            count++;
            if (count == Math.pow(2, level + 1)) {
                level++;
                start = (int)Math.pow(2, level);
                end = (int)Math.pow(2, level + 1) - 1;
                prev = p;
            }
            if (level >= 1) {
                if (count != start) prev.next = p;
                prev = p;
                if (count == end) p.next = null;
            }
            if (p.left != null) queue.addLast(p.left);
            if (p.right != null) queue.addLast(p.right);
        }
        return root;
    }

    /**
     *
     * @param root root of <b>perfect binary tree</b>
     * @return root of linked tree
     */
    public static BinaryNode dfsConnect(BinaryNode root) {
        if (root == null) return null;
        System.out.println(root.val);
        if (root.left == null && root.right == null) return root;
        root.left.next = root.right;
        if (root.next != null)
            root.right.next = root.next.left;
        else {
            root.right.next = null;
        }
        root.left = dfsConnect(root.left);
        root.right = dfsConnect(root.right);
        return root;
    }

    public static void main(String[] args) {
        BinaryNode root = BinaryNode.newTree(1,2,3,4,5,6,7);
        root = dfsConnect(root);
    }
}
