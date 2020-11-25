//链接：https://www.nowcoder.com/questionTerminal/c76408782512486d91eea181107293b6
//来源：牛客网
//
//N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行，不同列也不再同一条斜线上，求给一个整数n，返回n皇后的摆法。
//示例1
//输入
//1
//输出
//1
//示例2
//输入
//8
//输出
//92
//
//备注:
//1≤n≤14
package nowcoder;

public class NowCoder_NQueens {
	public static int queens(int n) {
		if (n <= 1) {
			return n;
		}
		// TODO
		// 位运算优化
		return -1;
	}
	public static int queens2(int n) {
		if (n <= 1) {
			return n;
		}
		int[] records = new int[n];
		return process(0, records, n);
	}

	public static int process(int i, int[] record, int n) {
		if (i == n) {
			return 1;
		}
		int res = 0;
		for (int j = 0; j < n; j++) {
			if (isValid(record, i, j)) {
				record[i] = j;
				res += process(i + 1, record, n);
			}
		}
		return res;
	}

	// records[i]=j [i][j]
	public static boolean isValid(int[] records, int i, int j) {
		for (int m = 0; m < i; m++) {
			if (records[m] == j || Math.abs(records[m] - j) == Math.abs(i - m)) {
				return false;
			}
		}
		return true;
	}

}
