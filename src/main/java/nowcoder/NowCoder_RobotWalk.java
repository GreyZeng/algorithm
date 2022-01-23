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
//输出包括一行四个正整数N（2<=N<=5000）、M(1<=M<=N)、K(1<=K<=5000)、P(1<=P<=N)。
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

public class NowCoder_RobotWalk {
    public static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int start = in.nextInt() - 1;
        int step = in.nextInt();
        int end = in.nextInt() - 1;
        System.out.println(ways2(len, start, step, end) % MOD);
        System.out.println(ways3(len, start, step, end) % MOD);
        System.out.println(ways4(len, start, step, end) % MOD);
        in.close();
    }

    // 暴力递归 无法AC
    // 从start开始，恰好走step步，到达end的方法数
    public static long ways(int len, int start, int step, int end) {
        if (step == 0) {
            // 没有步数可以走了
            // M正好是P位置，找到一种方法，否则没有方法
            return (start == end ? 1 : 0);
        }
        // K != 0 还有步数可以走
        long ways = 0;
        if (start == 0) {
            // 只能往右边走
            ways += ways(len, start + 1, step - 1, end);
        } else if (start == len - 1) {
            ways += ways(len, start - 1, step - 1, end);
        } else {
            ways = (ways(len, start - 1, step - 1, end) + ways(len, start + 1, step - 1, end));
        }
        return ways;
    }

    // 缓存法，可以AC
    public static long ways2(int len, int start, int step, int end) {
        long[][] dp = new long[len][step + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= step; j++) {
                dp[i][j] = -1L;
            }
        }
        p(len, start, step, end, dp);
        return dp[start][step];

    }

    private static long p(int len, int start, int step, int end, long[][] dp) {
        if (dp[start][step] != -1L) {
            return dp[start][step] % MOD;
        }
        if (step == 0) {
            // 没有步数可以走了
            // M正好是P位置，找到一种方法，否则没有方法
            long ans = (start == end ? 1 : 0);
            dp[start][step] = ans;
            return ans;
        }
        // K != 0 还有步数可以走
        long ways = 0;
        if (start == 0) {
            // 只能往右边走
            ways += (p(len, start + 1, step - 1, end, dp));
        } else if (start == len - 1) {
            ways += (p(len, start - 1, step - 1, end, dp));
        } else {
            ways = (p(len, start - 1, step - 1, end, dp) + p(len, start + 1, step - 1, end, dp));
        }
        dp[start][step] = ways;
        return ways;
    }

    // 动态规划
    public static long ways3(int len, int start, int step, int end) {
        long[][] dp = new long[len][step + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= step; j++) {
                if (j == 0) {
                    if (i == end) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 0;
                    }
                } else {
                    dp[i][j] = -1L;
                }
            }
        }
        // 从第一列开始
        // 每列从上到下
        for (int i = 1; i < step + 1; i++) {
            for (int j = 0; j < len; j++) {
                long ways = 0;
                if (j == 0) {
                    ways += dp[j + 1][i - 1];
                } else if (j == len - 1) {
                    ways += dp[j - 1][i - 1];
                } else {
                    ways = dp[j - 1][i - 1] + dp[j + 1][i - 1];
                }
                dp[j][i] = ways % MOD;
            }
        }
        return dp[start][step];
    }

    // 空间压缩
    public static long ways4(int len, int start, int step, int end) {
        // int times = step + 1;
        long[] dp = new long[len];
        // 第一列
        dp[end] = 1L;
        // 从第一列开始
        // 每列从上到下
        long tmp = 0;
        for (int i = 1; i < step + 1; i++) {
            for (int j = 0; j < len; j++) {
                long ways = dp[j];
                if (j == 0) {
                    dp[j] = dp[j + 1] % MOD;
                } else if (j == len - 1) {
                    dp[j] = tmp % MOD;
                } else {
                    dp[j] = tmp % MOD + dp[j + 1] % MOD;
                }
                tmp = ways;
            }
        }
        return dp[start];
    }
}
