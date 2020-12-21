package leetcode;

public class LeetCode_0136_SingleNumber {

    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int L = nums[0];
        for (int i = 1; i < nums.length; i++) {
            L ^= nums[i];
        }
        return L;
    }

}
