package leetcode.hard;

// TODO
//给定一个数组nums，请将nums切三刀，切出四个部分，被切的数字不算，要求每个部分累加和都一样大，返回能不能做到
//		比如
//		输入：nums = [1,2,1,2,1,2,1]
//		输出：true
//		解释：
//		三刀的位置分别为，i = 1, j = 3, k = 5，切完后这三个位置的数不算
//		所以切出的四个部分为(1) (1) (1) (1)，所以能做到返回true
//		leetcode题目：https://leetcode.cn/problems/split-array-with-equal-sum/
// 暴力解法，直接过
public class LeetCode_0548_SplitArrayEithEqualSum {

    public static boolean splitArray(int[] nums) {
        if (nums.length < 7) {
            return false;
        }
        int[] sumLeftToRight = new int[nums.length];
        int[] sumRightToLeft = new int[nums.length];
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            sumLeftToRight[i] = s;
            s += nums[i];
        }
        s = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sumRightToLeft[i] = s;
            s += nums[i];
        }
        for (int i = 1; i < nums.length - 5; i++) {
            for (int j = nums.length - 2; j > i + 3; j--) {
                if (sumLeftToRight[i] == sumRightToLeft[j] && find(sumLeftToRight, sumRightToLeft, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean find(int[] sumLeftToRight, int[] sumRightToLeft, int l, int r) {
        int s = sumLeftToRight[l];
        int prefix = sumLeftToRight[l + 1];
        int suffix = sumRightToLeft[r - 1];
        for (int i = l + 2; i < r - 1; i++) {
            int s1 = sumLeftToRight[i] - prefix;
            int s2 = sumRightToLeft[i] - suffix;
            if (s1 == s2 && s1 == s) {
                return true;
            }
        }
        return false;
    }

}
