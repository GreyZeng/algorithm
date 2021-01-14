/*Given a string s. In one step you can insert any character at any index of the string.

        Return the minimum number of steps to make s palindrome.

        A Palindrome String is one that reads the same backward as well as forward.



        Example 1:

        Input: s = "zzazz"
        Output: 0
        Explanation: The string "zzazz" is already palindrome we don't need any insertions.
        Example 2:

        Input: s = "mbadm"
        Output: 2
        Explanation: String can be "mbdadbm" or "mdbabdm".
        Example 3:

        Input: s = "leetcode"
        Output: 5
        Explanation: Inserting 5 characters the string becomes "leetcodocteel".
        Example 4:

        Input: s = "g"
        Output: 0
        Example 5:

        Input: s = "no"
        Output: 1


        Constraints:

        1 <= s.length <= 500
        All characters of s are lower case English letters.*/
package leetcode;

public class LeetCode_1312_MinimumInsertionStepsToMakeAStringPalindrome {

    public static int minInsertions(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        // 对角线均为0
        // 对角线上一条对角线，相等则为0，不等则为1

        for (int j = 1; j < N; j++) {
            dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
            for (int i = j - 2; i >=0; i--) {
                // 最后解决开头
                // 最后解决结尾
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                }
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("mbadm"));
    }

}
