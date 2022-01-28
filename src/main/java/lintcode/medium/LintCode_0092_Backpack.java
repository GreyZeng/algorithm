package lintcode.medium;

import java.util.ArrayList;
import java.util.List;

//描述
//        在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A_{i}A
//        i
//        ​
//
//
//        你不可以将物品进行切割。
// https://www.lintcode.com/problem/92/
public class LintCode_0092_Backpack {
    // 暴力递归
    public static int backPack(int m, int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return p(m, 0, arr);
    }

    // 还剩rest容量,得到的最大容量是多少
    // 从i开始到最后，得到的最大容量是多少
    public static int p(int rest, int i, int[] arr) {
        if (i == arr.length) {
            return 0;
        }
        int p1 = p(rest, i + 1, arr);
        return rest >= arr[i] ? Math.max(arr[i] + p(rest - arr[i], i + 1, arr), p1) : p1;
    }

    public static int backPack2(int m, int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][m + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return p2(m, 0, arr, dp);
    }

    // 还剩rest容量,得到的最大容量是多少
    // 从i开始到最后，得到的最大容量是多少
    public static int p2(int rest, int i, int[] arr, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        int ans = 0;
        if (i == arr.length) {
            dp[i][rest] = ans;
            return ans;
        }
        int p1 = p2(rest, i + 1, arr, dp);
        ans = rest >= arr[i] ? Math.max(arr[i] + p2(rest - arr[i], i + 1, arr, dp), p1) : p1;
        dp[i][rest] = ans;
        return ans;
    }

    // 动态规划
    public static int backPack3(int m, int[] A) {
        if (null == A || A.length == 0 || m == 0) {
            return 0;
        }
        List<Integer> notOver = new ArrayList<>();
        int sum = 0;
        boolean over = true;
        for (int a : A) {
            if (a == m) {
                // 如果有一个物品正好等于背包容量，直接返回
                return m;
            } else if (a < m) {
                over = false;
                notOver.add(a);
                sum += a;
            }
        }
        if (over) {
            // 物品重量都大于背包重量，直接返回0
            return 0;
        }
        if (sum <= m) {
            // 能拿的货物之和的重量都没有超过m
            // 直接返回货品重量之和
            return sum;
        }
        int n = notOver.size();
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j < m + 1; j++) {
            dp[n][j] = m - j;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = m;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                int t = j - notOver.get(i);
                if (t >= 0) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][t]);
                }
            }
        }
        return dp[0][m];
    }

    // 动态规划+压缩数组优化
    // TODO
    public static int backPack4(int m, int[] A) {
        if (null == A || A.length == 0 || m == 0) {
            return 0;
        }
        // 离m最近的比m小的值
        int[] dp = new int[m + 1];
        for (int k : A) {
            for (int j = m; j >= 0; j--) {
                if (j >= k) {
                    dp[j] = Math.max(dp[j], dp[j - k] + k);
                }
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        int m = 10;
        int[] A = {3, 4, 8, 5};
        System.out.println(backPack(m, A));
        System.out.println(backPack2(m, A));
        System.out.println(backPack3(m, A));
        System.out.println(backPack4(m, A));
    }
}
