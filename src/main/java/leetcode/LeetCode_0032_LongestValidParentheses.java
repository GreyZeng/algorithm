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
package leetcode;


// tips: 以i结尾的答案全部收集一遍
public class LeetCode_0032_LongestValidParentheses {

    public static int longestValidParentheses(String str) {
        if (null == str || str.length() <= 1) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N];
        dp[1] = (chars[0] == '(' && chars[1] == ')') ? 2 : 0;
        int max = dp[1];
        for (int i = 2; i < N; i++) {
            if (chars[i] == ')') {
                if (i - dp[i - 1] - 1 >= 0 && chars[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + (2 + ((i - dp[i - 1] - 2) > 0 ? dp[i - dp[i - 1] - 2] : 0));
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        String st = ")()())()()(";
        int i = longestValidParentheses(st);
        System.out.println(i);
    }

}
