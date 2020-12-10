package codingInterview;

import DataStructure.ListNode;
import DataStructure.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.printNumbers(3)));
    }

    /**
     * 19.正则表达式匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        return false;
    }

    /**
     * 18.删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = dummyHead.next;
        ListNode pre = dummyHead;
        while (p != null && p.val != val) {
            pre = p;
            p = p.next;
        }
        pre.next = p.next;
        return dummyHead.next;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int max = 9;
        while (n > 1) {
            max = max * 10 + 9;
            n--;
        }
        int[] ret = new int[max];
        for (int i = 0; i < max; i++) {
            ret[i] = i + 1;
        }
        return ret;
    }

    /**
     * 16.快速幂
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        boolean positive = true;
        long longN = n;
        if (longN < 0) {
            longN = -longN;
            positive = false;
        }
        return positive ? myPowUnsigned(x, longN) : 1.0 / myPowUnsigned(x, longN);
    }

    public double myPowUnsigned(double x, long n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double ret = myPowUnsigned(x, n >> 1);
        ret *= ret;
        if ((n & 0x1) == 1) {
            ret *= x;
        }
        return ret;
    }

    /**
     * 15. 二进制中1的个数
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            n &= (n - 1);
            cnt++;
        }
        return cnt;
    }

    /**
     * 14.I.剪绳子
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] ret = new int[n + 1];
        ret[1] = 1;
        ret[2] = 2;
        ret[3] = 3;
        // 填充数组
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int curr = ret[j] * ret[i - j];
                if (curr > max) {
                    max = curr;
                }
            }
            ret[i] = max;
        }
        return ret[n];
    }


    /**
     * 剑指 Offer 13. 机器人的运动范围
     *
     * @param m rows
     * @param n cols
     * @param k
     * @return count
     */
    public int movingCount(int m, int n, int k) {
        int count = 0;
        int[] memo = new int[m * n];
        count = movingCount(0, 0, m, n, memo, k);
        return count;
    }

    private int movingCount(int i, int j, int m, int n, int[] memo, int k) {
        int count = 0;
        if (check(i, j, m, n, memo, k)) {
            memo[i * n + j]++;
            count = 1 +
                    movingCount(i, j - 1, m, n, memo, k) +
                    movingCount(i, j + 1, m, n, memo, k) +
                    movingCount(i - 1, j, m, n, memo, k) +
                    movingCount(i + 1, j, m, n, memo, k);
        }
        return count;
    }

    private boolean check(int i, int j, int m, int n, int[] memo, int k) {
        if (i >= 0 && i < m && j >= 0 && j < n
                && split(i) + split(j) <= k
                && memo[i * n + j] == 0) {
            return true;
        }
        return false;
    }

    /**
     * 将整数按位相加
     *
     * @param n
     * @return
     */
    public int split(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;

            n /= 10;
        }
        return sum;
    }

    /**
     * 12.矩阵中的路径
     *
     * @param board 矩阵
     * @param word  路径
     * @return 返回是否存在
     */
    public boolean exist(char[][] board, String word) {
        int[] memo = new int[board.length * board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exist(board, memo, i, j, word, 0))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int[] memo, int i, int j, String word, int index) {
        if (i < 0 || i >= board.length) return false;
        if (j < 0 || j >= board[0].length) return false;
        if (memo[i * board[0].length + j] > 0) return false;
        boolean ret = false;
        if (index < word.length() && board[i][j] == word.charAt(index) && index == word.length() - 1) {
            ret = true;
        } else if (index < word.length() && board[i][j] == word.charAt(index)) {
            memo[i * board[0].length + j]++;
            ret = exist(board, memo, i + 1, j, word, index + 1) ||
                    exist(board, memo, i - 1, j, word, index + 1) ||
                    exist(board, memo, i, j + 1, word, index + 1) ||
                    exist(board, memo, i, j - 1, word, index + 1);
            if (!ret) {
                memo[i * board[0].length + j]--;
            }
        }
        return ret;
    }

    /**
     * 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 0) return -1;
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int pre1 = 0, pre2 = 1, t = 0;
        for (int i = 2; i < n + 1; i++) {
            t = (pre1 + pre2) % (1000000007);
            pre1 = pre2;
            pre2 = t;
        }
        return t;
    }

    /**
     *  用两个栈模拟队列
     */

    /**
     * 7.重建二叉树
     *
     * @param preorder 前序数组
     * @param inorder  中序数组
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int lo1, int hi1, int[] inorder, int lo2, int hi2) {
        if (lo1 == hi1) {
            TreeNode end = new TreeNode(preorder[lo1]);
            end.left = null;
            end.right = null;
            return end;
        } else if (lo1 > hi1) {
            return null;
        }
        int pivot = preorder[lo1];
        int leftLength = 0;
        for (int i = lo2; i <= hi2; i++) {
            if (inorder[i] == pivot)
                break;
            leftLength++;
        }
        TreeNode root = new TreeNode(pivot);
        root.left = buildTree(preorder, lo1 + 1, lo1 + leftLength, inorder, lo2, lo2 + leftLength - 1);
        root.right = buildTree(preorder, lo1 + leftLength + 1, hi1, inorder, lo2 + leftLength + 1, hi2);
        return root;
    }

    /**
     * 6.从尾到头打印链表
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        p = head;
        int[] ret = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            ret[i] = p.val;
            p = p.next;
        }
        return ret;
    }


    /**
     * 5.替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        char[] ret = new char[s.length() + count * 2];
        int j = s.length() - 1;
        int k = ret.length - 1;
        while (j >= 0 && k >= 0) {
            if (s.charAt(j) != ' ') {
                ret[k--] = s.charAt(j);
            } else {
                ret[k--] = '0';
                ret[k--] = '2';
                ret[k--] = '%';
            }
            j--;
        }
        return new String(ret);
    }

    /**
     * 4.二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean result = false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0;
        int j = cols - 1;
        while (i < rows && j >= 0) {
            if (target == matrix[i][j]) {
                result = true;
                break;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    /**
     * 3.数组中重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums.length < 2) return -1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int m = nums[i];
                if (nums[m] == m) {
                    // 有重复
                    return m;
                } else {
                    swap(nums, i, m);
                }
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
