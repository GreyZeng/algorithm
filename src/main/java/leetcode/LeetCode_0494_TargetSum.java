/*You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
For each integer, you should choose one from + and - as its new symbol.

		Find out how many ways to assign symbols to make sum of integers equal to target S.

		Example 1:

		Input: nums is [1, 1, 1, 1, 1], S is 3.
		Output: 5
		Explanation:

		-1+1+1+1+1 = 3
		+1-1+1+1+1 = 3
		+1+1-1+1+1 = 3
		+1+1+1-1+1 = 3
		+1+1+1+1-1 = 3

		There are 5 ways to assign symbols to make the sum of nums be target 3.


		Constraints:

		The length of the given array is positive and will not exceed 20.
		The sum of elements in the given array will not exceed 1000.
		Your output answer is guaranteed to be fitted in a 32-bit integer.*/
package leetcode;

public class LeetCode_0494_TargetSum {
    public static int findTargetSumWays(int[] arr, int sum) {
        int max = 0;
        for (int i : arr) {
            max += i;
        }
        if (sum > max) {
            return 0;
        }
        int M = arr.length + 1;
        int N = max * 2 + 1;
        int[][] dp = new int[M][N];
        dp[M - 1][max] = 1;
        for (int i = M - 2; i >= 0; i--) {
            for (int j = -max; j <= max; j++) {
                if (j + max + arr[i] < N) {
                    dp[i][j + max] = dp[i + 1][j + max + arr[i]];
                }
                if (j + max - arr[i] >= 0) {
                    dp[i][j + max] += dp[i + 1][j + max - arr[i]];
                }
            }
        }
        return dp[0][sum + max];
    }

    public static int findTargetSumWays2(int[] arr, int sum) {
        return process(arr, 0, sum);
    }

    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // i位置添加+号
        int p1 = process(arr, i + 1, rest + arr[i]);
        // i位置添加-号
        int p2 = process(arr, i + 1, rest - arr[i]);
        return p1 + p2;
    }
}
