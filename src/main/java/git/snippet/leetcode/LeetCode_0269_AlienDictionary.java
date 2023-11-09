package git.snippet.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// TODO
// 出版社印发了一个名单，名单上的每一行都是一个字符串，字符串都是小写字母，但是字符串出现的先后顺序和日常的字典序不一样
// 现在怀疑出版社内部对于字符有自己的字典序规则，请根据字符串之间出现的顺序，返回可能存在的、出版社内部的字符顺序
// 如果从名单来看不存在这样的字符顺序，返回空字符串
// Leetcode题目 : https://leetcode.com/problems/alien-dictionary/
public class LeetCode_0269_AlienDictionary {

    // 生成图，进行拓扑排序（入度为0，擦掉，然后继续）
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int N = words.length;
        HashMap<Character, Integer> indegree = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (char c : words[i].toCharArray()) {
                indegree.put(c, 0);
            }
        }
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] nex = words[i + 1].toCharArray();
            int len = Math.min(cur.length, nex.length);
            int j = 0;
            for (; j < len; j++) {
                if (cur[j] != nex[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (!graph.get(cur[j]).contains(nex[j])) {
                        graph.get(cur[j]).add(nex[j]);
                        indegree.put(nex[j], indegree.get(nex[j]) + 1);
                    }
                    break;
                }
            }
            if (j < cur.length && j == nex.length) {
                return "";
            }
        }
        StringBuilder ans = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                q.offer(key);
            }
        }
        while (!q.isEmpty()) {
            char cur = q.poll();
            ans.append(cur);
            if (graph.containsKey(cur)) {
                for (char next : graph.get(cur)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return ans.length() == indegree.size() ? ans.toString() : "";
    }
}
