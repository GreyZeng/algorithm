/*Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.

        We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.

        Since the answer may be very large, the answer should be modulo 109 + 7.

        Example 1:

        Input: n = 3, k = 0
        Output: 1
        Explanation:
        Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.


        Example 2:

        Input: n = 3, k = 1
        Output: 2
        Explanation:
        The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.


        Note:

        The integer n is in the range [1, 1000] and k is in the range [0, 1000].*/
package leetcode;

/**
 * @author Young
 * @version 1.0
 * @date 2021/2/10 20:00
 */
public class LeetCode_0629_KInversePairsArray {
    static int MOD = 1000000007;

    // 优化版本
    public static int kInversePairs(int n, int k) {
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
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;
                }
            }
        }
        return dp[n][k];
    }

    // 超时，可以优化
    public static int kInversePairs2(int n, int k) {
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
