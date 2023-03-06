import java.util.Objects;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}

    public static ListNode newTree(Integer... vars) {
        return newTree(0, vars);
    }

    private static ListNode newTree(int index, Integer... vars) {
        if (index >= vars.length || vars[index] == null) return null;

        ListNode node = new ListNode(vars[index]);
        node.next = newTree(index + 1, vars);

        return node;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode listNode = (ListNode) o;

        if (val != listNode.val) return false;
        return Objects.equals(next, listNode.next);
    }
 }