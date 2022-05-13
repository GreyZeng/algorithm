/*We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.



Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now and the result string is "rgeat" which is s2.
As there is one possible scenario that led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true


Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lower-case English letters.*/
package leetcode.hard;

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
                        boolean case2 = dp[L1 + cutPoint][L2][k - cutPoint] && dp[L1][L2 + k - cutPoint][cutPoint];
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

    // str1中，L1往后（包括L1）一共k个字符串 以及  str2中，L2往后（包括L2）一共k个字符串 是否互为扰动串
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
            boolean case1 = f2(str1, str2, L1, L2, cutPoint, dp) && f2(str1, str2, L1 + cutPoint, L2 + cutPoint, k - cutPoint, dp);
            boolean case2 = f2(str1, str2, L1 + cutPoint, L2, k - cutPoint, dp) && f2(str1, str2, L1, L2 + k - cutPoint, cutPoint, dp);
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

    // str1中，L1往后（包括L1）一共k个字符串 以及  str2中，L2往后（包括L2）一共k个字符串 是否互为扰动串
    private static boolean f(char[] str1, char[] str2, int L1, int L2, int k) {
        if (k == 1) {
            // base case， 针对这样的情况，只需要判断str1[L1], str2[L2]
            return str1[L1] == str2[L2];
        }
        // 枚举第一刀的位置
        for (int cutPoint = 1; cutPoint < k; cutPoint++) {
            boolean case1 = f(str1, str2, L1, L2, cutPoint) && f(str1, str2, L1 + cutPoint, L2 + cutPoint, k - cutPoint);
            boolean case2 = f(str1, str2, L1 + cutPoint, L2, k - cutPoint) && f(str1, str2, L1, L2 + k - cutPoint, cutPoint);
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
