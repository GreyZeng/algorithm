package leetcode.hard;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//		Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//		Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
//
//
//		Example 1:
//
//		Input: prices = [3,3,5,0,0,3,1,4]
//		Output: 6
//		Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//		Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
//		Example 2:
//
//		Input: prices = [1,2,3,4,5]
//		Output: 4
//		Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//		Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
//		Example 3:
//
//		Input: prices = [7,6,4,3,1]
//		Output: 0
//		Explanation: In this case, no transaction is done, i.e. max profit = 0.
//		Example 4:
//
//		Input: prices = [1]
//		Output: 0
//
//
//		Constraints:
//
//		1 <= prices.length <= 105
//		0 <= prices[i] <= 105
// 测评链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
public class LeetCode_0123_BestTimeToBuyAndSellStockIII {
    // 股票问题4的特例
    // tips:一定要在i位置的时候交易做完，并且，最后一次卖出时机是在i位置
    // 无法用两个最大上坡来做
    // 到了i位置，需要一个指标：0-i-1 做完一次交易并且减一个买入价格的最优值
    // [13672135]i
    public static int maxProfit(int[] prices) {
        if (null == prices) {
            return 0;
        }
        int L = prices.length;
        if (L == 1 || L == 0) {
            return 0;
        }
        int ans = 0;
        int finishPreMinusSecondMax = -prices[0];
        int min = prices[0];
        int onceMax = 0;
        for (int i = 1; i < L; i++) {
            ans = Math.max(ans, finishPreMinusSecondMax + prices[i]);
            min = Math.min(min, prices[i]);
            onceMax = Math.max(onceMax, prices[i] - min);
            finishPreMinusSecondMax = Math.max(finishPreMinusSecondMax, onceMax - prices[i]);
        }
        return ans;
    }

}
