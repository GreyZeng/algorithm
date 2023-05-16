
package leetcode;

// 给定两个字符串S和T
// 返回S的所有子序列中
// 有多少个子序列的字面值等于T
// https://leetcode.cn/problems/distinct-subsequences/
// https://leetcode-cn.com/problems/21dk04/
// 笔记：https://www.cnblogs.com/greyzeng/p/16414324.html
public class LeetCode_0115_DistinctSubsequences {
    // leetcode超时
    public static int numDistinct1(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        return process(s.toCharArray(), t.toCharArray(), 0, 0);
    }

    // str[0....结尾]搞定t[0....结尾]
    public static int process(char[] str, char[] t, int i, int j) {
        if (j == t.length) {
            // 全部搞定了
            return 1;
        }
        if (i == str.length) {
            // 没有了，搞不定
            return 0;
        }
        // 不用i位置的去搞定
        int ans = process(str, t, i + 1, j);
        if (str[i] == t[j]) {
            ans += process(str, t, i + 1, j + 1);
        }
        return ans;
    }

    // 二维动态规划
    public static int numDistinct2(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int m = str.length;
        int n = target.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + (str[i] == target[j] ? dp[i + 1][j + 1] : 0);
            }
        }
        return dp[0][0];
    }

    // 压缩数组技巧
    public static int numDistinct3(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int m = str.length;
        int n = target.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            // 这里要注意，从左往右
            for (int j = 0; j <= n - 1; j++) {
                dp[j] += (str[i] == target[j] ? dp[j + 1] : 0);
            }

        }
        return dp[0];
    }
}
