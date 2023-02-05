package 练习题.leetcode.hard;

// 有 N 堆石头排成一排，第 i 堆中有stones[i]块石头。每次移动（move）需要将连续的K堆石头合并为一堆，而这个移动的成本为这K堆石头的总数。
// 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
// Leetcode题目：https://leetcode.com/problems/minimum-cost-to-merge-stones/
// 笔记：https://www.cnblogs.com/greyzeng/p/14428072.html
public class LeetCode_1000_MinimumCostToMergeStones {
    public static int mergeStones(int[] stones, int K) {
        // k和数组长度先做一次过滤
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }
        // 前缀和用来加速求L..R范围内的累加和
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        int[][][] dp = new int[n][n][K + 1];
        return f2(stones, 0, n - 1, K, 1, preSum, dp);
    }

    // f(L,R,part) -> L..R范围上一定要合成出part个数，最小代价是多少
    public static int f2(int[] arr, int L, int R, int K, int part, int[] preSum, int[][][] dp) {
        if (dp[L][R][part] != 0) {
            return dp[L][R][part];
        }
        if (L == R) {
            dp[L][R][part] = (part == 1 ? 0 : -1);
            return dp[L][R][part];
        }
        if (part == 1) {
            // part只有1块的时候
            // 需要算出当part是K份的时候，最小代价
            int pre = f2(arr, L, R, K, K, preSum, dp);
            if (pre == -1) {
                dp[L][R][part] = -1;
                return -1;
            }
            dp[L][R][part] = pre + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
            return dp[L][R][part];
        }
        // part不止一块
        // 则可以让 L..i 得到1块
        // i+1...R得到part-1块
        // 然后合并即可
        int ans = Integer.MAX_VALUE;
        for (int i = L; i < R; i += (K - 1)) {
            int cost1 = f2(arr, L, i, K, 1, preSum, dp);
            int cost2 = f2(arr, i + 1, R, K, part - 1, preSum, dp);
            if (cost1 != -1 && cost2 != -1) {
                ans = Math.min(ans, cost2 + cost1);
            } else {
                dp[L][R][part] = -1;
            }
        }
        dp[L][R][part] = ans;
        return ans;
    }

    // 暴力解法
    public static int mergeStones2(int[] stones, int K) {
        // k和数组长度先做一次过滤
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }
        // 前缀和用来加速求L..R范围内的累加和
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        return f(stones, 0, n - 1, K, 1, preSum);
    }

    // f(L,R,part) -> L..R范围上一定要合成出part个数，最小代价是多少
    public static int f(int[] arr, int L, int R, int K, int part, int[] preSum) {
        if (L == R) {
            return part == 1 ? 0 : -1;
        }
        if (part == 1) {
            // part只有1块的时候
            // 需要算出当part是K份的时候，最小代价
            int pre = f(arr, L, R, K, K, preSum);
            if (pre == -1) {
                return -1;
            }
            return pre + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
        }
        // part不止一块
        // 则可以让 L..i 得到1块
        // i+1...R得到part-1块
        // 然后合并即可
        int ans = Integer.MAX_VALUE;
        for (int i = L; i < R; i += (K - 1)) {
            int cost1 = f(arr, L, i, K, 1, preSum);
            int cost2 = f(arr, i + 1, R, K, part - 1, preSum);
            if (cost1 != -1 && cost2 != -1) {
                ans = Math.min(ans, cost2 + cost1);
            }
        }
        return ans;
    }

}
