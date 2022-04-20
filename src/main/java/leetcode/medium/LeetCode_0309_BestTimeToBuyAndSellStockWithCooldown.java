package leetcode.medium;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//        You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//        After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//        Example:
//
//        Input: [1,2,3,0,2]
//        Output: 3
//        Explanation: transactions = [buy, sell, cooldown, buy, sell]
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class LeetCode_0309_BestTimeToBuyAndSellStockWithCooldown {
    // i位置上交易，怎么获得最好收益，枚举i位置参与/不参与的情况
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        // 可以参考股票问题4
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[N - 1];
    }


    // 空间压缩版本
    public static int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy1 = Math.max(-prices[0], -prices[1]);
        int sell1 = Math.max(0, prices[1] - prices[0]);
        int sell2 = 0;
        for (int i = 2; i < prices.length; i++) {
            int tmp = sell1;
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(buy1, sell2 - prices[i]);
            sell2 = tmp;
        }
        return sell1;
    }

}
