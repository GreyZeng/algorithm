package trie;

// 笔记：https://www.cnblogs.com/greyzeng/p/16647565.html
// https://leetcode.com/problems/implement-trie-prefix-tree/
public class LeetCode_0208_Trie {
  class Trie {
    private class Node {
      int p;
      int e;
      Node[] nexts = new Node[26];
    }

    Node root;

    public Trie() {
      root = new Node();
    }

    public void insert(String word) {
      if (null == word || word.length() == 0) {
        return;
      }
      char[] strs = word.toCharArray();
      Node cur = root;
      for (char c : strs) {
        cur.p++;
        if (cur.nexts[c - 'a'] == null) {
          cur.nexts[c - 'a'] = new Node();
        }
        cur = cur.nexts[c - 'a'];
      }
      cur.e++;
    }

    public boolean search(String word) {
      if (null == word || word.length() == 0 || !startsWith(word)) {
        return false;
      }
      char[] strs = word.toCharArray();
      Node cur = root;
      for (char c : strs) {
        if (cur.nexts[c - 'a'] != null) {
          cur = cur.nexts[c - 'a'];
        } else {
          return false;
        }
      }
      return cur.e != 0;
    }

    public boolean startsWith(String prefix) {
      char[] strs = prefix.toCharArray();
      Node cur = root;
      for (char c : strs) {
        if (cur.nexts[c - 'a'] != null) {
          cur = cur.nexts[c - 'a'];
        } else {
          return false;
        }
      }
      return true;
    }
  }
}
