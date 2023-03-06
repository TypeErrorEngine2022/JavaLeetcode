import java.util.Objects;

public class BinaryNode implements Comparable<BinaryNode>{
    public int val;
    public BinaryNode left;
    public BinaryNode right;
    public BinaryNode next;

    public BinaryNode() {}

    public BinaryNode(int _val) {
        val = _val;
    }

    public BinaryNode(int _val, BinaryNode _left, BinaryNode _right, BinaryNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static BinaryNode newTree(Integer... vars) {
        return newTree(0, vars);
    }

    private static BinaryNode newTree(int index, Integer... vars) {
        if (index >= vars.length || vars[index] == null) return null;

        BinaryNode node = new BinaryNode(vars[index]);
        node.left = newTree(2 * index + 1, vars);
        node.right = newTree(2 * index + 2, vars);

        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryNode treeNode = (BinaryNode) o;

        if (val != treeNode.val) return false;
        if (!Objects.equals(left, treeNode.left)) return false;
        return Objects.equals(right, treeNode.right);
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + left+
                ", right=" + right +
                ", next=" + next +
                '}';
    }

    @Override
    public int compareTo(BinaryNode o) {
        return Integer.compare(this.val, o.val);
    }
}
