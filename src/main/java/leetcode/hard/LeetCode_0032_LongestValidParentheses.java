/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.



        Example 1:

        Input: s = "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()".
        Example 2:

        Input: s = ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()".
        Example 3:

        Input: s = ""
        Output: 0


        Constraints:

        0 <= s.length <= 3 * 10^4
        s[i] is '(', or ')'.*/
package leetcode.hard;


// tips: 以i结尾的答案全部收集一遍
// ref: https://www.lintcode.com/problem/longest-valid-parentheses/description
// https://leetcode-cn.com/problems/longest-valid-parentheses/
public class LeetCode_0032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int ans = 0;
        // TODO
        return ans;
    }
//    public static int longestValidParentheses(String s) {
//        if (null == s || s.length() <= 1) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//        int n = str.length;
//        // 必须以i结尾最长括号长度是多少
//        int[] dp = new int[n];
//        dp[0] = 0;
//        dp[1] = (str[0] == '(' && str[1] == ')') ? 2 : 0;
//        int max = dp[1];
//        for (int i = 2; i < n; i++) {
//            if (str[i] == ')') {
//                if (str[i - 1] == '(') {
//                    dp[i] = 2 + dp[i - 2];
//                } else {
//                    if (i - dp[i - 1] - 1 >= 0 && str[i - dp[i - 1] - 1] == '(') {
//                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
//                    }
//                }
//                max = Math.max(max, dp[i]);
//            }
//        }
//        return max;
//    }
}
