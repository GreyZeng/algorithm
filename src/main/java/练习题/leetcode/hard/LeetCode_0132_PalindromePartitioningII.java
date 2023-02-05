package 练习题.leetcode.hard;

import java.util.*;

// 问题一：一个字符串至少要切几刀能让切出来的子串都是回文串
// 问题二：返回问题一的其中一种划分结果
// 问题三：返回问题一的所有划分结果 （回溯）
// https://leetcode-cn.com/problems/palindrome-partitioning-ii/
public class LeetCode_0132_PalindromePartitioningII {

    // 问题一
    // 从左往右的尝试：f(str,i),从i到后面，可以得到几个回文串，这样的处理比定义切几刀更方便，不过最后的结果需要减一，因为分成n部分要切n-1刀
    // 范围上的尝试：dp[i][j]是否是回文，其中对角线是TRUE，普遍位置：i==j&&dp[i+1][j-1]
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        // check可以表示一个范围内是否是回文
        boolean[][] check = check(str);
        // dp[i] 表示从i到后面，可以分割出几部分回文
        int[] dp = new int[str.length];
        dp[dp.length - 1] = 1;
        for (int i = dp.length - 2; i >= 0; i--) {
            if (check[i][dp.length - 1]) {
                dp[i] = 1;
            } else {
                // [i....dp.length-1] 不是回文，枚举能分割的回文数量
                int max = Integer.MAX_VALUE;
                for (int j = i; j < dp.length; j++) {
                    if (check[i][j]) {
                        max = Math.min(max, 1 + dp[j + 1]);
                    }
                }
                dp[i] = max;
            }
        }

        return dp[0] - 1;
    }

    public static boolean[][] check(char[] str) {
        boolean[][] check = new boolean[str.length][str.length];
        for (int i = 0; i < str.length; i++) {
            check[i][i] = true;
        }
        for (int i = 0; i < str.length - 1; i++) {
            check[i][i + 1] = (str[i] == str[i + 1]);
        }
        for (int i = str.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < str.length; j++) {
                check[i][j] = str[i] == str[j] && check[i + 1][j - 1];
            }
        }
        return check;
    }


    // TODO
    // 本题第二问，返回其中一种结果
    public static List<String> minCutOneWay(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = check(str);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            // dp[i] (0....5) 回文！ dp[0] == dp[6] + 1
            // (0....5) 6
            for (int i = 0, j = 1; j <= N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return ans;
    }

    // TODO
    // 本题第三问，返回所有结果
    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            ans.add(cur);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = check(str);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
        }
        return ans;
    }

    // s[0....i-1] 存到path里去了
    // s[i..j-1]考察的分出来的第一份
    public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                               List<String> path, List<List<String>> ans) {
        if (j == s.length()) { // s[i...N-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                ans.add(copyStringList(path));
                path.remove(path.size() - 1);
            }
        } else {// s[i...j-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                process(s, j, j + 1, checkMap, dp, path, ans);
                path.remove(path.size() - 1);
            }
            process(s, i, j + 1, checkMap, dp, path, ans);
        }
    }

    public static List<String> copyStringList(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            ans.add(str);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = null;
        List<String> ans2 = null;
        List<List<String>> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "abacbc";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabccbac";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabaa";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();
        System.out.println("本题第二问，返回其中一种结果测试结束");
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试开始");
        s = "cbbbcbc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "aaaaaa";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "fcfffcffcc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试结束");
    }

}
