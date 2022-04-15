package leetcode.hard;

// 求最长的有效括号的长度
// tips: 以i结尾的答案全部收集一遍
// ref: https://www.lintcode.com/problem/longest-valid-parentheses/description
// https://leetcode-cn.com/problems/longest-valid-parentheses/
public class LeetCode_0032_LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i]：必须以i位置符号结尾的字符串，最长有效括号串长度是多少
        int[] dp = new int[str.length];
        // dp[0] = 0, dp[1] = 0
        dp[1] = str[0] == '(' && str[1] == ')' ? 2 : 0;
        int ans = dp[1];
        for (int i = 2; i < str.length; i++) {
            if (str[i] == ')') {
                // 且右括号结尾才可能是有效括号
                if (str[i - 1] != '(') {
                    if ((i - 1) - dp[i - 1] >= 0 && str[(i - 1) - dp[i - 1]] == '(') {
                        dp[i] = dp[i - 1] + 2 + (((i - 1) - dp[i - 1] - 1) >= 0 ? dp[(i - 1) - dp[i - 1] - 1] : 0);
                    }
                } else {
                    dp[i] = 2 + dp[i - 2];
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "()(())";
        int i = longestValidParentheses(s);
        System.out.println(i);
    }
}
