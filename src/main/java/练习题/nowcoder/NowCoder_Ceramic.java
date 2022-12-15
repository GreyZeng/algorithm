package 练习题.nowcoder;


//链接：https://www.nowcoder.com/questionTerminal/d5c1e5ffbe124306a3a2ec5fe4139021
//来源：牛客网
//
//牛牛有一块"2*n"的空白瓷砖并且有足够多的"1*2"和"2*3"两种类型的地毯(地毯可以旋转).现在他想在满足以下条件: 地毯之间不能相互重叠,地毯不能铺出瓷砖外以及不能有空隙下铺满整个瓷砖.问你一共有多少种不同的方案并且结果模上10007输出.
//
//输入描述:
//
//第一行输入一个正整数 T .表示有 T 组数据.
//接下来 T 行,每行输入一个正整数 n.
//1<= T <= 100
//1<= n <= 100000
//
//
//
//输出描述:
//
//输出 T 行,每一行对应每组数据的输出.
//
//示例1
//输入
//
//4
//1
//2
//3
//5
//
//输出
//
//1
//2
//4
//13

import java.util.Scanner;

public class NowCoder_Ceramic {
    public static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[] arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = in.nextInt();
        }
        for (int n : arr) {
            System.out.println(ways2(n));
        }
        in.close();
    }

    // f(n)=f(n-1) + f(n-2) + f(n-3)
    private static long ways(int n) {
        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        long a = 1;
        long b = 2;
        long c = 4;
        long r = 0;
        for (int i = 4; i <= n; i++) {
            r = (a + b + c) % MOD;
            a = b;
            b = c;
            c = r;
        }
        return r;
    }

    // 优化版本
    private static long ways2(int n) {
        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        long[][] base = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        long[][] result = matrixPow(base, n - 3);
        return (4 * result[0][0] + 2 * result[1][0] + result[2][0]) % MOD;
    }

    private static long[][] matrixPow(long[][] base, int n) {
        long[][] ans = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        long[][] t = base;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrix(ans, t);
            }
            t = matrix(t, t);
            n = (n >> 1);
        }
        return ans;
    }

    private static long[][] matrix(long[][] ans, long[][] t) {
        long[][] result = new long[3][3];
        result[0][0] = (ans[0][0] * t[0][0] + ans[0][1] * t[1][0] + ans[0][2] * t[2][0]) % MOD;

        result[0][1] = (ans[0][0] * t[0][1] + ans[0][1] * t[1][1] + ans[0][2] * t[2][1]) % MOD;

        result[0][2] = (ans[0][0] * t[0][2] + ans[0][1] * t[1][2] + ans[0][2] * t[2][2]) % MOD;

        result[1][0] = (ans[1][0] * t[0][0] + ans[1][1] * t[1][0] + ans[1][2] * t[2][0]) % MOD;

        result[1][1] = (ans[1][0] * t[0][1] + ans[1][1] * t[1][1] + ans[1][2] * t[2][1]) % MOD;

        result[1][2] = (ans[1][0] * t[0][2] + ans[1][1] * t[1][2] + ans[1][2] * t[2][2]) % MOD;

        result[2][0] = (ans[2][0] * t[0][0] + ans[2][1] * t[1][0] + ans[2][2] * t[2][0]) % MOD;

        result[2][1] = (ans[2][0] * t[0][1] + ans[2][1] * t[1][1] + ans[2][2] * t[2][1]) % MOD;

        result[2][2] = (ans[2][0] * t[0][2] + ans[2][1] * t[1][2] + ans[2][2] * t[2][2]) % MOD;

        return result;
    }


}
