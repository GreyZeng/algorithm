package lintcode;

// 实现前缀树
// 你可以认为所有的输入都是小写字母a-z。
// https://www.lintcode.com/problem/442/
public class LintCode_0442_ImplementTrie {
	public class Trie {
		private Node root;

		public Trie() {
			root = new Node();
		}

		public void insert(String word) {
			if (word == null || word.length() == 0) {
				return;
			}
			char[] str = word.toCharArray();
			Node start = root;
			for (char c : str) {
				if (start.next[c - 'a'] == null) {
					// 没有走向c所代表字符的路径
					start.next[c - 'a'] = new Node();
				}
				start = start.next[c - 'a'];
				start.p++;
			}
			start.e++;
		}

		public boolean search(String word) {
			if (word == null || word.length() == 0) {
				return false;
			}
			char[] str = word.toCharArray();
			Node start = root;
			for (char c : str) {
				if (start.next[c - 'a'] == null) {
					return false;
				}
				start = start.next[c - 'a'];
			}
			return start.e != 0;
		}

		public boolean startsWith(String prefix) {
			if (prefix == null || prefix.length() == 0) {
				return false;
			}
			char[] str = prefix.toCharArray();
			Node start = root;
			for (char c : str) {
				if (start.next[c - 'a'] == null) {
					return false;
				}
				start = start.next[c - 'a'];
			}
			return true;
		}

		// 前缀树的节点定义
		private class Node {
			private Node[] next;
			private int p;
			private int e;

			private Node() {
				// 每个节点都可能有走向26个其他节点的路
				next = new Node[26];
			}
		}
	}
}
