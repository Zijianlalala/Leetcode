import com.sun.xml.internal.ws.message.stream.StreamHeader;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 3, 2, 3, 1};
        System.out.println(
                solution.reversePairs(nums));
        System.out.println(Arrays.toString(nums));
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
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 1370.上升下降字符串
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }
        StringBuilder result = new StringBuilder();
        while (result.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (nums[i] > 0) {
                    nums[i]--;
                    System.out.println('a' + i);
                    result.append((char) ('a' + i));
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (nums[i] > 0) {
                    nums[i]--;
                    result.append((char) ('a' + i));
                }
            }
        }
        return result.toString();
    }

    private void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 164.最大间距
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        // 单个桶的区间
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        // 桶的数量
        int bucketSize = (maxVal - minVal) / d + 1;
        // 用二维数组维护桶的，一个桶维护两个值（max, min）
        int[][] bucket = new int[bucketSize][2];
        // 初始化，min=-1/max=-1
        for (int i = 0; i < bucketSize; i++) {
            Arrays.fill(bucket[i], -1);
        }
        // 排序，将各个元素放入不同的桶中（每个桶只维护两个元素，这个桶中的最大值和最小值）
        for (int i = 0; i < n; i++) {
            // 映射函数
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(nums[i], bucket[idx][0]);
                bucket[idx][1] = Math.max(nums[i], bucket[idx][1]);
            }
        }
        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1)// 说明该桶没有元素
                continue;
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }

    /**
     * 454.四数相加II
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }

    /**
     * 220.存在重复元素III
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        // 设置桶的长度范围
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            // 计算每个元素所属哪一个桶
            long m = getID(nums[i], w);
            if (d.containsKey(m))
                return true;
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long) nums[i]);
            // 维护一个滑动窗口
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    /**
     * 映射函数
     *
     * @param x
     * @param w
     * @return
     */
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    /**
     * 493.翻转对
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums.length < 2) return 0;
        int[] aux = new int[nums.length];
        return (int) sort(nums, aux, 0, nums.length - 1);
    }

    private long sort(int[] nums, int[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = (hi - lo) / 2 + lo;
        long inversions = 0;
        inversions += sort(nums, aux, lo, mid);
        inversions += sort(nums, aux, mid + 1, hi);
        inversions += merge(nums, aux, lo, mid, hi);
        return inversions;
    }

    private long merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        long inversions = 0;
        int i = lo;
        int j = mid + 1;
         for (int k = lo; k <= hi; k++) {
            aux[k] = nums[k];
        }
        // 统计
        while (i <= mid && j <= hi) {
            if ((long) aux[i] > 2 * (long) aux[j]) {
                inversions += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }
        // 重置指针
        i = lo;
        j = mid + 1;
        // 合并
        for (int k = lo; k <= hi; k++) {
            if (i > mid) nums[k] = aux[j++];
            else if (j > hi) nums[k] = aux[i++];
            else if (aux[i] < aux[j])
                nums[k] = aux[i++];
            else
                nums[k] = aux[j++];
        }
        return inversions;
    }
}
