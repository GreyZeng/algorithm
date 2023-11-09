package git.snippet.catalan;

import java.math.BigInteger;

// 笔记：https://www.cnblogs.com/greyzeng/p/16735679.html
// N个节点有多少种形态的二叉树
// https://www.lintcode.com/problem/163/
public class LintCode_0163_UniqueBinarySearchTrees {

    // 卡特兰数
    // 1个节点 -> 1
    // 2个节点-> 2
    // 3 个节点 左边0 ，右边2，左边1，右边1，左边2，右边1 -> f(3) = f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
    public static int numTrees(int n) {
        if (n < 0) {
            return BigInteger.ZERO.intValue();
        }
        if (n < 2) {
            return BigInteger.ONE.intValue();
        }
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        for (int i = 1, j = n + 1; i <= n; i++, j++) {
            a = a.multiply(BigInteger.valueOf(i));
            b = b.multiply(BigInteger.valueOf(j));
            BigInteger gcd = gcd(a, b);
            a = a.divide(gcd);
            b = b.divide(gcd);
        }
        return (b.divide(a)).divide(BigInteger.valueOf(n + 1)).intValue();
    }

    public static BigInteger gcd(BigInteger m, BigInteger n) {
        return n.equals(BigInteger.ZERO) ? m : gcd(n, m.mod(n));
    }

    private static int numTrees2(int n) {
        if (n < 0) {
            return BigInteger.ZERO.intValue();
        }
        if (n < 2) {
            return BigInteger.ONE.intValue();
        }
        BigInteger a = BigInteger.valueOf(n + 1);
        BigInteger b = BigInteger.valueOf(1);
        for (int i = n + 2; i <= (2 * n); i++) {
            a = a.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= n; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }
        return a.divide(b).divide(BigInteger.valueOf(n + 1)).intValue();
    }
}
