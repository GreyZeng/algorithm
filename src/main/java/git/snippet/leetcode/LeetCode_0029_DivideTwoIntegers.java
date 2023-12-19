package git.snippet.leetcode;

// 记住结论即可
// 位运算实现除法
// https://leetcode.com/problems/divide-two-integers
// 进阶：只用位运算实现加减乘
// 笔记：https://www.cnblogs.com/greyzeng/p/16637476.html
public class LeetCode_0029_DivideTwoIntegers {

    // 原始加法就是：无进位信息（异或） 结合(+) 进位信息
    public int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 第一次进入这个循环，得到的是原始 a 和 原始 b 的异或结果，即无进位信息相加的结果
            // 除了第一次，后面都是把a 和 b 相加的进位信息累加到 sum 中
            sum = a ^ b;
            // a & b -> 只有 a 和 b 对应的位置都是 1 的情况下，才会是1，其他情况都是0
            // 而 a 和 b 对应位置都是 1 的情况下，也正好是进位信息会产生的地方
            // << 1 表示把进位信息进位到高位进行累加
            // 如果得到的结果不为 0 说明肯定有进位信息
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 某个数n的相反数就是 ~n + 1，由于不能用+号
    // 所以是 add(~n,1)
    public int negNum(int n) {
        return add(~n, 1);
    }

    public int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // 参考小学算乘法的过程。
    // 比如 `a = 12`，`b = 22`，`a * b`通过如下方式计算：
    // **b 的二进制值(10110)从右往左开始，如果 b 的某一位是 1 ，
    // 则把 a 左移一位的值加到结果中，
    // 模拟 1 * a，如果 b 的某一位是 0，
    // 则 a 左移一位的值不加入结果中。** 最后累加的结果就是`a * b`的答案。
    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            // b 的 二进制从右往左开始
            if ((b & 1) != 0) {
                // b 的某位是 1，则把 a 右移动一位的值加入进来
                res = add(res, a);
            }
            a <<= 1;
            // 带符号右移
            b >>>= 1;
        }
        return res;
    }

    public boolean isNeg(int n) {
        return n < 0;
    }

    // 实现除法
    // 假设 $a / b = c$，则 $a = b * c$，
    //用二进制来说明，如果：
    //$a = b * 2^7 + b * 2^4 + b * 2^1$，
    //则 c 的二进制一定是$10010010$。
    //同理，如果：
    //$a = b * 2^3 + b * 2^0$，
    //则 c 的二进制一定是$1001$。
    //抽象一下，如果$a = b * 2 ^ x + b * 2 ^ y + b * 2 ^ z$，则 c 的二进制表示中： x 位置，y 位置，z 位置一定是 1，其他位置都是 0。
    //所以，我们的思路可以转换成 a 是由几个 【b * 2的某次方】的结果组成，
    public int div(int x, int y) {
        // 把复数全部转换为正数来算
        int a = isNeg(x) ? negNum(x) : x;
        int b = isNeg(y) ? negNum(y) : y;
        int res = 0;
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            if ((a >> i) >= b) {
                res |= (1 << i);
                a = minus(a, b << i);
            }
        }
        return isNeg(x) ^ isNeg(y) ? negNum(res) : res;
    }
    // 主方法
    public int divide(int a, int b) {
        if (b == Integer.MIN_VALUE) {
            return a == Integer.MIN_VALUE ? 1 : 0;
        }
        // 除数不是系统最小
        if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                // leetcode的题目要求
                return Integer.MAX_VALUE;
            }
            // a == Integer.MIN_VALUE
            // 求 a / b
            // 先算 (a + 1)/b = c
            // 然后算 a - (b*c) = d
            // 然后 d / b = e
            // c + e = (a+1)/b + (a-(b*c))/b = a / b
            int c = div(add(a, 1), b);
            return add(c, div(minus(a, multi(c, b)), b));
        }
        // dividend不是系统最小，divisor也不是系统最小
        return div(a, b);
    }
    // div(a,b) a和b都不能是系统最小
}
