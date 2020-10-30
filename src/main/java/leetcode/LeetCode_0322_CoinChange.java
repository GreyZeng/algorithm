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

import java.util.ArrayList;
import java.util.List;

// TODO 记忆化搜索 斜率优化
// 暴力解
public class LeetCode_0322_CoinChange {
    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 过滤一遍coins，比amount大的都不要,因为没有意义
        List<Integer> av = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i : coins) {
            if (i <= amount) {
                av.add(i);
                max = Math.max(max, i);
            }
        }
        // 过滤掉后可以用的硬币数量为空，则直接返回-1
        if (av.isEmpty()) {
            return -1;
        }
        if (amount % max == 0) {
            return amount / max;
        }
        int size = av.size();
        int[][] dp = new int[size][amount + 1];
        int num;
        for (int j = 1; j <= amount; j++) {
            num = av.get(0);
            if (j % num != 0) {
                dp[0][j] = -1;
            } else {
                dp[0][j] = j / num;
            }
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                num = av.get(i);


                // 先假设i-1已经把所有amount都搞定了
                // dp[i][j] 自然就等于dp[i-1][j]
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 走到这里，如果dp[i][j]还是系统最大值，则说明dp[i-1][j]其实是搞不定的

                if (j - num >= 0 && dp[i][j - num] != -1) {

                    dp[i][j] = Math.min(dp[i][j], dp[i][j - num] + 1);
                }
                if (dp[i][j] == Integer.MAX_VALUE) {
                    dp[i][j] = -1;
                }
            }
        }
        return dp[size - 1][amount];
    }


}
