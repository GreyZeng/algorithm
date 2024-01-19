package git.snippet.bit;

// Given an integer array nums where every element appears three times except for one,
// which appears exactly once. Find the single element and return it.
// You must implement a solution with a linear runtime complexity and use only constant extra space.
// https://www.cnblogs.com/greyzeng/p/15385402.html
// NowCoder_FindOneInK 的特例之一而已
// leetcode: https://leetcode.com/problems/single-number-ii/
public class LeetCode_0137_SingleNumberII {
    public int singleNumber(int[] nums) {
        int[] helper = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                helper[i] += ((num >>> i) & 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (helper[i] % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

}
