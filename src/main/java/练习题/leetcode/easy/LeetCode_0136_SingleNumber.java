package 练习题.leetcode.easy;

// https://www.cnblogs.com/greyzeng/p/15385402.html
public class LeetCode_0136_SingleNumber {

    public static int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
