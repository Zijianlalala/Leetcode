public class Solution {
    /**
     * 283. 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }

    /**
     * 147.对链表进行插入排序
     * @param head
     * @return
     */
    public ListNode InsertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode q, qPre = null;
        ListNode pPre = head, p = pPre.next;
        while (pPre.next != null) {
            // 如果当前节点比前序节点大，则略过本次排序
            if (p.val >= pPre.val) {
                pPre = p;
                p = p.next;
                continue;
            }
            q = head;
            p = pPre.next;
            // 从头遍历，找到比p大的节点
            while (q != p && p.val >= q.val) {
                //pPre = p;
                //p = p.next;
                qPre = q;
                q = q.next;
            }
            if (q == p) {
                // 没有找到
                pPre = p;
                continue;
            }
            // 移除p
            pPre.next = p.next;
            // p 插在 q前面
            // 1. q前面有节点
            if (q != head) {
                p.next = q;
                qPre.next = p;
            }
            // 2. q是头节点
            else {
                p.next = q;
                head = p;
            }
        }
        return head;
    }
}
