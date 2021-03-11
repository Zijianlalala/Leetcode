package Leetcode;

import DataStructure.ListNode;
import DataStructure.TreeNode;
import sun.plugin.cache.OldCacheEntry;

import java.net.Inet4Address;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "1+1-1";
        System.out.println(solution.calculate(s));
     }
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        Deque<Integer> operand = new LinkedList<>();
        Deque<Character> operator = new LinkedList<>();
        StringBuilder numString = new StringBuilder();
        char[] ch = s.toCharArray();
        int idx = 0;
        while(idx < ch.length) {
            char c = ch[idx];
            if (c >= '0' && c <='9') {
                numString = new StringBuilder();
                numString.append(c);
                int j = 1;
                 while(idx + j < ch.length && ch[idx+j] >= '0' && ch[idx+j] <='9') {
                     numString.append(ch[idx+j]);
                     j++;
                 }
                idx += j;
                int num = Integer.valueOf(numString.toString());
                if (operator.peek() == null) {//操作数栈为空
                    operand.push(num);
                } else {// 操作数栈不为空
                    char op = operator.pop();
                    if (op == '*') {
                        int num2 = operand.pop();
                        int ans = num2 * num;
                        operand.push(ans);
                    } else if (op == '/') {
                        int num2 = operand.pop();
                        int ans = num2 / num;
                        operand.push(ans);
                    } else { // 把弹出来的+/-号再入栈//不进行操作
                        operator.push(op);
                        operand.push(num);
                    }
                }
            } else { // 操作符直接入栈
                operator.push(c);
                idx++;
            }
        }
        while(operator.size() > 0 ) {
            char op = operator.removeLast();
            if (op == '+') {
                int num1 = operand.removeLast();
                int num2 = operand.removeLast();
                int ans = num1 + num2;
                operand.addLast(ans);
            } else {
                int num1 = operand.removeLast();
                int num2 = operand.removeLast();
                int ans = num1 - num2;
                operand.addLast(ans);
            }
        }
        return operand.peek();
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        for (String part : wordDict) {
            if (part.length() < minLength)
                minLength = part.length();
            if (part.length() > maxLength)
                maxLength = part.length();
        }
        return wordBreak(s, 0, minLength, maxLength, wordDict);
    }

    boolean wordBreak(String s, int idx, int min, int max, List<String> wordDict) {
        if (idx == s.length()) {
            return true;
        }
        if (idx > s.length())
            return false;
        boolean result = false;
        for (int i = min; i < max + 1; i++) {
            if (result) break;
            if (idx + i > s.length()) break;
            String split = s.substring(idx, idx + i);
            if (wordDict.indexOf(split) != -1) {
                result = wordBreak(s, idx + i, min, max, wordDict);
            }

        }
        return result;
    }

    public String removeDuplicates(String S) {
        char[] ch = S.toCharArray();
        int i = 0;
        int count = 0;
        while (i < ch.length - count - 1 && count < ch.length) {
            if (ch[i] == ch[i + 1]) {
                for (int j = i + 2; j < ch.length - count; j++) {
                    ch[j - 2] = ch[j];
                }

                count += 2;
                i = 0;
                System.out.println(new String(ch) + ", " + new String(ch, 0, ch.length - count) + ", count = " + count);
            } else {
                i++;
            }
        }
        System.out.println(count);
        return new String(ch, 0, ch.length - count);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void f(int[] nums, int idx, List<Integer> list, List<List<Integer>> res) {
        if (idx <= nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            f(nums, i + 1, list, res);
            list.remove(list.size() - 1);
            new String();
        }
    }

    public void backtrack(List<String> ans, StringBuilder s, int left, int right) {
        if (left == 0 && right == 0) {
            ans.add(s.toString());
            return;
        }
        if (left > 0) {
            s.append('(');
            if (validate(s)) {
                backtrack(ans, s, --left, right);
                left++;
            }
            s.deleteCharAt(s.length() - 1);
        }
        if (right > 0) {
            s.append(')');
            if (validate(s)) {
                backtrack(ans, s, left, --right);
                ++right;
            }
            s.deleteCharAt(s.length() - 1);
        }
    }

    boolean validate(StringBuilder s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')')
                right++;
            else
                left++;
        }
        return left >= right;
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                Character top = stack.peek();
                if (c == ')' && (top == null || top != '('))
                    return false;
                else if (c == ']' && (top == null || top != '['))
                    return false;
                else if (c == '}' && (top == null || top != '{'))
                    return false;
                stack.pop();
            }

        }

        return stack.size() == 0;
    }

    int ret = Integer.MAX_VALUE;

    public int minimumEffortPath(int[][] heights) {
        int[][] marked = new int[heights.length][heights[0].length];
        dfs(heights, marked, 0, 0, heights[0][0], 0);
        return ret;
    }

    public void dfs(int[][] heights, int[][] marked, int i, int j, int lastVal, int maxVal) {
//        System.out.println("[" + i + ", " + j + "], " + heights[i][j]);
        int temp = Math.abs(heights[i][j] - lastVal);
        if (temp > maxVal) {
            maxVal = temp;
        }
        if (i == heights.length - 1 && j == heights[0].length - 1) {
//            System.out.println(maxVal);
            if (maxVal < ret) {
                ret = maxVal;
            }
            return;
        }
        marked[i][j] = 1;
        if (i < heights.length - 1 && marked[i + 1][j] == 0) {
//            System.out.println("↓");
            dfs(heights, marked, i + 1, j, heights[i][j], maxVal);
            //marked[i+1][j] = 0;
        }
        if (j < heights[0].length - 1 && marked[i][j + 1] == 0) {
//            System.out.println("→");
            dfs(heights, marked, i, j + 1, heights[i][j], maxVal);
            //marked[i][j+1]=0;
        }
        if (i > 0 && marked[i - 1][j] == 0) {
//            System.out.println("↑");
            dfs(heights, marked, i - 1, j, heights[i][j], maxVal);
            //marked[i-1][j] = 0;
        }
        if (j > 0 && marked[i][j - 1] == 0) {
//            System.out.println("←");
            dfs(heights, marked, i, j - 1, heights[i][j], maxVal);
            //marked[i][j-1] = 0;
        }
        marked[i][j] = 0;

    }

    private class UnionFind {
        private int[] parent;
        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }
    }


    public int findContentChildren(int[] g, int[] s) {
        int ret = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (s[j] != -1 && g[i] <= s[j]) {
                    ret++;
                    s[j] = -1;
                    g[i] = -1;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(g));
        System.out.println(Arrays.toString(s));
        return ret;
    }

    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        int[] indexes = new int[26];
        int firstIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            indexes[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 1 && firstIndex == -1) {
                firstIndex = indexes[i];
            } else if (counts[i] == 1 && firstIndex != -1 && firstIndex > indexes[i]) {
                firstIndex = indexes[i];
            }
        }
        return firstIndex;
    }


    /**
     * 单调递增的数字
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        String s = String.valueOf(N);
        char[] ret = new char[s.length()];
        ret[0] = s.charAt(0);
        int i = 1;
        for (; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                ret[--i]--;
                break;
            }
            ret[i] = s.charAt(i);
        }
        if (i < s.length()) {
            while (i > 0 && ret[i - 1] > ret[i]) {
                ret[--i]--;
            }
            // 填充后面的值
            for (int j = i + 1; j < s.length(); j++) {
                ret[j] = '9';
            }

        }
        return Integer.parseInt(new String(ret));
    }

    /**
     * 649. Dota2 参议院
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }


    /**
     * 860. 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int[] wallet = new int[3];//5/10/20
        for (int i = 0; i < bills.length; i++) {
            int change = bills[i] - 5;
            if (change == 5) { // 需要找零
                if (wallet[0] > 0) {
                    wallet[0]--;
                } else {
                    return false;
                }
            } else if (change == 15) {
                if (wallet[0] > 0 && wallet[1] > 0) {
                    wallet[0]--;
                    wallet[1]--;
                } else if (wallet[0] > 2) {
                    wallet[0] -= 3;
                } else {
                    return false;
                }
            }
            if (bills[i] != 20)
                wallet[bills[i] / 5 - 1]++;
        }
        return true;
    }

    /**
     * 62.不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (n < 2 || m < 2) return 1;

        int[][] aux = new int[m][n];
        for (int i = 0; i < m; i++) {
            aux[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            aux[m - 1][j] = 1;
        }
        for (int j = n - 2; j >= 0; j--) {
            for (int i = m - 2; i >= 0; i--) {
                aux[i][j] = aux[i][j + 1] + aux[i + 1][j];
            }
        }
        return aux[0][0];
    }

    /**
     * 842. 将数组拆分成斐波那契序列
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<Integer>();
        backtrack(list, S, S.length(), 0, 0, 0);
        return list;
    }

    public boolean backtrack(List<Integer> list, String S, int length, int index, int sum, int prev) {
        if (index == length) {
            return list.size() >= 3;
        }
        long currLong = 0;
        for (int i = index; i < length; i++) {
            if (i > index && S.charAt(index) == '0') {
                break;
            }
            // 想到了这样组合成一个整型
            currLong = currLong * 10 + S.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            if (list.size() >= 2) {
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            list.add(curr);
            // 没有想到递归失败，怎么回溯的方法，每次backtrack就是一次切分
            if (backtrack(list, S, length, i + 1, prev + curr, curr)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }

    /**
     * 861.翻转矩阵后的得分
     *
     * @param
     * @return
     */
    public int matrixScore(int[][] A) {
        int ret = 0;
        int rows = A.length;
        int cols = A[0].length;
        // 调整行向量
        for (int i = 0; i < rows; i++) {
            // 可以直接判断首位是不是0
            if (A[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    A[i][j] ^= 1;//异或
                }
            }
        }
        // 调整列向量
        for (int i = 0; i < cols; i++) {
            // 统计一列中"1"的个数
            int count = 0;
            for (int j = 0; j < rows; j++) {
                count += A[j][i];
            }
            ret += Math.max(count, rows - count) * (1 << (cols - i - 1));
        }
        return ret;
    }


    /**
     * 118.杨辉三角
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(list);
        }
        return ret;
    }

    /**
     * 621.任务调度器
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int count = tasks.length;
        int[] nums = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            nums[tasks[i] - 'A']++;
        }
        int N = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > N) {
                N = nums[i];
            }
        }
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == N) {
                x++;
            }
        }
        return Math.max(count, (N - 1) * (n + 1) + x);
    }

    /**
     * 659.分割数组为连续子序列
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums) {
            if (!map.containsKey(x)) {
                map.put(x, new PriorityQueue<Integer>());
            }
            if (map.containsKey(x - 1)) {
                int preLength = map.get(x - 1).poll();
                if (map.get(x - 1).isEmpty()) {
                    map.remove(x - 1);
                }
                map.get(x).offer(preLength + 1);
            } else {
                map.get(x).offer(1);
            }
        }
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> set = map.entrySet();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : set) {
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 204.计数质数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n < 3) return 0;
        // 默认值是false
        boolean[] signs = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!signs[i]) {
                count++;
                for (int j = i * i; j < n; j += i) {
                    signs[j] = true;
                }
            }
        }
        return count;
    }


    /**
     * 321.拼接最大数
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n);//??
        int end = Math.min(k, m);//??
        for (int i = start; i <= end; i++) {
            int[] sub1 = maxSubsequence(nums1, i);
            int[] sub2 = maxSubsequence(nums2, k - i);
            int[] curMaxSub = merge2(sub1, sub2);
            if (compare(curMaxSub, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSub, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 用单调栈来找长度为k的最大子序列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;// remain表示可以出栈的元素的数量，当remain=0时，再出栈的话，stack就填不满了，故remain=0时，不再出栈了
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            // 找到比num小的，使其出栈
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public int[] merge2(int[] nums1, int[] nums2) {
        int x = nums1.length;
        int y = nums2.length;
        if (x == 0) return nums2;
        if (y == 0) return nums1;
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(nums1, index1, nums2, index2) > 0) {
//            if (nums1[index1] >= nums2[index2]) {
                merged[i] = nums1[index1++];
            } else {
                merged[i] = nums2[index2++];
            }
        }
        return merged;
    }

    private int compare(int[] nums1, int index1, int[] nums2, int index2) {
        int x = nums1.length;
        int y = nums2.length;
        while (index1 < x && index2 < y) {
            int diff = nums1[index1] - nums2[index2];
            if (diff != 0) {
                return diff;
            }
            index1++;
            index2++;
        }
        // index1或index2越界后
        // 若index2越界，则-(y-index2) > 0，故return value > 0 太妙了吧草
        return (x - index1) - (y - index2);
    }


    /**
     * 34.在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;
        if (nums.length > 0) {
            // 二分查找
            int mid = binarySort(nums, 0, nums.length - 1, target);
            if (mid != -1) {
                int i = mid;
                while (i >= 0 && nums[i] == target) {
                    i--;
                }
                start = i + 1;
                int j = mid;
                while (j < nums.length && nums[j] == target) {
                    j++;
                }
                end = j - 1;
            }
        }
        return new int[]{start, end};
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param lo
     * @param hi
     * @param target
     * @return
     */
    public int binarySort(int[] nums, int lo, int hi, int target) {
        int mid = (lo + hi) / 2;
        while (nums[mid] != target) {
            if (lo == hi) {
                return -1;
            }
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
            mid = (lo + hi) / 2;
        }
        return mid;
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
    public ListNode<Integer> InsertionSortList(ListNode<Integer> head) {
        if (head == null) return null;
        ListNode<Integer> q, qPre = null;
        ListNode<Integer> pPre = head, p = pPre.next;
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
    public ListNode<Integer> sortList(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        // create a null head
        ListNode<Integer> nullHead = new ListNode(0, head);
        ListNode<Integer> sorted = new ListNode(0, null);
        ListNode<Integer> cur, pre;
        while (nullHead.next != null) {
            pre = nullHead;
            cur = pre.next;
            int maxVal = cur.val;
            ListNode<Integer> max = cur, preMax = pre;
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

    private ListNode<Integer> merge(ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> dummyHead = new ListNode(0);
        ListNode<Integer> temp = dummyHead, temp1 = head1, temp2 = head2;

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

    /**
     * 976.三角形的最大周长
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        if (A.length <= 2) return 0;
        Arrays.sort(A);
        int k = A.length - 1;
        int j = k - 1;
        int i = j - 1;
        while (i >= 0) {
            if (isTriangle(A[i], A[j], A[k])) {
                return A[i] + A[j] + A[k];
            }
            i--;
            j--;
            k--;
        }
        return 0;
    }

    private boolean isTriangle(int a, int b, int c) {
        if (a + b <= c) return false;
        else if (a + c <= b) return false;
        else if (b + c <= a) return false;
        else return true;
    }

    /**
     * 767. 重构字符串
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        // 哈希表
        int[] table = new int[26];
        // 存放结果
        char[] ret = new char[S.length()];
        // 统计个数
        for (int i = 0; i < S.length(); i++) {
            table[S.charAt(i) - 'a']++;
        }
        // 重新构建
        int i = 0;
        while (i < S.length()) {
            // 遍历一遍哈希表
            for (int j = 0; j < table.length; j++) {
                if (table[j] > 0 && i > 0 && ret[i - 1] == (char) (j + 'a')) {
                    if (!insert(ret, (char) (j + 'a'), i - 1)) {
                        return "";
                    } else {
                        i++;
                        table[j]--;
                        continue;
                    }
                }
                if (table[j] > 0) {
                    ret[i++] = (char) (j + 'a');
                    table[j]--;
                }
            }
        }
        return String.valueOf(ret);
    }

    /**
     * 将v从后往前遍历插入到不与v'相邻的位置
     *
     * @param ret
     * @param v
     * @param tail
     */
    private boolean insert(char[] ret, char v, int tail) {
        // 找到插入位置
        int lo = -1;
        for (int i = tail - 1; i > 0; i--) {
            if (ret[i] != v && ret[i - 1] != v) {
                lo = i;
            }
        }
        // 判断头部满不满足条件
        if (lo == -1 && ret[0] != v) {
            lo = 0;
        }
        // 没有满足的位置
        if (lo == -1) {
            return false;
        }
        // 有满足的位置
        for (int i = tail; i >= lo; i--) {
            ret[i + 1] = ret[i];
        }
        ret[lo] = v;
        return true;
    }
}
