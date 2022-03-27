package leetcode.medium;

public class LeetCode_0062_UniquePaths {
    // 动态规划解法
    public static int uniquePaths1(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int i, j;
        int[][] dp = new int[m][n];
        // 将单边界的情况赋值为1
        for (i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 动态规划
        for (i = 1; i < m; i++) {
            for (j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // C((m-1)/(n+m-2)) = (m+n-2)!/(m-1)!(n-1)!
    // m = k , m+n = n
    public static int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        return C(n + m - 2, m - 1);
    }

    public static int C(int n, int k) {
        long res = 1L;
        long res2 = 1L;
        int x = n;
        int y = n - k;
        long gcd;
        while (x >= k + 1 && y >= 1) {
            res *= x--;
            res2 *= y--;
            gcd = gcd(res, res2);
            res /= gcd;
            res2 /= gcd;
        }
        int f = (int) (res / res2);
        while (x >= k + 1) {
            f *= x--;
        }
        while (y >= 1) {
            f /= y--;
        }
        return f;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
