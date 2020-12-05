package codingInterview;

import DataStructure.ListNode;
import DataStructure.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fib(81));
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
            t =  (pre1 + pre2) % (1000000007);
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
