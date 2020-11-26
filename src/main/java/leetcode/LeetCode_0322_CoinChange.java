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

// TODO 记忆化搜索 斜率优化
// 暴力解
public class LeetCode_0322_CoinChange {

	public int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		if (coins.length == 0) {
			return -1;
		}
		return p(coins, 0, amount);
	}

	// 用掉了0..i-1号硬币，从i号开始选，还剩下rest要处理
	public static int p(int[] coins, int index, int rest) {
		if (rest == 0) {
			return 0;
		}
		if (index == coins.length) {
			return -1;
		}
		int p = Integer.MAX_VALUE;
		for (int n = 0; n * coins[index] <= rest; n++) {
			int p1 = p(coins, index + 1, rest - n * coins[index]);
			if (p1 != -1) {
				p = Math.min(p, p1);
			}
		}
		return p;
	}

}
