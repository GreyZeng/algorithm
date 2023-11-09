package git.snippet.stock;

// Say you have an array for which the i-th element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete at most k transactions.
//
// Note:
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before
// you buy again).
//
// Example 1:
//
// Input: [2,4,1], k = 2
// Output: 2
// Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
// Example 2:
//
// Input: [3,2,6,5,0,3], k = 2
// Output: 7
// Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
// Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
// 笔记：https://www.cnblogs.com/greyzeng/p/16182420.html
public class LeetCode_0188_BestTimeToBuyAndSellStockIV {

    // 暴力方法
    // k> N/2 就等于无限次交易
    // 因为爬坡的数量不会大于N/2
    // dp[i][j] 0-i范围上，不超过j次交易
    // i位置如果要参与交易，只需要参加最后一次交易
    // i位置只需要最后一次交易的卖出时机
    // a. 最后一次的买入时机在i
    // b. 最后的买入时机是i-1
    public static int maxProfit1(int k, int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        if (k >= N >> 1) {
            return infinityMax(arr);
        }
        int[][] dp = new int[N][k + 1];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= k; j++) {
                // i位置不参与交易，则dp[i][j]至少是dp[i-1][j]
                dp[i][j] = dp[i - 1][j];
                for (int m = 0; m <= i; m++) {
                    // 枚举每次买入的时机
                    dp[i][j] = Math.max(dp[m][j - 1] - arr[m] + arr[i], dp[i][j]);
                }
            }
        }
        return dp[N - 1][k];
    }

    // 优化方法
    // 优化枚举
    // K> N/2 就等于无限次交易
    // 因为爬坡的数量不会大于N/2
    // dp[j][i] 0-i范围上，不超过j次交易
    // i位置如果要参与交易，只需要参加最后一次交易
    // i位置只需要最后一次交易的卖出时机
    // a. 最后一次的买入时机在i
    // b. 最后的买入时机是i-1
    // c. 最后的买入时机是i-2
    // ...
    public static int maxProfit(int k, int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        if (k >= N >> 1) {
            return infinityMax(arr);
        }
        int[][] dp = new int[N][k + 1];
        for (int j = 1; j <= k; j++) {
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                best = Math.max(dp[i][j - 1] - arr[i], best);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][k];
    }

    public static int infinityMax(int[] arr) {
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans += Math.max(arr[i] - arr[i - 1], 0);
        }
        return ans;
    }
}
