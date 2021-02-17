/*You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

        If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

        Return the maximum coins you can collect by bursting the balloons wisely.



        Example 1:

        Input: nums = [3,1,5,8]
        Output: 167
        Explanation:
        nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
        coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
        Example 2:

        Input: nums = [1,5]
        Output: 10


        Constraints:

        n == nums.length
        1 <= n <= 500
        0 <= nums[i] <= 100*/
package leetcode;

public class LeetCode_0312_BurstBalloons {
    // 暴力递归，LeetCode上会直接超时
    public static int maxCoins(int[] nums) {
        // 设置辅助数组的原因是让每个气球打爆的情况无差别（无需额外讨论边界的情况）
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        arr[nums.length + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, nums.length);
        return p(arr, 1, nums.length);
    }

    // L...R范围内，打爆气球的最大分值是
    public static int p(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L - 1] * arr[L] * arr[L + 1];
        }
        // 讨论最后打爆L和R的情况
        int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + p(arr, L + 1, R), arr[L - 1] * arr[R] * arr[R + 1] + p(arr, L, R - 1));
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + p(arr, L, i - 1) + p(arr, i + 1, R));
        }
        return max;
    }

    public static int maxCoins2(int[] nums) {
        // 设置辅助数组的原因是让每个气球打爆的情况无差别（无需额外讨论边界的情况）
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);
        int[][] dp = new int[arr.length][arr.length];
        for (int L = n; L >= 1; L--) {
            dp[L][L] = arr[L - 1] * arr[L] * arr[L + 1];
            for (int R = L + 1; R <= n; R++) {
                int max = Math.max(arr[L - 1] * arr[L] * arr[R + 1] + dp[L + 1][R], arr[L - 1] * arr[R] * arr[R + 1] + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
                dp[L][R] = max;
            }
        }
        return dp[1][n];
    }
}
