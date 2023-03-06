import java.util.List;

public class LC206_ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode cur = head;
        ListNode prev = null;
        while (cur.next != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        cur.next = prev;
        return cur;
    }

    public static ListNode reverseListSecond(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode cur = head;
        ListNode prev = null;
        while (cur.next != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        cur.next = prev;

        return cur;
    }

    public static ListNode reverseListRecursive(ListNode head) {
        return recursiveHelper(head, null);
    }

    public static ListNode recursiveHelper(ListNode head, ListNode prev) {
        if (head == null) return prev;
        ListNode next = head.next;
        head.next = prev;
        return recursiveHelper(next, head);
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.newTree(1,2,3,4,5);
        System.out.println(reverseListRecursive(listNode));
    }
}
