/*
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same
 * frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words. Note that "i" comes before "love"
 * due to a lower alphabetical order. Example 2: Input: ["the", "day", "is", "sunny", "the", "the",
 * "the", "sunny", "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation: "the",
 * "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4,
 * 3, 2 and 1 respectively. Note: You may assume k is always valid, 1 ≤ k ≤ number of unique
 * elements. Input words contain only lowercase letters. Follow up: Try to solve it in O(n log k)
 * time and O(n) extra space.
 */
package git.snippet.leetcode;

import java.util.*;

// 给定一个由字符串组成的数组String[] strs，给定一个正数K 返回词频最大的前K个字符串，假设结果是唯一的
// - 方法1：Hash表+小根堆
// - 方法2：用bfprt和快排改进 TODO
public class LeetCode_0692_TopKFrequentWords {
  public static class Node {
    public String value;
    public int times;

    public Node(String v, int t) {
      value = v;
      times = t;
    }
  }

  public static class TimesComparator implements Comparator<Node> {

    @Override
    public int compare(Node a, Node b) {
      if (a.times == b.times) {
        return b.value.compareTo(a.value);
      } else {
        if (a.times > b.times) {
          return 1;
        } else {
          return -1;
        }
      }
    }
  }

  public static List<String> topKFrequent(String[] words, int k) {
    HashMap<String, Integer> map = new HashMap<>();

    for (String word : words) {
      if (map.containsKey(word)) {
        map.put(word, map.get(word) + 1);
      } else {
        map.put(word, 1);
      }
    }
    PriorityQueue<Node> heap = new PriorityQueue<>(new TimesComparator());
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      heap.offer(new Node(entry.getKey(), entry.getValue()));
      if (heap.size() > k) {
        heap.poll();
      }
    }

    List<String> ans = new LinkedList<>();
    while (!heap.isEmpty()) {
      ans.add(0, heap.poll().value);
    }
    return ans;
  }

  public static void main(String[] args) {
    String[] words1 = {"i", "love", "练习题/leetcode", "i", "love", "coding"};
    int k = 2;
    System.out.println(topKFrequent(words1, k));
    String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
    k = 4;
    System.out.println(topKFrequent(words2, k));
  }
}
