package lintcode;

import java.util.HashSet; 
import java.util.Set;


//描述
//给定字符串 s 和单词字典 dict，确定 s 是否可以分成一个或多个以空格分隔的子串，并且这些子串都在字典中存在。
//因为我们已经使用了更强大的数据，所以普通的DFS方法无法解决此题。
// https://www.lintcode.com/problem/107/
// 因为使用了更强大的数据，所以普通的DFS方法无法解决此题。
// s.length <= 1e5 
// dict.size <= 1e5 
public class LintCode_0107_WordBreak {
	public static void main(String[] args) {
		String s = "aaab";
		Set<String> wordSet = new HashSet<>();
		wordSet.add("aa");
		wordSet.add("b");
//		 wordSet.add("a");
//		 wordSet.add("b");
//		s = "aaaaaaa";
// 		 wordSet.add("aaaa");
// 		 wordSet.add("aa");

		System.out.println(wordBreak(s, wordSet));

	}

	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	// 最优解前缀树
	public static boolean wordBreak(String s, Set<String> wordSet) {
		if (null == wordSet) {
			return false;
		}
		if (wordSet.isEmpty()) {
			return s.length() == 0;
		}
		Node root = buildTrie(wordSet);
		char[] str = s.toCharArray();
		int N = str.length;
		boolean[] dp = new boolean[N + 1];
		dp[N] = true;
		for (int index = N - 1; index >= 0; index--) {
			Node cur = root;
			for (int i = index; i < N; i++) {
				int p = str[i] - 'a';
				if (cur.nexts[p] == null) {
					break;
				}
				cur = cur.nexts[p];
				if (cur.end && dp[i + 1]) {
					dp[index] = true;
					break;
				}
			}
		}
		return dp[0];

	}

	// 构造前缀树
	private static Node buildTrie(Set<String> wordDict) {
		Node root = new Node();

		for (String str : wordDict) {
			Node cur = root;
			char[] s = str.toCharArray();
			for (char c : s) { 
				if (cur.nexts[c - 'a'] == null) {
					cur.nexts[c - 'a'] = new Node();
				}
				cur = cur.nexts[c - 'a'];
			}
			cur.end = true;
		}
		return root;
	}
}
