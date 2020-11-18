package leetcode;

//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//		A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//		Example:
//
//		X X X X
//		X O O X
//		X X O X
//		X O X X
//		After running your function, the board should be:
//
//		X X X X
//		X X X X
//		X X X X
//		X O X X
//		Explanation:
//
//		Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
//		Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
//		Two cells are connected if they are adjacent cells connected horizontally or vertically.
public class LeetCode_0130_SurroundedRegions {
	static final char tag = '#';

	// 递归方法
	public static void solve(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int M = board.length;
		int N = board[0].length;
		for (int i = 0; i < M; i++) {
			if (board[i][0] == 'O') {
				free(board, i, 0);
			}
			if (board[i][N - 1] == 'O') {
				free(board, i, N - 1);
			}
		}
		for (int i = 0; i < N; i++) {
			if (board[0][i] == 'O') {
				free(board, 0, i);
			}
			if (board[M - 1][i] == 'O') {
				free(board, M - 1, i);
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != tag) {
					board[i][j] = 'X';
				} else {
					board[i][j] = 'O';
				}
			}
		}
	}

	private static void free(char[][] board, int i, int j) {
		if (!inValid(board, i, j)) {
			return;
		}
		board[i][j] = tag;
		free(board, i + 1, j - 1);
		free(board, i + 1, j + 1);
		free(board, i - 1, j - 1);
		free(board, i - 1, j + 1);
	}

	public static boolean inValid(char[][] board, int i, int j) {
		return !(i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != 'O');
	}

	public static void main(String[] args) {

	}

	public static void solve2(char[][] board) {
		if (board == null || board.length <= 2 || board[0].length <= 2) {
			return;
		}
		int M = board.length;
		int N = board[0].length;
		UnionFind unionFind = new UnionFind(M * N + 1);
		int dump = 0;
		for (int i = 0; i < M; i++) {
			if (board[i][0] == 'O') {
				unionFind.union(dump, oneArrIndex(M, N, i, 0));
			}
			if (board[i][N - 1] == 'O') {
				unionFind.union(dump, oneArrIndex(M, N, i, N - 1));
			}
		}
		for (int i = 0; i < N; i++) {
			if (board[0][i] == 'O') {
				unionFind.union(dump, oneArrIndex(M, N, 0, i));
			}
			if (board[M - 1][i] == 'O') {
				unionFind.union(dump, oneArrIndex(M, N, M - 1, i));
			}
		}
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				// TODO
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'O' && !unionFind.isSameSet(dump, i * j)) {
					board[i][j] = 'X';
				}
			}
		}
	}

	// 将二维坐标转换成一维坐标
	public static int oneArrIndex(int M, int N, int i, int j) {
		return i * N + j + 1;
	}

	public static class UnionFind {
		private int[] records;

		public UnionFind(int n) {
			records = new int[n];
			for (int i = 0; i < n; i++) {
				records[i] = i;
			}
		}

		public boolean isSameSet(int a, int b) {
			return find(a) == find(b);
		}

		public void union(int a, int b) {
			int fa = find(a);
			int fb = find(b);
			if (fa != fb) {
				records[fa] = fb;
			}
		}

		private int find(int a) {
			int t = a;
			while (t != records[t]) {
				int m = records[t];
				t = m;
			}
			int ans = t;
			// 扁平化操作
			while (a != t) {
				int m = records[a];
				records[m] = t;
				a = m;
			}
			return ans;
		}
	}

}
