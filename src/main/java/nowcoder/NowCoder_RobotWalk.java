//链接：https://www.nowcoder.com/questionTerminal/54679e44604f44d48d1bcadb1fe6eb61
//来源：牛客网
//
//假设有排成一行的N个位置，记为1~N，开始时机器人在M位置，
// 机器人可以往左或者往右走，如果机器人在1位置，那么下一步机器人只能走到2位置，
// 如果机器人在N位置，那么下一步机器人只能走到N-1位置。
// 规定机器人只能走k步，最终能来到P位置的方法有多少种。
// 由于方案数可能比较大，所以答案需要对1e9+7取模。
//
//输入描述:
//输入包括一行四个正整数N（2<=N<=5000）、M(1<=M<=N)、K(1<=K<=5000)、P(1<=P<=N)。
//
//
//输出描述:
//输出一个整数，代表最终走到P的方法数对10^9+7取模后的值。
//示例1
//输入
//5 2 3 3
//输出
//3
//说明
//1).2->1,1->2,2->3
//
//2).2->3,3->2,2->3
//
//3).2->3,3->4,4->3
//示例2
//输入
//1000 1 1000 1
//输出
//591137401
//说明
//注意答案要取模
//
//备注:
//时间复杂度O(n*k),空间复杂度O(N)
package nowcoder;

import java.util.Scanner;

// 笔记：https://www.cnblogs.com/greyzeng/p/16837512.html
public class NowCoder_RobotWalk {
    public static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int start = in.nextInt() - 1;
        int step = in.nextInt();
        int end = in.nextInt() - 1;
        System.out.println(ways4(len, start, step, end) % MOD);
        in.close();
    }


    // 暴力递归
    public static long ways(int len, int start, int step, int end) {
        if (step == 0) {
            if (start == end) {
                return 1L;
            }
            return 0L;
        }
        // step不止一步
        if (start == 0) {
            return ways(len, start + 1, step - 1, end);
        } else if (start == len - 1) {
            return ways(len, start - 1, step - 1, end);
        } else {
            return (ways(len, start - 1, step - 1, end) + ways(len, start + 1, step - 1, end));
        }
    }

    // 动态规划-缓存法
    public static long ways2(int len, int start, int step, int end) {
        long[][] dp = new long[len][step + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= step; j++) {
                dp[i][j] = -1L;
            }
        }
        dp(len, start, step, end, dp);
        return dp[start][step];
    }

    private static long dp(int len, int start, int step, int end, long[][] dp) {
        if (dp[start][step] != -1L) {
            return dp[start][step] % MOD;
        }
        if (step == 0) {
            dp[start][step] = start == end ? 1L : 0L;
            return dp[start][step];
        }
        long ans;
        // step不止一步
        if (start == 0) {
            ans = dp(len, start + 1, step - 1, end, dp);
        } else if (start == len - 1) {
            ans = dp(len, start - 1, step - 1, end, dp);
        } else {
            ans = (dp(len, start - 1, step - 1, end, dp) + dp(len, start + 1, step - 1, end, dp));
        }
        dp[start][step] = ans;
        return ans;
    }

    // 动态规划版本
    public static long ways3(int len, int start, int step, int end) {
        long[][] dp = new long[len][step + 1];
        // 填好第0列
        dp[end][0] = 1L;
        // 判断边界
//        dp[0][1] = dp[1][0];
//        dp[len - 1][1] = dp[len - 2][0];
        // 第一行
        for (int j = 1; j < step + 1; j++) {
            for (int i = 0; i < len; i++) {
                if (i == 0) {
                    dp[i][j] = dp[1][j - 1];
                } else if (i == len - 1) {
                    dp[i][j] = dp[len - 2][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] % MOD + dp[i + 1][j - 1] % MOD;
                }
            }
        }
        return dp[start][step];
    }

    // 空间压缩
    public static long ways4(int len, int start, int step, int end) {
        long[] dp = new long[len];
        dp[end] = 1L;
        long tmp = 0;
        for (int j = 1; j < step + 1; j++) {
            for (int i = 0; i < len; i++) {
                long ways = dp[i];
                if (i == 0) {
                    dp[i] = dp[1] % MOD;
                } else if (i == len - 1) {
                    dp[i] = tmp % MOD;
                } else {
                    dp[i] = tmp % MOD + dp[i + 1] % MOD;
                }
                tmp = ways;
            }
        }
        return dp[start];
    }
}
