/*There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.

A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.

Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.

 

Example 1:

Input: stones = [3,2,4,1], K = 2
Output: 20
Explanation: 
We start with [3, 2, 4, 1].
We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
We merge [4, 1] for a cost of 5, and we are left with [5, 5].
We merge [5, 5] for a cost of 10, and we are left with [10].
The total cost was 20, and this is the minimum possible.
Example 2:

Input: stones = [3,2,4,1], K = 3
Output: -1
Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
Example 3:

Input: stones = [3,5,1,2,6], K = 3
Output: 25
Explanation: 
We start with [3, 5, 1, 2, 6].
We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
We merge [3, 8, 6] for a cost of 17, and we are left with [17].
The total cost was 25, and this is the minimum possible.
 

Note:

1 <= stones.length <= 30
2 <= K <= 30
1 <= stones[i] <= 100*/
package leetcode.hard;

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
