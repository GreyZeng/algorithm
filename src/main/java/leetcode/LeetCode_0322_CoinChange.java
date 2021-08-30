/*You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

        You may assume that you have an infinite number of each kind of coin.



        Example 1:

        Input: coins = [1,2,5], amount = 11
        Output: 3
        Explanation: 11 = 5 + 5 + 1
        Example 2:

        Input: coins = [2], amount = 3
        Output: -1
        Example 3:

        Input: coins = [1], amount = 0
        Output: 0
        Example 4:

        Input: coins = [1], amount = 1
        Output: 1
        Example 5:

        Input: coins = [1], amount = 2
        Output: 2


        Constraints:

        1 <= coins.length <= 12
        1 <= coins[i] <= 2^31 - 1
        0 <= amount <= 10^4*/
package leetcode;

// 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
// 你可以认为每种硬币的数量是无限的。
public class LeetCode_0322_CoinChange {
    public static int coinChange1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        return p(coins, 0, amount);
    }

    // 从i...往后自由选择，凑成rest的最少的硬币个数
    public static int p(int[] coins, int i, int rest) {
        if (rest == 0) {
            return 0;
        }
        // rest不为空
        if (i == coins.length) {
            // i 已经走到尽头
            return -1;
        }
        // 既没有到最后，也还有剩余
        int min = Integer.MAX_VALUE;
        // TODO
        
        return min;
    }
    // 优化枚举行为
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int N = coins.length;
        // 自由使用0..i位置的钱，拼出j元的最小张数是多少？
        int[][] dp = new int[N][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = -1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= amount; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
                if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[N - 1][amount];
    }

    // 未优化版本
    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        // 自由使用0..i位置的钱，拼出j元的最小张数是多少？
        int[][] dp = new int[N][amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = i / coins[0];
            } else {
                dp[0][i] = -1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= amount; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
                int k = 0;
                while (j - k * coins[i] >= 0 && dp[i][j - k * coins[i]] != -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - k * coins[i]] + k);
                    k++;
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[N - 1][amount];
    }

}
