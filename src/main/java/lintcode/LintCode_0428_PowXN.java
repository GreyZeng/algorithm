package lintcode;

public class LintCode_0428_PowXN {

    // 类fabanacci问题
    // pow X N   ( N 转成2进制）
    // 复杂度 log（N）
    public static double myPow(double x, int n) { 
        int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        double ans = 1D;
        double t = x;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                ans *= t;
            }
            pow >>= 1;
            t *= t;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= x;
        }
        if (n < 0) {
            ans = 1D / ans;
        }
        return ans;
    }
}
