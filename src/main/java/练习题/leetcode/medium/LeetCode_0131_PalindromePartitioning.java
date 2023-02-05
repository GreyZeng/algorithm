package 练习题.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Given a string s, partition s such that every substring of the partition is a palindrome.
//
// Return all possible palindrome partitioning of s.
//
// Example:
//
// Input: "aab"
// Output:
// [
// ["aa","b"],
// ["a","a","b"]
// ]
public class LeetCode_0131_PalindromePartitioning {
    // 分割所有的回文串
    // L。。R是否是回文，可以用二维表
    public static List<List<String>> partition(String s) {
        if (null == s) {
            return null;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (N == 0) {
            return null;
        }
        if (N == 1) {
            List<String> list = new ArrayList<>();
            list.add(s);
            List<List<String>> lists = new ArrayList<>();
            lists.add(list);
            return lists;
        }
        boolean[][] dp = initDp(str);

        LinkedList<String> path = new LinkedList<>();
        List<List<String>> ans = new ArrayList<>();
        process(str, dp, 0, path, ans);
        return ans;
    }

    public static void process(char[] str, boolean[][] dp, int i, LinkedList<String> path,
                               List<List<String>> ans) {
        if (i == str.length) {
            ans.add(copy(path));
        }
        for (int j = i; j < str.length; j++) {
            if (dp[i][j]) {
                path.addLast(buildStr(str, i, j));
                process(str, dp, j + 1, path, ans);
                path.pollLast();
            }
        }
    }

    public static String buildStr(char[] str, int i, int j) {
        StringBuilder sb = new StringBuilder();
        for (int x = i; x <= j; x++) {
            sb.append(str[x]);
        }
        return sb.toString();
    }

    public static List<String> copy(List<String> path) {
        List<String> copy = new ArrayList<>();
        for (String it : path) {
            copy.add(it);
        }
        return copy;
    }

    private static boolean[][] initDp(char[] str) {
        int N = str.length;
        boolean[][] dp = new boolean[N][N];
        // 对角线以下不用管
        // 对角线都是True
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        // 对角线上一行填完
        for (int i = 0; i <= N - 2; i++) {
            dp[i][i + 1] = str[i] == str[i + 1];
        }

        // dp[i][j] 如果要等于true，则必须满足dp[i+1][j-1] == true 且 str[i] == str[j]

        for (int i = N - 3; i >= 0; i--) {
            int m = i;
            for (int j = N - 1; j >= 2 && m >= 0; j--, m--) {
                dp[m][j] = dp[m + 1][j - 1] && str[m] == str[j];
            }
        }
        return dp;
    }


}
