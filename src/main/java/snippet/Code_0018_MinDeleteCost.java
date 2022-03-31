package snippet;

import java.util.ArrayList;
import java.util.List;

// 题目：
// 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
// 比如 s1 = "abcde"，s2 = "axbc"
// 返回 1
public class Code_0018_MinDeleteCost {

    // 如果s2很短，那么生成s2的所有子序列(2^M)，如果是s1的子串（kmp），直接删除s1 - s2 个字符就可以了 O(N*2^M)
    // 解法一
    // 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
    // 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
    // 分析：
    // 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
    // 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
    public static int minCost1(String s1, String s2) {
        // s2已经是s1的子串了，不需要删除任何字符
        if (s1.contains(s2)) {
            return 0;
        }
        List<String> s2subs = new ArrayList<>();
        p(0, s2.toCharArray(), s2subs, "");
        s2subs.sort((o1, o2) -> o2.length() - o1.length());
        for (String s2sub : s2subs) {
            if (s1.contains(s2sub)) {
                return s2.length() - s2sub.length();
            }
        }
        return s2.length();
    }

    private static void p(int i, char[] str, List<String> subs, String s) {
        if (i == str.length) {
            subs.add(s);
        } else {
            p(i + 1, str, subs, s);
            p(i + 1, str, subs, s + str[i]);
        }
    }


    // 解法二
    // 如果s1和s2都很长，那么就生成s1的所有子串，然后和s2求编辑距离（只有删除行为，增加和代替的代价无穷大）O(N^2*M)
    public static int minCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                ans = Math.min(ans, distance(str2, s1.substring(start, end).toCharArray()));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    // 求str2到s1sub的编辑距离
    // 假设编辑距离只有删除动作且删除一个字符的代价为1
    public static int distance(char[] str2, char[] s1sub) {
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        // dp[i][j]的含义：
        // str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
        // 可能性一：
        // str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
        // 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
        // 可能性二：
        // str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
        // 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
        // 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }
        return dp[row - 1][col - 1];
    }

    // 解法二的优化
    public static int minCost3(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        char[] str2 = s2.toCharArray();
        char[] str1 = s1.toCharArray();
        int M = str2.length;
        int N = str1.length;
        int[][] dp = new int[M][N];
        int ans = M;
        for (int start = 0; start < N; start++) { // 开始的列数
            dp[0][start] = str2[0] == str1[start] ? 0 : M;
            for (int row = 1; row < M; row++) {
                dp[row][start] = (str2[row] == str1[start] || dp[row - 1][start] != M) ? row : M;
            }
            ans = Math.min(ans, dp[M - 1][start]);
            // 以上已经把start列，填好
            // 以下要把dp[...][start+1....N-1]的信息填好
            // start...end end - start +2
            for (int end = start + 1; end < N && end - start < M; end++) {
                // 0... first-1 行 不用管
                int first = end - start;
                dp[first][end] = (str2[first] == str1[end] && dp[first - 1][end - 1] == 0) ? 0 : M;
                for (int row = first + 1; row < M; row++) {
                    dp[row][end] = M;
                    if (dp[row - 1][end] != M) {
                        dp[row][end] = dp[row - 1][end] + 1;
                    }
                    if (dp[row - 1][end - 1] != M && str2[row] == str1[end]) {
                        dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
                    }
                }
                ans = Math.min(ans, dp[M - 1][end]);
            }
        }
        return ans;
    }


    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {


        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            int ans3 = minCost3(str1, str2);
            if (ans1 != ans2 || ans1 != ans3) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
