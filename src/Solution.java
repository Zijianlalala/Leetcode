public class Solution {
    /**
     * 283. 移动零
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = 0,j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length){
            nums[j++] = 0;
        }
    }
}
