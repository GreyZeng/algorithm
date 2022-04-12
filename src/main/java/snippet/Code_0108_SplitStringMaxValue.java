package snippet;

import java.util.HashMap;
import java.util.Map;

// String str, int k, String[] parts, int[] record
// str一定要分割成k个部分，分割出来的每部分在parts里必须得有
// 那一部分的得分在record里
// 请问，str切成k个部分，最大得分是多少？
// 如果无法做到，则返回-1
// 参考：LeetCode_0139_WordBreak.java
public class Code_0108_SplitStringMaxValue {

    // 暴力解
    public static int maxRecord1(String str, int k, String[] parts, int[] record) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Map<String, Integer> records = new HashMap<>(parts.length);
        for (int i = 0; i < parts.length; i++) {
            records.put(parts[i], record[i]);
        }
        return p(str, 0, k, records);
    }

    // str中i及其往后所有，分解为part部分，最大得分是多少
    public static int p(String str, int i, int part, Map<String, Integer> r) {
        if (part < 0) {
            return -1;
        }
        if (i == str.length()) {
            return part == 0 ? 0 : -1;
        }
        int ans = -1;
        for (int e = i; e < str.length(); e++) {
            String f = str.substring(i, e + 1);
            int next = r.containsKey(f) ? p(str, e + 1, part - 1, r) : -1;
            if (next != -1) {
                ans = Math.max(ans, next + r.get(f));
            }
        }
        return ans;
    }

    // 动态规划解
    public static int maxRecord2(String str, int k, String[] parts, int[] record) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        Map<String, Integer> r = new HashMap<>(parts.length);
        for (int i = 0; i < parts.length; i++) {
            r.put(parts[i], record[i]);
        }
        int[][] dp = new int[str.length() + 1][k + 1];
        for (int i = 1; i < k + 1; i++) {
            dp[str.length()][i] = -1;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                int ans = -1;
                for (int e = i; e < str.length(); e++) {
                    String f = str.substring(i, e + 1);
                    int next = r.containsKey(f) && j - 1 >= 0 ? dp[e + 1][j - 1] : -1;
                    if (next != -1) {
                        ans = Math.max(ans, next + r.get(f));
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][k];
    }

    // 动态规划解 + 前缀树优化
    public static int maxRecord3(String s, int K, String[] parts, int[] record) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        TrieNode root = rootNode(parts, record);
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N + 1][K + 1];
        for (int rest = 1; rest <= K; rest++) {
            dp[N][rest] = -1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= K; rest++) {
                int ans = -1;
                TrieNode cur = root;
                for (int end = index; end < N; end++) {
                    int path = str[end] - 'a';
                    if (cur.nexts[path] == null) {
                        break;
                    }
                    cur = cur.nexts[path];
                    int next = rest > 0 && cur.value != -1 ? dp[end + 1][rest - 1] : -1;
                    if (next != -1) {
                        ans = Math.max(ans, cur.value + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][K];
    }

    public static class TrieNode {
        public TrieNode[] nexts;
        public int value;

        public TrieNode() {
            nexts = new TrieNode[26];
            value = -1;
        }
    }

    public static TrieNode rootNode(String[] parts, int[] record) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < parts.length; i++) {
            char[] str = parts[i].toCharArray();
            TrieNode cur = root;
            for (char c : str) {
                int path = c - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
            }
            cur.value = record[i];
        }
        return root;
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        int K = 3;
        String[] parts = {"abc", "def", "g", "ab", "cd", "efg", "defg"};
        int[] record = {1, 2, 2, 1, 3, 1, 2};
        System.out.println(maxRecord1(str, K, parts, record));
        System.out.println(maxRecord2(str, K, parts, record));
        System.out.println(maxRecord3(str, K, parts, record));
    }

}