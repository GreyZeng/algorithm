/*由 n 个节点最多可组成多少个不同形态的二叉树？

        输入格式
        包含一个正整数 n。

        输出格式
        输出一个整数，表示不同形态的二叉树的个数。

        数据范围
        1≤n≤5000
        输入样例：
        2
        输出样例：
        2*/
package acwing;

import java.math.BigInteger;
import java.util.Scanner;

//https://www.acwing.com/problem/content/description/1259/
public class ACWing_1257_DifferentBTNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(num2(n));
        in.close();
    }

    // 卡特兰数
    // 1个节点 -> 1
    // 2个节点-> 2
    // 3 个节点 左边0 ，右边2，左边1，右边1，左边2，右边1   -> f(3) = f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
    private static BigInteger num(int N) {
        if (N < 0) {
            return BigInteger.ZERO;
        }
        if (N < 2) {
            return BigInteger.ONE;
        }
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        for (int i = 1, j = N + 1; i <= N; i++, j++) {
            a = a.multiply(BigInteger.valueOf(i));
            b = b.multiply(BigInteger.valueOf(j));
            BigInteger gcd = gcd(a, b);
            a = a.divide(gcd);
            b = b.divide(gcd);
        }
        return (b.divide(a)).divide(BigInteger.valueOf(N + 1));
    }

    public static BigInteger gcd(BigInteger m, BigInteger n) {
        return n.equals(BigInteger.ZERO) ? m : gcd(n, m.mod(n));
    }

    private static BigInteger num2(int N) {
        if (N < 0) {
            return BigInteger.ZERO;
        }
        if (N < 2) {
            return BigInteger.ONE;
        }
        BigInteger a = BigInteger.valueOf(N + 1);
        BigInteger b = BigInteger.valueOf(1);
        for (int i = N + 2; i <= (2 * N); i++) {
            a = a.multiply(BigInteger.valueOf(i));
        }
        for (int i = 1; i <= N; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }
        return a.divide(b).divide(BigInteger.valueOf(N + 1));
    }
}
