package leetcode;

public class LeetCode_0062_UniquePaths {
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
