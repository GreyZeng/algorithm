package git.snippet.leetcode;

// 旋变字符串
// 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
// 如果字符串的长度为 1 ，算法停止
// 如果字符串的长度 > 1 ，执行下述步骤：
// 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
// 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
// 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
// 给你两个 长度相等 的字符串 s1 和s2，判断s2是否是s1的扰乱字符串。如果是，返回 true ；否则，返回 false 。
// Leetcode题目：https://leetcode.com/problems/scramble-string/
// 笔记：https://www.cnblogs.com/greyzeng/p/14447460.html
public class LeetCode_0087_ScrambleString {
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!isValid(str1, str2)) {
            return false;
        }
        int N = str1.length;
        boolean[][][] dp = new boolean[N][N][N + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][1] = (str1[i] == str2[j]);
            }
        }

        for (int k = 2; k < N + 1; k++) {
            for (int L1 = 0; L1 <= N - k; L1++) {
                for (int L2 = 0; L2 <= N - k; L2++) {
                    for (int cutPoint = 1; cutPoint < k; cutPoint++) {
                        boolean case1 = dp[L1][L2][cutPoint] && dp[L1 + cutPoint][L2 + cutPoint][k - cutPoint];
                        boolean case2 =
                                dp[L1 + cutPoint][L2][k - cutPoint] && dp[L1][L2 + k - cutPoint][cutPoint];
                        if (case1 || case2) {
                            dp[L1][L2][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][N];
    }

    // 记忆化搜索
    public static boolean isScramble2(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!isValid(str1, str2)) {
            return false;
        }
        int N = str1.length;
        // 0表示没算过
        // -1表示false
        // 1表示true
        int[][][] dp = new int[N][N][N + 1];
        return f2(str1, str2, 0, 0, N, dp);
    }

    // str1中，L1往后（包括L1）一共k个字符串 以及 str2中，L2往后（包括L2）一共k个字符串 是否互为扰动串
    private static boolean f2(char[] str1, char[] str2, int L1, int L2, int k, int[][][] dp) {
        if (dp[L1][L2][k] != 0) {
            return dp[L1][L2][k] == 1;
        }
        if (k == 1) {
            // base case， 针对这样的情况，只需要判断str1[L1], str2[L2]
            dp[L1][L2][k] = (str1[L1] == str2[L2] ? 1 : -1);
            return dp[L1][L2][k] == 1;
        }
        // 枚举第一刀的位置
        boolean ans = false;
        for (int cutPoint = 1; cutPoint < k; cutPoint++) {
            boolean case1 =
                    f2(str1, str2, L1, L2, cutPoint, dp)
                            && f2(str1, str2, L1 + cutPoint, L2 + cutPoint, k - cutPoint, dp);
            boolean case2 =
                    f2(str1, str2, L1 + cutPoint, L2, k - cutPoint, dp)
                            && f2(str1, str2, L1, L2 + k - cutPoint, cutPoint, dp);
            if (case1 || case2) {
                ans = true;
                break;
            }
        }
        dp[L1][L2][k] = ans ? 1 : -1;
        return ans;
    }

    // 暴力递归，在Leetcode上超时
    public static boolean isScramble3(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!isValid(str1, str2)) {
            return false;
        }
        return f(str1, str2, 0, 0, str2.length);
    }

    // str1中，L1往后（包括L1）一共k个字符串 以及 str2中，L2往后（包括L2）一共k个字符串 是否互为扰动串
    private static boolean f(char[] str1, char[] str2, int L1, int L2, int k) {
        if (k == 1) {
            // base case， 针对这样的情况，只需要判断str1[L1], str2[L2]
            return str1[L1] == str2[L2];
        }
        // 枚举第一刀的位置
        for (int cutPoint = 1; cutPoint < k; cutPoint++) {
            boolean case1 =
                    f(str1, str2, L1, L2, cutPoint)
                            && f(str1, str2, L1 + cutPoint, L2 + cutPoint, k - cutPoint);
            boolean case2 =
                    f(str1, str2, L1 + cutPoint, L2, k - cutPoint)
                            && f(str1, str2, L1, L2 + k - cutPoint, cutPoint);
            if (case1 || case2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        int[] map = new int[26];
        for (char c : str1) {
            map[c - 'a']++;
        }
        for (char c : str2) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
