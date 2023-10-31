package git.snippet.leetcode;

import java.util.*;

// TODO
// 按字典wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 ->
// ... -> sk 这样的单词序列，并满足：
// 每对相邻的单词之间仅有单个字母不同。
// 转换过程中的每个单词 si（1 <= i <= k）必须是字典wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词
// 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList
// 请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表
// 每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回
// https://leetcode.cn/problems/word-ladder-ii/
public class LeetCode_0126_WordLadderII {
  public static List<List<String>> findLadders(String start, String end, List<String> list) {
    list.add(start);
    HashMap<String, List<String>> nexts = getNexts(list);
    HashMap<String, Integer> fromDistances = getDistances(start, nexts);
    List<List<String>> res = new ArrayList<>();
    if (!fromDistances.containsKey(end)) {
      return res;
    }
    HashMap<String, Integer> toDistances = getDistances(end, nexts);
    LinkedList<String> pathList = new LinkedList<>();
    getShortestPaths(start, end, nexts, fromDistances, toDistances, pathList, res);
    return res;
  }

  public static HashMap<String, List<String>> getNexts(List<String> words) {
    HashSet<String> dict = new HashSet<>(words);
    HashMap<String, List<String>> nexts = new HashMap<>();
    for (int i = 0; i < words.size(); i++) {
      nexts.put(words.get(i), getNext(words.get(i), dict));
    }
    return nexts;
  }

  // word, 在表中，有哪些邻居，把邻居们，生成list返回
  public static List<String> getNext(String word, HashSet<String> dict) {
    ArrayList<String> res = new ArrayList<String>();
    char[] chs = word.toCharArray();
    for (char cur = 'a'; cur <= 'z'; cur++) {
      for (int i = 0; i < chs.length; i++) {
        if (chs[i] != cur) {
          char tmp = chs[i];
          chs[i] = cur;
          if (dict.contains(String.valueOf(chs))) {
            res.add(String.valueOf(chs));
          }
          chs[i] = tmp;
        }
      }
    }
    return res;
  }

  // 生成距离表，从start开始，根据邻居表，宽度优先遍历，对于能够遇到的所有字符串，生成(字符串，距离)这条记录，放入距离表中
  public static HashMap<String, Integer> getDistances(
      String start, HashMap<String, List<String>> nexts) {
    HashMap<String, Integer> distances = new HashMap<>();
    distances.put(start, 0);
    Queue<String> queue = new LinkedList<>();
    queue.add(start);
    HashSet<String> set = new HashSet<>();
    set.add(start);
    while (!queue.isEmpty()) {
      String cur = queue.poll();
      for (String next : nexts.get(cur)) {
        if (!set.contains(next)) {
          distances.put(next, distances.get(cur) + 1);
          queue.add(next);
          set.add(next);
        }
      }
    }
    return distances;
  }

  // cur 当前来到的字符串 可变
  // to 目标，固定参数
  // nexts 每一个字符串的邻居表
  // cur 到开头距离5 -> 到开头距离是6的支路 fromDistances距离表
  // cur 到结尾距离5 -> 到开头距离是4的支路 toDistances距离表
  // path : 来到cur之前，深度优先遍历之前的历史是什么
  // res : 当遇到cur，把历史，放入res，作为一个结果
  public static void getShortestPaths(
      String cur,
      String to,
      HashMap<String, List<String>> nexts,
      HashMap<String, Integer> fromDistances,
      HashMap<String, Integer> toDistances,
      LinkedList<String> path,
      List<List<String>> res) {
    path.add(cur);
    if (to.equals(cur)) {
      res.add(new LinkedList<String>(path));
    } else {
      for (String next : nexts.get(cur)) {
        if (fromDistances.get(next) == fromDistances.get(cur) + 1
            && toDistances.get(next) == toDistances.get(cur) - 1) {
          getShortestPaths(next, to, nexts, fromDistances, toDistances, path, res);
        }
      }
    }
    path.pollLast();
  }
  /*
   * // 方法超时 public List<List<String>> findLadders(String start, String end, List<String> list) {
   * list.add(start); int size = list.size(); // 每个字符串的邻居数组（只变换一个位置到的地方） Map<String, List<String>>
   * next = getNext(list, size); Map<String, Integer> distances = getDistances(start, next);
   * LinkedList<String> pathList = new LinkedList<>(); List<List<String>> res = new ArrayList<>();
   * getShortestPaths(start, end, next, distances, pathList, res); return res; }
   *
   * // 每一个字符串对应的邻居是哪个 public static Map<String, List<String>> getNext(List<String> list, int size)
   * { final HashSet<String> dict = new HashSet<>(list); // 指定map大小，可以加速 Map<String, List<String>>
   * res = new HashMap<>(size); for (String str : list) { List<String> next = new ArrayList<>(); for
   * (int i = 0; i < str.length(); i++) { char[] s = str.toCharArray(); for (char j = 'a'; j <= 'z';
   * j++) { if (s[i] != j) { char t = s[i]; s[i] = j; String n = String.valueOf(s); if
   * (dict.contains(n)) { next.add(n); } // 记得还原回去 s[i] = t; } } } res.put(str, next); } return res;
   * }
   *
   * // start 到各个位置的距离 private Map<String, Integer> getDistances(String start, Map<String,
   * List<String>> next) { Map<String, Integer> distance = new HashMap<>(); distance.put(start, 0);
   * Queue<String> queue = new LinkedList<>(); queue.add(start); HashSet<String> set = new
   * HashSet<>(); set.add(start); while (!queue.isEmpty()) { String s = queue.poll(); for (String nx
   * : next.get(s)) { if (!set.contains(nx)) { // 下一个节点的距离等于当前位置的距离+1 distance.put(nx,
   * distance.get(s) + 1); set.add(nx); queue.add(nx); } } } return distance; }
   *
   * // 现在来到了什么：cur // 目的地：end // 邻居表：next // 最短距离表：distances // 沿途走过的路径：path上{....} //
   * 答案往res里放，收集所有的最短路径 private void getShortestPaths(String cur, String to, Map<String,
   * List<String>> next, Map<String, Integer> distances, LinkedList<String> path, List<List<String>>
   * res) { path.add(cur); if (cur.equals(to)) { res.add(new LinkedList<>(path)); } else { for
   * (String n : next.get(cur)) { if (distances.get(n) == distances.get(cur) + 1) {
   * getShortestPaths(n, to, next, distances, path, res); } } } path.pollLast(); }
   */
}
