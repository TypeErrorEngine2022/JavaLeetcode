import java.util.List;

public class LC141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        if (head.next == null) return false;
        if (head.next.equals(head)) return true;

        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null) {
            if (fastPtr.next == null) return false;
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == null) return false;
            if (fastPtr.equals(slowPtr))
                return true;
        }
        return false;
    }
}
