package class_2023_07_4_week;

// 自 01背包问世之后，小 A 对此深感兴趣
// 一天，小 A 去远游，却发现他的背包不同于 01 背包，他的物品大致可分为 k 组
// 每组中的物品只能选择1件，现在他想知道最大的利用价值是多少
// 测试链接 : www.luogu.com.cn/problem/P1757
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code02_TeamDP {

	public static int MAXN = 1001;

	public static int MAXM = 1001;

	// arr[i][0] 重量
	// arr[i][1] 价值
	// arr[i][2] 组号
	public static int[][] arr = new int[MAXN][3];

	public static int[] dp = new int[MAXM];

	public static int m, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			// 总背包的载重
			m = (int) in.nval;
			in.nextToken();
			// 物品数量
			n = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				arr[i][0] = (int) in.nval;
				in.nextToken();
				arr[i][1] = (int) in.nval;
				in.nextToken();
				arr[i][2] = (int) in.nval;
			}
			
			// 根据组号排序
			// 1 : a b c   2 : d e  3 : f g h
			Arrays.sort(arr, 0, n, (a, b) -> a[2] - b[2]);
			// dp[位置][剩余重量]
			// dp[重量] 
			Arrays.fill(dp, 0, m + 1, 0);
			out.println(compute());
			out.flush();
		}
	}

	public static int compute() {
		for (int start = 0, end = 1; start < n;) {
			// start(首个物品)
			// end(当前这个组的越界物品)
			// a b c  |  d e  | f g
			// 0 1 2     3 4    5 6
			while (end < n && arr[end][2] == arr[start][2]) {
				end++;
			}
			// [start...end)是一个组的物品
			for (int r = m; r >= 0; r--) {
				for (int i = start; i < end; i++) {
					if (r >= arr[i][0]) {
						dp[r] = Math.max(dp[r], arr[i][1] + dp[r - arr[i][0]]);
					}
				}
			}
			start = end++;
		}
		return dp[m];
	}

}
