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


public class LeetCode_0322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // dp[i][j] 包含i号硬币搞定j数量钱的最小拼凑数量
        for (int i = 1; i < amount + 1; i++) {
            // 没有硬币了，如果amount数量不为0，就一定没有拼凑的机会
            dp[N][i] = -1;
        }

        
        
        return -1;
    }


}
