import com.sun.xml.internal.ws.message.stream.StreamHeader;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2}));
    }

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
     *
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

    /**
     * 148. 链表排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // create a null head
        ListNode nullHead = new ListNode(0, head);
        ListNode sorted = new ListNode(0, null);
        ListNode cur, pre;
        while (nullHead.next != null) {
            pre = nullHead;
            cur = pre.next;
            int maxVal = cur.val;
            ListNode max = cur, preMax = pre;
            while (cur != null) {
                if (cur.val > maxVal) {
                    maxVal = cur.val;
                    max = cur;
                    preMax = pre;
                }
                pre = cur;
                cur = cur.next;
            }
            // remove node from old list
            preMax.next = max.next;
            System.out.println("maxVal" + max.val);
            // print head
            ListNode.print(head);
            // add node to new list
            max.next = sorted.next;
            sorted.next = max;

        }
        return sorted.next;
    }


    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return head;
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

    /**
     * 242.有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--map[t.charAt(i) - 97] < 0)
                return false;
        }
        return true;
    }

    /**
     * 无重复最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int N = s.length();
        int i = 0, rk = -1, ans = 0;
        for (; i < N; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (rk + 1 < N && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    /**
     * 归并两个有序数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] merge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] aux = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                aux[k++] = nums1[i++];
            } else {
                aux[k++] = nums2[j++];
            }
        }
        while (i < n) {
            aux[k++] = nums1[i++];
        }
        while (j < m) {
            aux[k++] = nums2[j++];
        }
        return aux;
    }

    /**
     * 222. 完全二叉树的节点个数
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
