package git.snippet.stock;

// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like
// (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before
// you buy again).
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
// Example:
//
// Input: [1,2,3,0,2]
// Output: 3
// Explanation: transactions = [buy, sell, cooldown, buy, sell]
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// 笔记：https://www.cnblogs.com/greyzeng/p/16182420.html
public class LeetCode_0309_BestTimeToBuyAndSellStockWithCooldown {
  public static int maxProfit(int[] arr) {
    if (arr.length < 2) {
      return 0;
    }
    int N = arr.length;
    // 处于冷冻期
    int[] cooldown = new int[N];
    // 持有股票
    int[] withStock = new int[N];
    // 不持有股票，也不处于冷冻期
    int[] noStock = new int[N];
    cooldown[0] = 0;
    withStock[0] = -arr[0];
    noStock[0] = 0;
    for (int i = 1; i < arr.length; i++) {
      withStock[i] = Math.max(withStock[i - 1], noStock[i - 1] - arr[i]);
      cooldown[i] = withStock[i - 1] + arr[i];
      noStock[i] = Math.max(noStock[i - 1], cooldown[i - 1]);
    }
    return Math.max(cooldown[N - 1], Math.max(withStock[N - 1], noStock[N - 1]));
  }

  // 空间压缩版本
  public static int maxProfit2(int[] arr) {
    if (arr.length < 2) {
      return 0;
    }
    // 处于冷冻期
    int cooldown = 0;
    // 持有股票
    int withStock = -arr[0];
    // 不持有股票，也不处于冷冻期
    int noStock = 0;

    for (int i = 1; i < arr.length; i++) {
      int next1 = Math.max(withStock, noStock - arr[i]);
      int next2 = withStock + arr[i];
      int next3 = Math.max(noStock, cooldown);
      withStock = next1;
      cooldown = next2;
      noStock = next3;
    }
    return Math.max(cooldown, Math.max(withStock, noStock));
  }
}
