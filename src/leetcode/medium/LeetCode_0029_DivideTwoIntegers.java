package leetcode.medium;

// 记住结论即可
// 只用位运算实现加减乘除
// https://leetcode.cn/problems/divide-two-integers
// 笔记：
public class LeetCode_0029_DivideTwoIntegers {
    public static void main(String[] args) {
        int a = -13;
        System.out.println(a >>> 1);
        System.out.println(a >> 1);
        System.out.println(Integer.MIN_VALUE / (-1));
        System.out.println(Integer.MIN_VALUE);
    }

    // 原始加法就是：无进位信息（异或）+进位信息
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 异或运算就是无进位相加
            sum = a ^ b;
            // 进位信息
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 某个数n的相反数就是 ~n + 1，由于不能用+号
    // 所以是 add(~n,1)
    public static int negNum(int n) {
        return add(~n, 1);
    }


    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // 参考小学算乘法的过程。
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    // 全部转成整数来计算
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            if ((x >> i) >= y) {
                // 之所以不用 y << , 是因为容易越界。
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }


    public static int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 除数不是系统最小
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == negNum(1)) {
                // leetcode的题目要求
                return Integer.MAX_VALUE;
            }
            int res = div(add(dividend, 1), divisor);
            return add(res, div(minus(dividend, multi(res, divisor)), divisor));
        }
        // dividend不是系统最小，divisor也不是系统最小
        return div(dividend, divisor);
    }
    // div(a,b) a和b都不能是系统最小
}
