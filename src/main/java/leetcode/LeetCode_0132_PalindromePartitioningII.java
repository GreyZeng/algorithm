/*Given a string s, partition s such that every substring of the partition is a palindrome.

        Return the minimum cuts needed for a palindrome partitioning of s.



        Example 1:

        Input: s = "aab"
        Output: 1
        Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
        Example 2:

        Input: s = "a"
        Output: 0
        Example 3:

        Input: s = "ab"
        Output: 1


        Constraints:

        1 <= s.length <= 2000
        s consists of lower-case English letters only.*/
package leetcode;

// TODO
public class LeetCode_0132_PalindromePartitioningII {
    public static int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        boolean[][] isP = new boolean[N][N];
        // dp[i][j] 表示i...j这段是否是回文
        for (int i = 0; i < N; i++) {
            isP[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                isP[i][j] = isP[i][j - 1] && str[j] == str[i - 1];
            }
        }


        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                // i..end
                if (isP[i][end]) {
                    dp[i] = Math.min(dp[i], 1 + dp[end + 1]);
                }
            }
        }

        return dp[0];
    }

}
