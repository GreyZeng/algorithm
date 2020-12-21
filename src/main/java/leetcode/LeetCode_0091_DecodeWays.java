package leetcode;

// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given a non-empty string containing only digits, determine the total number of ways to decode it.

// Example 1:

// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// Example 2:

// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
public class LeetCode_0091_DecodeWays {
    public static int numDecodings(String s) {
        char[] strs = s.toCharArray();
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (strs[i] == '0') {
                dp[i] = 0;
                continue;
            }
            if (strs[i] == '1' && i + 1 < strs.length) {
                dp[i] = dp[i + 1] + dp[i + 2];
                continue;
            }
            if (strs[i] == '2' && i + 1 < strs.length && (strs[i + 1] <= '6')) {
                dp[i] = dp[i + 1] + dp[i + 2];
                continue;
            }
            dp[i] = dp[i + 1];
        }
        return dp[0];
    }


    // 记忆化搜索
    public static int numDecodings2(String s) {
        char[] str = s.toCharArray();
        int N = s.length();
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            if (i < N && str[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = -1;
            }
        }
        dp[N] = 1;
        return p2(str, 0, dp);
    }

    public static int p2(char[] strs, int index, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }
        if (strs[index] == '1' && index + 1 < strs.length) {
            // 第一位是1，下一位不越界
            dp[index] = p2(strs, index + 1, dp) + p2(strs, index + 2, dp);
            return dp[index];
        }
        if (strs[index] == '2' && index + 1 < strs.length && (strs[index + 1] <= '6')) {
            // 20，21，22，23，24，25，26
            dp[index] = p2(strs, index + 1, dp) + p2(strs, index + 2, dp);
            return dp[index];
        }
        dp[index] = p2(strs, index + 1, dp);
        return dp[index];
    }

    // 从左往右的尝试 这个方法在leetcode中会超时
    public static int numDecodings3(String s) {
        char[] str = s.toCharArray();
        return p(str, 0);
    }

    // 0...index - 1 位置都搞定了，index+1开始搞定strs字符串,有多少种决策
    public static int p(char[] strs, int index) {
        if (index == strs.length) {
            return 1;
        }
        if (strs[index] == '0') {
            return 0;
        }
        if (strs[index] == '1' && index + 1 < strs.length) {
            // 第一位是1，下一位不越界
            return p(strs, index + 1) + p(strs, index + 2);
        }
        if (strs[index] == '2' && index + 1 < strs.length && (strs[index + 1] <= '6')) {
            // 20，21，22，23，24，25，26
            return p(strs, index + 1) + p(strs, index + 2);
        }
        return p(strs, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("1"));
        System.out.println(numDecodings("26"));
        System.out.println(numDecodings("2611055971756562"));
    }

}
