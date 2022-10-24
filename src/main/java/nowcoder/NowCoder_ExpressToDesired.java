package nowcoder;
// 笔记：https://www.cnblogs.com/greyzeng/p/16823219.html
// https://www.nowcoder.com/questionTerminal/ec646f8954e64453bf1a9899a27cfbd6
//tips:
//        范围上尝试的模型
//        f(L,R,期待)
//        优化：
//        两张二维表 T表，F表
public class NowCoder_ExpressToDesired {


    // 暴力解法
    public static int getDesiredNum(String exp, boolean desired) {
        char[] str = exp.toCharArray();
        int N = str.length;
        if (errorFormat(str, N)) {
            return 0;
        }
        return p(str, 0, N - 1, desired);
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

    public static int p(char[] exp, int L, int R, boolean desired) {
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
                    res += p(exp, L, i - 1, true) * p(exp, i + 1, R, true);
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

    // 动态规划版本
    public static int desiredDp(String exp, boolean desired) {
        char[] str = exp.toCharArray();
        int N = str.length;
        if (errorFormat(str, N)) {
            return 0;
        }
        //tMap[i][j] 表示i到j能组成true的数量是多少，所以对角线下半区无用
        int[][] tMap = new int[N][N];
        //fMap[i][j] 表示i到j能组成false的数量是多少，所以对角线下半区无用
        int[][] fMap = new int[N][N];

        for (int i = 0; i < N; i += 2) {
            // 忽视符号位
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }
        for (int L = N - 3; L >= 0; L -= 2) {
            for (int R = L + 2; R < N; R += 2) {
                for (int i = L + 1; i < R; i += 2) {
                    if (str[i] == '&') {
                        tMap[L][R] += tMap[L][i - 1] * tMap[i + 1][R];
                        fMap[L][R] += tMap[L][i - 1] * fMap[i + 1][R];
                        fMap[L][R] += fMap[L][i - 1] * fMap[i + 1][R];
                        fMap[L][R] += fMap[L][i - 1] * tMap[i + 1][R];
                    } else if (str[i] == '|') {
                        tMap[L][R] += tMap[L][i - 1] * fMap[i + 1][R];
                        tMap[L][R] += tMap[L][i - 1] * tMap[i + 1][R];
                        tMap[L][R] += fMap[L][i - 1] * tMap[i + 1][R];
                        fMap[L][R] += fMap[L][i - 1] * fMap[i + 1][R];
                    } else {
                        tMap[L][R] += tMap[L][i - 1] * fMap[i + 1][R];
                        tMap[L][R] += fMap[L][i - 1] * tMap[i + 1][R];
                        fMap[L][R] += fMap[L][i - 1] * fMap[i + 1][R];
                        fMap[L][R] += tMap[L][i - 1] * tMap[i + 1][R];
                    }
                }
            }
        }
        return desired ? tMap[0][N - 1] : fMap[0][N - 1];
    }
}
