package leetcode.easy;
// https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/submissions/
// 参考
// https://leetcode.com/problems/3sum/
// https://www.cnblogs.com/greyzeng/p/7775192.html
public class LeetCode_0167_TwoSumII {
    public static int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{0, 0};
    }
}
