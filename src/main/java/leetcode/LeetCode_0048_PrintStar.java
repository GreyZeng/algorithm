package leetcode;


public class LeetCode_0048_PrintStar {

	public static char[][] printStar(int N) {
		if (N <= 0) {
			return null;
		}
		char[][] board = new char[N][N];
		int ltx = 0;
		int lty = 0;
		int rbx = N - 1;
		int rby = N - 1;
		while (ltx <= rbx) {
			printStar(board, ltx, lty, rbx, rby);
			ltx += 2;
			lty += 2;
			rbx -= 2;
			rby -= 2;
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}

		return board;
	}

	private static void printStar(char[][] board, int ltx, int lty, int rbx, int rby) {
		for (int i = lty; i <= rby; i++) {
			board[ltx][i] = '*';
		}

		for (int i = ltx; i <= rbx; i++) {
			board[i][rby] = '*';
		}
		for (int i = rby; i >= lty + 2; i--) {
			board[rbx][i] = '*';
		}
		for (int i = rbx; i >= ltx + 2; i--) {
			board[i][lty + 1] = '*';
		}
	}

	public static char[][] printStar2(int N) {
		int leftUp = 0;
		int rightDown = N - 1;
		char[][] m = new char[N][N];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				m[i][j] = ' ';
//			}
//		}
		while (leftUp <= rightDown) {
			set(m, leftUp, rightDown);
			leftUp += 2;
			rightDown -= 2;
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(m[i][j] + " ");
//			}
//			System.out.println();
//		}

		return m;
	}

	public static void set(char[][] m, int leftUp, int rightDown) {
		for (int col = leftUp; col <= rightDown; col++) {
			m[leftUp][col] = '*';
		}
		for (int row = leftUp + 1; row <= rightDown; row++) {
			m[row][rightDown] = '*';
		}
		for (int col = rightDown - 1; col > leftUp; col--) {
			m[rightDown][col] = '*';
		}
		for (int row = rightDown - 1; row > leftUp + 1; row--) {
			m[row][leftUp + 1] = '*';
		}
	}

	public static void main(String[] args) {

		System.out.println("test begin");
		for (int N = 5; N < 10001; N++) {
			char[][] m1 = printStar(N);
			char[][] m2 = printStar2(N);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (m1[i][j] != m2[i][j]) {
						System.out.println("ops!");
						break;
					}
				}
			}
		}
		System.out.println("test end");

	}

}
