package class_2022_09_4_week;

// 来自华为
// 一个n*n的二维数组中，只有0和1两种值
// 当你决定在某个位置操作一次
// 那么该位置的行和列整体都会变成1，不管之前是什么状态
// 返回让所有值全变成1，最少的操作次数
// 1 < n < 10，没错！原题就是说n < 10, 不会到10！最多到9！
public class Code02_SetAllOneMinTimes {

//	public static int minTimes(int[][] matrix) {
//		int n = matrix.length;
//		int m = matrix[0].length;
//		int[] arr = new int[n];
//		for (int i = 0; i < n; i++) {
//			int status = 0;
//			for (int j = 0; j < m; j++) {
//				// 0列 1列 2列 3列
//				// 1 0 1 1
//				// 1101
//				if (matrix[i][j] == 1) {
//					status |= 1 << m;
//				}
//			}
//			arr[i] = status;
//		}
//
//	}
//
//	// arr替代了原来的二维数组！
//	// n行，m列，固定
//	// 目前来到i行、j列
//	// rowStatus : 之前哪些行点过鼠标
//	// colStatus : 之前哪些列点过鼠标
//	// i 行变化 ： 9  * 9 * 2^9 * 2^9 * 9 = 191,102,976
//	public static int zuo(int[] arr, int n, int m, int i, int j, int clickRows, int clickCols) {
//		if (i == n) {
//			for (int row = 0; row < n; row++) {
//				if ((clickRows & (1 << row)) != 0) {
//					continue;
//				}
//				// row行，没点过鼠标
//				int merge = arr[row] | clickCols;
//				int full = (1 << m) - 1;
//				if (merge != full) {
//					return Integer.MAX_VALUE;// 表示无效
//				}
//			}
//			return 0;
//		}
//		if (j == m) {
//			return zuo(arr, n, m, i + 1, 0, clickRows, clickCols);
//		}
//		// i,j 正常的行、正常的列
//		// 当前就是不点鼠标
//		int p1 = zuo(arr, n, m, i, j + 1, clickRows, clickCols);
//		// 当前就是点鼠标
//		int p2 = Integer.MAX_VALUE;
//		int next = zuo(arr, n, m, i, j + 1,
//				clickRows | (1 << i), clickCols | (1 << j));
//		if(next != Integer.MAX_VALUE) {
//			p2 = 1 + next;
//		}
//		return Math.min(p1, p2);
//	}

	// 暴力方法
	// 为了验证
	public static int setOneMinTimes1(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int limit = 1 << (n * m);
		int ans = Integer.MAX_VALUE;
		// 0000000000000
		// 0000000000001
		// 0000000000010
		// 0000000000011
		// 0000000000100
		for (int status = 0; status < limit; status++) {
			if (ok(status, matrix, n, m)) {
				ans = Math.min(ans, hammingWeight(status));
			}
		}
		return ans;
	}

	public static boolean ok(int status, int[][] matrix, int n, int m) {
		int[][] help = new int[n][m];
		int limit = n * m;
		for (int i = 0; i < limit; i++) {
			if ((status & (1 << i)) != 0) {
				int row = i / m;
				int col = i % m;
				for (int j = 0; j < n; j++) {
					help[j][col] = 1;
				}
				for (int j = 0; j < m; j++) {
					help[row][j] = 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (help[i][j] == 0 && matrix[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int hammingWeight(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}

	// 正式方法
	public static int setOneMinTimes2(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int status = 0;
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					status |= 1 << j;
				}
			}
			arr[i] = status;
		}
		int[][][][] dp = new int[1 << n][1 << m][n][m];
		for (int a = 0; a < (1 << n); a++) {
			for (int b = 0; b < (1 << m); b++) {
				for (int c = 0; c < n; c++) {
					for (int d = 0; d < m; d++) {
						dp[a][b][c][d] = -1;
					}
				}
			}
		}
		return process2(arr, n, m, 0, 0, 0, 0, dp);
	}

	public static int process2(int[] arr, int n, int m, int row, int col, int r, int c, int[][][][] dp) {
		if (r == n) {
			for (int i = 0; i < n; i++) {
				if ((row & (1 << i)) == 0 && (arr[i] | col) != (1 << m) - 1) {
					return Integer.MAX_VALUE;
				}
			}
			return 0;
		}
		if (c == m) {
			return process2(arr, n, m, row, col, r + 1, 0, dp);
		}
		if (dp[row][col][r][c] != -1) {
			return dp[row][col][r][c];
		}
		int p1 = process2(arr, n, m, row, col, r, c + 1, dp);
		int p2 = Integer.MAX_VALUE;
		int next2 = process2(arr, n, m, row | (1 << r), col | (1 << c), r, c + 1, dp);
		if (next2 != Integer.MAX_VALUE) {
			p2 = 1 + next2;
		}
		int ans = Math.min(p1, p2);
		dp[row][col][r][c] = ans;
		return ans;
	}

	// 正式方法 + 贪心
	public static int setOneMinTimes3(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int status = 0;
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					status |= 1 << j;
				}
			}
			arr[i] = status;
		}
		int[][][][] dp = new int[1 << n][1 << m][n][m];
		for (int a = 0; a < (1 << n); a++) {
			for (int b = 0; b < (1 << m); b++) {
				for (int c = 0; c < n; c++) {
					for (int d = 0; d < m; d++) {
						dp[a][b][c][d] = -1;
					}
				}
			}
		}
		return process3(arr, n, m, 0, 0, 0, 0, dp);
	}

	public static int process3(int[] arr, int n, int m, int row, int col, int r, int c, int[][][][] dp) {
		if (r == n) {
			for (int i = 0; i < n; i++) {
				if ((row & (1 << i)) == 0 && (arr[i] | col) != (1 << m) - 1) {
					return Integer.MAX_VALUE;
				}
			}
			return 0;
		}
		if (c == m) {
			return process3(arr, n, m, row, col, r + 1, 0, dp);
		}
		if (dp[row][col][r][c] != -1) {
			return dp[row][col][r][c];
		}
		int p1 = process3(arr, n, m, row, col, r, c + 1, dp);
		int p2 = Integer.MAX_VALUE;
		int next2 = process3(arr, n, m, row | (1 << r), col | (1 << c), r + 1, 0, dp);
		if (next2 != Integer.MAX_VALUE) {
			p2 = 1 + next2;
		}
		int ans = Math.min(p1, p2);
		dp[row][col][r][c] = ans;
		return ans;
	}

	public static int[][] randomMatrix(int n, int m, double p0) {
		int[][] ans = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ans[i][j] = Math.random() < p0 ? 0 : 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int N = 3;
		int testTimes = 5000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * N) + 1;
			double p0 = Math.random();
			int[][] matrix = randomMatrix(n, m, p0);
			int ans1 = setOneMinTimes1(matrix);
			int ans2 = setOneMinTimes2(matrix);
			int ans3 = setOneMinTimes3(matrix);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("出错了！");
			}
		}
		System.out.println("功能测试结束");

		int[][] matrix = randomMatrix(9, 9, 0.9);
		long start = System.currentTimeMillis();
		setOneMinTimes2(matrix);
		long end = System.currentTimeMillis();
		System.out.println("最极限的数据下的运行时间 : " + (end - start) + "毫秒");
	}

}
