// 链接：https://www.nowcoder.com/questionTerminal/e2696bb900ce41cda8b060768e61f796
// 来源：牛客网
//
// 假设农场中成熟的母牛每年只会生 1 头小母牛，并且永远不会死。第一年农场中有一只成熟的母牛，
// 从第二年开始，母牛开始生小母牛。
// 每只小母牛 3 年之后成熟又可以生小母牛。
// 给定整数 n，求出 n 年后牛的数量。
//
// 输入描述:
// 输入一个整数 n。
//
//
// 输出描述:
// 输出 n 年后牛的数量对 1e9 + 7 取模的值。
// 示例1
// 输入
// 6
// 输出
// 9
//
// 备注:
// 1≤n≤1e18
package git.snippet.nowcoder;

import java.util.Scanner;

// 第一年农场有1只成熟的母牛A，往后的每年：
// 1）每一只成熟的母牛都会生一只母牛
// 2）每一只新出生的母牛都在出生的第三年成熟
// 3）每一只母牛永远不会死
// 返回N年后牛的数量
// TIPS : F(N) = F(N-1) + F(N-3) （最后F(N-3)表示三阶问题，同理，F(N) = F(N-1) + ... + F(N-K)
// 表示某个K阶问题
// 假设母牛10年后会死，表达式：
// F(N) = F(N-1) + F(N-3) - F(N-10)
// N年后牛的数量等于，去年牛的数量，和3年前牛的数量（因为3年后牛可以生小牛）
public class NowCoder_Cow {

    static final int MOD = 1_000_000_007;

    // F(N) = F(N-1) + F(N-3)
    // 3阶
    public static long cow(long N) {

        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2 || N == 3) {
            return N;
        }
        long[][] matrix = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        long[][] res = powerN(N - 3, matrix);
        return (3 * res[0][0] + 2 * res[1][0] + res[2][0]) % MOD;
    }

    public static long[][] powerN(long n, long[][] m) {
        long[][] ans = new long[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        long[][] t = m;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrix(ans, t);
            }
            t = matrix(t, t);
            n >>= 1;
        }
        return ans;
    }

    // 3阶矩阵乘法
    public static long[][] matrix(long[][] A, long[][] B) {
        long[][] result = new long[3][3];
        result[0][0] = A[0][0] * B[0][0] % MOD + A[0][1] * B[1][0] % MOD + A[0][2] * B[2][0] % MOD;
        result[0][1] = A[0][0] * B[0][1] % MOD + A[0][1] * B[1][1] % MOD + A[0][2] * B[2][1] % MOD;
        result[0][2] = A[0][0] * B[0][2] % MOD + A[0][1] * B[1][2] % MOD + A[0][2] * B[2][2] % MOD;
        result[1][0] = A[1][0] * B[0][0] % MOD + A[1][1] * B[1][0] % MOD + A[1][2] * B[2][0] % MOD;
        result[1][1] = A[1][0] * B[0][1] % MOD + A[1][1] * B[1][1] % MOD + A[1][2] * B[2][1] % MOD;
        result[1][2] = A[1][0] * B[0][2] % MOD + A[1][1] * B[1][2] % MOD + A[1][2] * B[2][2] % MOD;
        result[2][0] = A[2][0] * B[0][0] % MOD + A[2][1] * B[1][0] % MOD + A[2][2] * B[2][0] % MOD;
        result[2][1] = A[2][0] * B[0][1] % MOD + A[2][1] * B[1][1] % MOD + A[2][2] * B[2][1] % MOD;
        result[2][2] = A[2][0] * B[0][2] % MOD + A[2][1] * B[1][2] % MOD + A[2][2] * B[2][2] % MOD;
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(cow(in.nextLong()));
        in.close();
    }
}
