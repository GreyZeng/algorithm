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
	// TODO
	public static int queens(int n) {
		int[][] dp = new int[n][n];
			
		return -1;
	}
	

	public static boolean isValid(int[][] dp, int i, int j, int m, int n) {
		if (i == m || j == n || Math.abs(i - m) == Math.abs(j - n)) {
			return false;
		}
		return true;
	}

}
