package git.snippet.leetcode;

// 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数
// 逆序对的定义如下：对于数组的第i个和第j个元素，如果满i<j且a[i]>a[j]，则其为一个逆序对，否则不是
// 由于答案可能很大，只需要返回 答案 mod 10^9+ 7 的值
// Leetcode题目：https://leetcode.com/problems/k-inverse-pairs-array/
// 笔记：https://www.cnblogs.com/greyzeng/p/14417917.html
public class LeetCode_0629_KInversePairsArray {

    // 优化版本
    public static int kInversePairs(int n, int k) {
        final int MOD = 1000000007;
        // dp[i][j] : 1 ...i 范围内，形成j个逆序对有多少种方式
        // dp[0][...] 弃而不用，因为没有意义
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            // 1....i范围内，形成0个逆序对的数组只有一个(按顺序排列那个）
            dp[i][0] = 1;
        }
        // dp[i][j] 普遍位置
        // 按照i依次从i位置放到1位置，一共可以生成多少逆序对来做
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 优化
                // 情况1：
                // dp[7][3]
                // 7放倒数第一，dp[7][3] = dp[6][3]
                // 7放倒数第二，dp[7][3] = dp[6][2]
                // 7放倒数第三，dp[7][3] = dp[6][1]
                // 7放倒数第四，dp[7][3] = dp[6][0]
                // dp[7][4]
                // 7放倒数第一，dp[7][4] = dp[6][4]
                // 7放倒数第二，dp[7][4] = dp[6][3]
                // 7放倒数第三，dp[7][4] = dp[6][2]
                // 7放倒数第四，dp[7][4] = dp[6][1]
                // 7放倒数第五，dp[7][4] = dp[6][0]
                // 所以 情况1： dp[i][j] = dp[i][j-1] + dp[i-1][j]
                // 情况2：dp[7][9]
                // 7放倒数第一，dp[7][9] = dp[6][9]
                // 7放倒数第二，dp[7][9] = dp[6][8]
                // 7放倒数第三，dp[7][9] = dp[6][7]
                // 7放倒数第四，dp[7][9] = dp[6][6]
                // 7放倒数第五，dp[7][9] = dp[6][5]
                // 7放倒数第六，dp[7][9] = dp[6][4]
                // 7放倒数第七，dp[7][9] = dp[6][3]
                // dp[7][8]
                // 7放倒数第一，dp[7][8] = dp[6][8]
                // 7放倒数第二，dp[7][8] = dp[6][7]
                // 7放倒数第三，dp[7][8] = dp[6][6]
                // 7放倒数第四，dp[7][8] = dp[6][5]
                // 7放倒数第五，dp[7][8] = dp[6][4]
                // 7放倒数第六，dp[7][8] = dp[6][3]
                // 7放倒数第七，dp[7][8] = dp[6][2]
                // 情况2 ： j>=i 下发生
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                if (j >= i) {
                    // dp[i][j] 和 dp[i-1][j-1]有可能是mod完毕的结果，所以仅仅看
                    // dp[i][j] 和 dp[i-1][j-1]不知道原始值大小，所以让dp[i][j] + MOD后再去减dp[i - 1][j - i]
                    // 这样不至于到负数！！！
                    dp[i][j] = (dp[i][j] + MOD - dp[i - 1][j - i]) % MOD;
                }
            }
        }
        return dp[n][k];
    }

    // 超时，可以优化
    public static int kInversePairs2(int n, int k) {
        final int MOD = 1000000007;
        // dp[i][j] : 1 ...i 范围内，形成j个逆序对有多少种方式
        // dp[0][...] 弃而不用，因为没有意义
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            // 1....i范围内，形成0个逆序对的数组只有一个(按顺序排列那个）
            dp[i][0] = 1;
        }
        // dp[i][j] 普遍位置
        // 按照i依次从i位置放到1位置，一共可以生成多少逆序对来做
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 通过7放在哪个位置来确定
                // 情况1：dp[7][3]
                // 7放倒数第一，dp[7][3] = dp[6][3]
                // 7放倒数第二，dp[7][3] = dp[6][2]
                // 7放倒数第三，dp[7][3] = dp[6][1]
                // 7放倒数第四，dp[7][3] = dp[6][0]
                // 情况2：dp[7][7]
                // 7放倒数第一，dp[7][7] = dp[6][7]
                // 7放倒数第二，dp[7][7] = dp[6][6]
                // 7放倒数第三，dp[7][7] = dp[6][5]
                // 7放倒数第四，dp[7][7] = dp[6][4]
                // 7放倒数第五，dp[7][7] = dp[6][3]
                // 7放倒数第六，dp[7][7] = dp[6][2]
                // 7放倒数第七，dp[7][7] = dp[6][1]
                for (int l = j; l >= Math.max(0, j - i + 1); l--) {
                    dp[i][j] += dp[i - 1][l];
                    dp[i][j] %= MOD;
                }
            }
        }
        return dp[n][k];
    }
}
