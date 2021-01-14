/*链接：https://www.nowcoder.com/questionTerminal/9690bd908d5b4f9b91640fa835ef6f4f
        来源：牛客网

        给定一个只由0（假）、1（真）、&（逻辑与）、|（逻辑或）和^（异或）五种字符组成的字符串express，再给定一个布尔值desired。求出express能有多少种组合方式，
        可以达到desired的结果。并输出你所求出的总方案数对10^9+7取模后的值。

        输入描述:
        输出两行，第一行包含一个只有0、1、&、|和^组成的字符串。其长度小于500，第二行只有一个布尔值，代表desired。


        输出描述:
        输出一个整数，表示取模后的答案。
        示例1
        输入
        1^0|0|1
        false
        输出
        2
        说明
        1^((0|0)|1)和1^(0|(0|1))可以得到false
        示例2
        输入
        1
        false
        输出
        0

        备注:
        时间复杂度O(n^3),空间复杂度O(n^2)。*/
package nowcoder;

import java.util.Scanner;

//tips:
//        范围上尝试的模型
//        f(L,R,期待)
//        优化：
//        两张二维表 T表，F表
public class NowCoder_ExpressToDesired {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String express = in.nextLine();
        boolean desired = in.nextBoolean();
        System.out.println(desired(express, desired));
        in.close();
    }

    static int MOD = 1_000_000_001;

    public static long desired(String express, boolean desired) {
        char[] exp = express.toCharArray();
        int N = exp.length;
        if (errorFormat(exp, N)) {
            return 0;
        }
        return p(exp, 0, N - 1, desired) % MOD;
    }

    // 初步筛选一下exp串的合法性
    public static boolean errorFormat(char[] exp, int n) {
        if ((n & 1) == 0) {
            // 表达式不能为偶数个长度
            return true;
        }
        for (int i = 0; i < n; i += 2) {
            if (exp[i] != '1' && exp[i] != '0') {
                // 0,2,4,8...n-1位置上一定只能是 1 或者 0
                return true;
            }
        }
        for (int i = 1; i < n; i += 2) {
            if (exp[i] != '|' && exp[i] != '^' && exp[i] != '&') {
                return true;
            }
        }
        return false;
    }

    public static long p(char[] exp, int L, int R, boolean desired) {
        if (L == R) {
            if (desired && exp[L] == '1') {
                return 1;
            } else if (!desired && exp[L] == '0') {
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;

        for (int i = L + 1; i < R; i++) {
            if (exp[i] == '&') {
                if (desired) {
                    res += p(exp, L, i - 1, desired) * p(exp, i + 1, R, desired);
                } else {
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, false);
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, false);
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, true);
                }
            } else if (exp[i] == '|') {
                if (desired) {
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, false);
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, true);
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, true);
                } else {
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, false);
                }
            } else {
                // exp[i] == '^'
                if (desired) {
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, false);
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, true);
                } else {
                    res += p(exp, L, i - 1, false) * p(exp, i + 1, R, false);
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, true);
                }
            }
        }
        return res;
    }

    public static long desiredDp(String express, boolean desired) {
        char[] exp = express.toCharArray();
        int N = exp.length;
        if (errorFormat(exp, N)) {
            return 0;
        }
        //tMap[i][j] 表示i到j能组成true的数量是多少，所以对角线下半区无用
        long[][] tMap = new long[N][N];
        //fMap[i][j] 表示i到j能组成false的数量是多少，所以对角线下半区无用
        long[][] fMap = new long[N][N];

        for (int i = 0; i < N; i++) {
            tMap[i][i] = exp[i] == '1' ? 1 : 0;
            fMap[i][i] = exp[i] == '0' ? 1 : 0;
        }

        // TODO
        return desired ? tMap[0][N - 1] : fMap[0][N - 1];

    }

}
