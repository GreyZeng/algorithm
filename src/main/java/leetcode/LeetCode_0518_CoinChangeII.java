//You are given coins of different denominations and a total amount of money.
// Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
//
//
//
//        Example 1:
//
//        Input: amount = 5, coins = [1, 2, 5]
//        Output: 4
//        Explanation: there are four ways to make up the amount:
//        5=5
//        5=2+2+1
//        5=2+1+1+1
//        5=1+1+1+1+1
//        Example 2:
//
//        Input: amount = 3, coins = [2]
//        Output: 0
//        Explanation: the amount of 3 cannot be made up just with coins of 2.
//        Example 3:
//
//        Input: amount = 10, coins = [10]
//        Output: 1
//
//
//        Note:
//
//        You can assume that
//
//        0 <= amount <= 5000
//        1 <= coin <= 5000
//        the number of coins is less than 500
//        the answer is guaranteed to fit into signed 32-bit integer
package leetcode;


public class LeetCode_0518_CoinChangeII {
// TODO
    public static int change(int amount, int[] coins) {
    return -1;
    }
    // 记忆化搜索
    public static int change2(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int M = coins.length + 1;
        int N = amount + 1;
        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        return p2(coins, 0, amount, dp);
    }

    // 前面0..i-1号硬币用过了，剩余rest数量没有凑够，来到i位置决策
    // 需要的方法数是多少
    public static int p2(int[] coins, int i, int rest, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        if (rest == 0) {
            dp[i][rest] = 1;
            return dp[i][rest];
        }
        if (i == coins.length) {
            dp[i][rest] = 0;
            return dp[i][rest];
        }
        int p = 0;
        for (int j = 0; coins[i] * j <= rest; j++) {
            p += p2(coins, i + 1, rest - coins[i] * j, dp);
        }
        dp[i][rest] = p;
        return dp[i][rest];
    }

    // 暴力解 过不了leetcode
    public static int change3(int amount, int[] coins) {
        return p(coins, 0, amount);
    }

    // 前面0..i-1号硬币用过了，剩余rest数量没有凑够，来到i位置决策
    // 需要的方法数是多少
    public static int p(int[] coins, int i, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (i == coins.length) {
            return 0;
        }
        int p = 0;
        for (int j = 0; coins[i] * j <= rest; j++) {
            p += p(coins, i + 1, rest - coins[i] * j);
        }
        return p;
    }


    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {5};
        System.out.println(change(amount, coins));
    }
}
