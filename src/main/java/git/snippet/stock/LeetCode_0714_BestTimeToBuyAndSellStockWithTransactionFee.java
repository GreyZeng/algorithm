package git.snippet.stock;

// 参考股票问题5 cooldown问题
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// 笔记：https://www.cnblogs.com/greyzeng/p/16182420.html
public class LeetCode_0714_BestTimeToBuyAndSellStockWithTransactionFee {
    public static int maxProfit1(int[] arr, int fee) {
        if (arr.length < 2) {
            return 0;
        }
        int[] withStock = new int[arr.length];
        int[] noStock = new int[arr.length];
        // 持有股票
        withStock[0] = -arr[0];
        // 不持有股票
        noStock[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            withStock[i] = Math.max(withStock[i - 1], noStock[i - 1] - arr[i]);
            noStock[i] = Math.max(withStock[i - 1] + arr[i] - fee, noStock[i - 1]);
        }
        return Math.max(withStock[arr.length - 1], noStock[arr.length - 1]);
    }

    public static int maxProfit(int[] arr, int fee) {
        if (arr.length < 2) {
            return 0;
        }
        // 持有股票
        int withStock = -arr[0];
        // 不持有股票
        int noStock = 0;
        for (int i = 1; i < arr.length; i++) {
            int next1 = Math.max(withStock, noStock - arr[i]);
            int next3 = Math.max(withStock + arr[i] - fee, noStock);
            withStock = next1;
            noStock = next3;
        }
        return Math.max(withStock, noStock);
    }
}
