public class LC876_MiddleLinkedList {
    public ListNode middleNode(ListNode head) {
        if (head.next == null) return head;
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        int mid = 0;
        if (size % 2 != 0)
            mid = (size + 1) / 2;
        else
            mid = (size + 1) / 2 + 1;

        for (int i = 0; i < mid - 1; i++) {
            head = head.next;
        }

        return head;
    }
}
