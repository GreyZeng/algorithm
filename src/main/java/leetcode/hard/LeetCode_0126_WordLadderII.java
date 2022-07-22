/*Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

        Only one letter can be changed at a time
        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
        Note:

        Return an empty list if there is no such transformation sequence.
        All words have the same length.
        All words contain only lowercase alphabetic characters.
        You may assume no duplicates in the word list.
        You may assume beginWord and endWord are non-empty and are not the same.
        Example 1:

        Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        Output:
        [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
        ]
        Example 2:

        Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        Output: []

        Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.*/
package leetcode.hard;

import java.util.*;

// TODO
// 超时
// https://leetcode.cn/problems/word-ladder-ii/
public class LeetCode_0126_WordLadderII {

    public List<List<String>> findLadders(String start, String end, List<String> list) {
        list.add(start);
        int size = list.size();
        // 每个字符串的邻居数组（只变换一个位置到的地方）
        Map<String, List<String>> next = getNext(list, size);
        Map<String, Integer> distances = getDistances(start, next);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, next, distances, pathList, res);
        return res;
    }

    // 每一个字符串对应的邻居是哪个
    public static Map<String, List<String>> getNext(List<String> list, int size) {
        final HashSet<String> dict = new HashSet<>(list);
        // 指定map大小，可以加速
        Map<String, List<String>> res = new HashMap<>(size);
        for (String str : list) {
            List<String> next = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                char[] s = str.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    if (s[i] != j) {
                        char t = s[i];
                        s[i] = j;
                        String n = String.valueOf(s);
                        if (dict.contains(n)) {
                            next.add(n);
                        }
                        // 记得还原回去
                        s[i] = t;
                    }
                }
            }
            res.put(str, next);
        }
        return res;
    }

    // start 到各个位置的距离
    private Map<String, Integer> getDistances(String start, Map<String, List<String>> next) {
        Map<String, Integer> distance = new HashMap<>();
        distance.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            for (String nx : next.get(s)) {
                if (!set.contains(nx)) {
                    // 下一个节点的距离等于当前位置的距离+1
                    distance.put(nx, distance.get(s) + 1);
                    set.add(nx);
                    queue.add(nx);
                }
            }
        }
        return distance;
    }

    // 现在来到了什么：cur
    // 目的地：end
    // 邻居表：next
    // 最短距离表：distances
    // 沿途走过的路径：path上{....}
    // 答案往res里放，收集所有的最短路径
    private void getShortestPaths(String cur, String to,
                                  Map<String, List<String>> next,
                                  Map<String, Integer> distances,
                                  LinkedList<String> path,
                                  List<List<String>> res) {
        path.add(cur);
        if (cur.equals(to)) {
            res.add(new LinkedList<>(path));
        } else {
            for (String n : next.get(cur)) {
                if (distances.get(n) == distances.get(cur) + 1) {
                    getShortestPaths(n, to, next, distances, path, res);
                }
            }
        }
        path.pollLast();
    }
}
