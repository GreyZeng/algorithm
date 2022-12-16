package 练习题.leetcode.hard;

import java.util.HashSet;
import java.util.List;

// Given two words (beginWord and endWord), and a dictionary's word list, find the length of
// shortest transformation sequence from beginWord to endWord, such that:
//
// Only one letter can be changed at a time.
// Each transformed word must exist in the word list.
// Note:
//
// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
// Example 1:
//
// Input:
// beginWord = "hit",
// endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]
//
// Output: 5
//
// Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.
// Example 2:
//
// Input:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log"]
//
// Output: 0
//
// Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
public class LeetCode_0127_WordLadder {

  // 1. 将list加入Hashset中，如果end不在hashset中，直接返回0
  //
  public static int ladderLength(String start, String end, List<String> list) {
    HashSet<String> dict = new HashSet<>(list);
    if (!dict.contains(end)) {
      return 0;
    }
    HashSet<String> b = new HashSet<>(); // 存开始字符串
    HashSet<String> e = new HashSet<>(); // 存目标字符串
    HashSet<String> v = new HashSet<>(); // 存中途访问过的字符，防止到某一步的时候，取下一个字符是之前已经取过的字符，这样会导致循环依赖

    b.add(start);
    e.add(end);

    int len = 2; // 至少以2起步，因为开始位置字符串和目标字符串不相等【题目要求】，假设有结果，则结果中必然包含目标字符串+至少一次的转换

    while (!b.isEmpty()) {
      HashSet<String> n = new HashSet<>(); // 下一组字符串
      for (String s : b) {
        // char[] a = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
          char[] a = s.toCharArray();
          for (char j = 'a'; j <= 'z'; j++) {
            if (s.charAt(i) != j) {
              a[i] = j;
              String nx = String.valueOf(a);
              if (e.contains(nx)) {
                return len;
              }
              if (!v.contains(nx) && dict.contains(nx)) {
                n.add(nx);
                v.add(nx);
              }
            }

          }
        }

      }
      len++;
      b = (n.size() < e.size()) ? n : e;
      e = (b == n) ? e : n;

    }

    return 0;

  }

}
