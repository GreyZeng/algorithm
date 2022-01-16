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
    public int backPack(int m, int[] A) {
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
        // notOver过滤出可以装的物品
        return p(m, notOver, 0, m);
    }

    // 0 ~ i - 1 已经做好选择，还剩余j，从i开始做选择，能拿到的最大不超过M的重量是多少
    public int p(int m, List<Integer> notOver, int i, int j) {
        if (i == notOver.size()) {
            if (j == 0) {
                return m;
            } else {
                return m - j;
            }
        }
        if (j == 0) {
            return m;
        }

        if (j - notOver.get(i) >= 0) {
            return Math.max(p(m, notOver, i + 1, j), p(m, notOver, i + 1, j - notOver.get(i)));
        }
        return p(m, notOver, i + 1, j);
    }

    // 缓存
    public int backPack2(int m, int[] A) {
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
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = -1;
            }
        }
        p2(m, notOver, 0, m, dp);
        return dp[0][m];
    }

    public int p2(int m, List<Integer> notOver, int i, int rest, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        int ans;
        if (i == notOver.size()) {
            if (rest == 0) {
                dp[i][0] = m;
                ans = dp[i][0];
            } else {
                dp[i][rest] = m - rest;
                ans = dp[i][rest];
            }
            return ans;
        }

        if (rest == 0) {
            dp[i][0] = m;
            ans = dp[i][0];
            return ans;
        }
        dp[i][rest] = p2(m, notOver, i + 1, rest, dp);
        ans = dp[i][rest];
        if (rest - notOver.get(i) >= 0) {
            dp[i][rest] = Math.max(ans, p2(m, notOver, i + 1, rest - notOver.get(i), dp));
            ans = dp[i][rest];
            return ans;
        }
        return ans;
    }


    // 动态规划
    public int backPack3(int m, int[] A) {
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
        int t = backPack4(m, A);
        System.out.println(t);
    }
}
