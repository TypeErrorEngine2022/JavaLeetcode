import org.w3c.dom.ls.LSException;

public class LC21_MergeTwoSortedLists {
    /*public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head = null;

        if (list1.val <= list2.val){
            head = list1;
            list1 = list1.next;
        }
        else {
            head = list2;
            list2 = list2.next;
        }

        ListNode cur = head;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur.next = list2;
                break;
            }
            if (list2 == null) {
                cur.next = list1;
                break;
            }

            if (list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }
            else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        return head;
    }*/

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode head;
        if (list1.val <= list2.val){
            head = list1;
            list1 = list1.next;
        }
        else {
            head = list2;
            list2 = list2.next;
        }
        ListNode res = head;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                head.next = list2;
                break;
            }
            else if (list2 == null) {
                head.next = list1;
                break;
            }

            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            }
            else {
                head.next = list2;
                list2 = list2.next;
            }

            head = head.next;
        }

        return res;
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.newTree(1,2,4);
        ListNode l2 = ListNode.newTree(1,3,4);
        ListNode l3 = mergeTwoLists(l1, l2);
        System.out.println(l3);
    }
}
