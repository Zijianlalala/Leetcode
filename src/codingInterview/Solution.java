package codingInterview;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println(solution.replaceSpace("We are happy."));
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
        int j = s.length()-1;
        int k = ret.length-1;
        while (j >= 0 && k >= 0) {
            if (s.charAt(j) != ' ') {
                ret[k--] = s.charAt(j);
            } else{
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
