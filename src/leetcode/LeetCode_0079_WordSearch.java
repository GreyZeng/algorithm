package leetcode;

//
//Given a 2D board and a word, find if the word exists in the grid.
//
//		The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//		Example:
//
//		board =
//		[
//		['A','B','C','E'],
//		['S','F','C','S'],
//		['A','D','E','E']
//		]
//
//		Given word = "ABCCED", return true.
//		Given word = "SEE", return true.
//		Given word = "ABCB", return false.
//
//
//		Constraints:
//
//		board and word consists only of lowercase and uppercase English letters.
//		1 <= board.length <= 200
//		1 <= board[i].length <= 200
//		1 <= word.length <= 10^3
public class LeetCode_0079_WordSearch {
	// 深度优先遍历
	// 增加现场
	public static boolean exist(char[][] board, String word) {
		char[] words = word.toCharArray();
		int M = board.length;
		int N = board[0].length;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (p(board, words, 0, i, j, M, N)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean p(char[][] board, char[] words, int s, int i, int j, int M, int N) {
		if (s == words.length) {
			return true;
		}
		if (i == M || i == -1 || j == N || j == -1) {
			return false;
		}
		if (board[i][j] == words[s]) {
			char t = board[i][j];
			board[i][j] = '0';
			boolean r = p(board, words, s + 1, i + 1, j, M, N) || p(board, words, s + 1, i, j + 1, M, N)
					|| p(board, words, s + 1, i, j - 1, M, N) || p(board, words, s + 1, i - 1, j, M, N);
			board[i][j] = t;
			return r;
		} else {
			return false;
		}
	}
}
