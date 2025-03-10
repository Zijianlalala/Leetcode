package DataStructure;

/**
 * 单链表
 */
public class ListNode<T> {
    public T val;
    public ListNode next;

    public ListNode(T x) {
        val = x;
    }

    public ListNode(T val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.print("null\n");
        }
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + "->");
            p = p.next;
        }
        System.out.print("null\n");
    }

    public static ListNode initList(int[] vals) {
        ListNode head = new ListNode(vals[0]);

        ListNode q = head, p;
        for (int i = 1; i < vals.length; i++) {
            p = new ListNode(vals[i]);
            q.next = p;
            q = q.next;
        }
        q.next = null;
        return head;
    }


}
