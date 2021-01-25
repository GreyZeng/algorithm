package snippet;

import java.util.*;

/*给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
        比如 s1 = "abcde"，s2 = "axbc"
        返回1。s2删掉'x'就是s1的子串了。*/
public class Code_0018_MinDeleteCost {

    // 如果s2很短，那么生成s2的所有子序列(2^M)，如果是s1的子串（kmp），直接删除s1 - s2 个字符就可以了 O(N*2^M)
    public static int minCost1(String s1, String s2) {
        char[] str2 = s2.toCharArray();
        List<String> str2SubSequence = getAllSubSequence(str2);
        str2SubSequence.sort(new MyComparator());
        for (String seq : str2SubSequence) {
            if (s1.contains(seq)) {
                return s2.length() - seq.length();
            }
        }
        return s2.length();
    }

    private static List<String> getAllSubSequence(char[] str) {
        List<String> sub = new ArrayList<>();
        p(str, 0, "", sub);
        return sub;
    }

    private static void p(char[] str, int index, String path, List<String> sub) {
        if (index == str.length) {
            sub.add(path);
            return;
        }
        p(str, index + 1, path, sub);
        p(str, index + 1, path + str[index], sub);
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    // 解法二
    // 如果s1和s2都很长，那么就生成s1的所有字串，然后和s2求编辑距离（只有插入代价或者删除行为，删除和代替的代价无穷大） O(N^2 * M)
    public static int minCost2(String s1, String s2) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                long result = minEditCost(s2, s1.substring(i, j), Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
                if (result < Integer.MAX_VALUE) {
                    ans = Math.min(ans, (int) result);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    public static long minEditCost(String str1, String str2, long ic, long dc, long rc) {
        if (ic + dc < rc) {
            rc = ic + dc;
        }
        char[] w1 = str1.toCharArray();
        char[] w2 = str2.toCharArray();
        // words1 前i个 搞定 words2 前j个需要的最小编辑代价是多少？
        int m = w1.length + 1;
        int n = w2.length + 1;
        long[][] dp = new long[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = (long) i * dc;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = (long) i * ic;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (w1[i - 1] != w2[j - 1]) {
                    dp[i][j] = rc + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[m - 1][n - 1];
    }


    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            if (ans1 != ans2) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
