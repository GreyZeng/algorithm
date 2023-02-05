package 算法.卡特兰数;

import java.math.BigInteger;

// 笔记：https://www.cnblogs.com/greyzeng/p/16735679.html
// 不同的二叉数
// TODO 卡特兰数
// https://leetcode.cn/problems/unique-binary-search-trees/
public class LeetCode_0096_UniqueBinarySearchTrees {

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

}
