package lintcode;
//描述
//        有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
//
//        问最多能装入背包的总价值是多大?
//
//        A[i], V[i], n, m 均为整数
//        你不能将物品进行切分
//        你所挑选的要装入背包的物品的总大小不能超过 m
//        每个物品只能取一次
//        m <= 1000m<=1000\
//        len(A),len(V)<=100len(A),len(V)<=100
//
//        样例
//        样例 1：
//
//        输入：
//
//        m = 10
//        A = [2, 3, 5, 7]
//        V = [1, 5, 2, 4]
//        输出：
//
//        9
//        解释：
//
//        装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
//
//        样例 2：
//
//        输入：
//
//        m = 10
//        A = [2, 3, 8]
//        V = [2, 5, 8]
//        输出：
//
//        10
//        解释：
//
//        装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
//
//        挑战
//        O(nm) 空间复杂度可以通过, 你能把空间复杂度优化为O(m)吗？

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2021/8/20
 * @since
 */
public class LintCode_0125_BackpackII {
    // 暴力递归版本
    public int backPackII(int m, int[] A, int[] V) {
        if (A == null || V == null || A.length == 0 || V.length == 0 || m == 0) {
            return 0;
        }
        int len = A.length;
        return p(A, V, len, 0, m);
    }

    // 0...i-1已经搞定，还剩下rest空间
    // 返回还能获取的最大价值
    public int p(int[] A, int[] V, int len, int i, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (i == len) {
            // i到结尾位置了，没办法产生任何价值了
            return 0;
        }
        // 选择i位置
        int yes = p(A, V, len, i + 1, rest - A[i]);
        // 不选择i位置
        int no = p(A, V, len, i + 1, rest);
        if (yes != -1) {
            return Math.max(no, yes + V[i]);
        }
        return no;
    }

    public int backPackII2(int bag, int[] w, int[] v) {
        if (w == null || v == null || w.length == 0 || v.length == 0 || bag == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < bag + 1; j++) {
                dp[i][j] = -1;
            }
        }
        dp2(w, v, N, 0, bag, dp);
        return dp[0][bag];
    }

    public int dp2(int[] w, int[] v, int len, int i, int rest, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        // rest空间不能为负数

        if (i == len) {
            dp[i][rest] = 0;
            return dp[i][rest];
        }
        int p1 = dp2(w, v, len, i + 1, rest, dp);
        if (rest - w[i] >= 0) {
            int p2 = dp2(w, v, len, i + 1, rest - w[i], dp);
            if (p2 != -1) { // p2 不为-1才能算做正常解
                p2 += v[i];
            }
            dp[i][rest] = Math.max(p1, p2);
            return dp[i][rest];
        }
        dp[i][rest] = p1;
        return dp[i][rest];
    }

    // 动态规划
    public int backPackII3(int bag, int[] w, int[] v) {
        if (w == null || v == null || w.length == 0 || v.length == 0 || bag == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < bag + 1; j++) {
                dp[i][j] = -1;
            }
        }
        for (int j = 0; j < bag + 1; j++) {
            dp[N][j] = 0;
        }
        // 选择i位置
        for (int i = N; i >= 0; i--) {
            for (int j = 0; j < bag + 1; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - w[i] >= 0 && dp[i + 1][j - w[i]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - w[i]] + v[i]);
                }
            }
        }
        return dp[0][bag];
    }

    // 动态规划+压缩数组
    public int backPackII4(int m, int[] w, int[] v) {
        if (w == null || v == null || w.length == 0 || v.length == 0 || m == 0) {
            return 0;
        }
        int N = w.length;
        int[] dp = new int[m + 1];

        // 选择i位置
        for (int i = 0; i < N; i++) {
            for (int j = m; j >= 0; j--) {
                if (j - w[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        int[] w = {2, 3, 5, 7};
        int[] v = {1, 5, 2, 4};
        int i = new LintCode_0125_BackpackII().backPackII4(10, w, v);
        System.out.println(i);
    }

}
