package leetcode;

import java.util.Arrays;

//给你一个房屋数组houses 和一个整数 k ，其中 houses[i] 是第 i 栋房子在一条街上的位置，现需要在这条街上安排 k 个邮筒。
//
//请你返回每栋房子与离它最近的邮筒之间的距离的 最小总和。
//
//答案保证在 32 位有符号整数范围以内。
//提示：

//n == houses.length
//1 <= n <= 100
//1 <= houses[i] <= 10^4
//1 <= k <= n
//数组 houses 中的整数互不相同。
//示例 1：
//
//
//
//输入：houses = [1,4,8,10,20], k = 3
//输出：5
//解释：将邮筒分别安放在位置 3， 9 和 20 处。
//每个房子到最近邮筒的距离和为 |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5 。
//示例 2：
//
//
//
//输入：houses = [2,3,5,12,18], k = 2
//输出：9
//解释：将邮筒分别安放在位置 3 和 14 处。
//每个房子到最近邮筒距离和为 |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9 。
//示例 3：
//
//输入：houses = [7,4,6,1], k = 1
//输出：8
//示例 4：
//
//输入：houses = [3,6,14,10], k = 4
//输出：0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/allocate-mailboxes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//
//
//        题目
//        arr有序(表示距离)
//        k=3 表示邮局
//        建在哪里，总距离最小是多少
//        暴力尝试 dp[i][j]
//        0..i位置上，建立j个邮局，总距离最小，放在dp中
//        一个样本做行，一个样本做列的模型
//        为了加速，我们需要生成如下结构：
//        record[i][j] -> 只有一个邮局的时候，总距离最小是多少？ 一个结论，如果必须只能建一座邮局，一定要建在中点的时候总距离最小
//        record[0][0] = 0
//        record[0][1] = [1] - [0]
//        record[0][2] = record[0][1] + [2] - [1]
//        record[0][3] = record[0][2] + [3] - [1]
//        ....
//
//        然后用record去辅助我们的动态规划的尝试
//        O(N^2 * K) 有枚举行为
//        0....i给j个邮局m
//        考虑0...i-1给j个邮局的最优划分k
//        所以0...i给j个邮局 m>=k
//
//        0....i给j个邮局
//        考虑0...i给j+1个邮局的最优划分T
//        所以0...i给j个邮局 m<=T
//        所以    k<=m<=T
//
//        1. 有枚举行为
//        2. dp与i，j存在单调关系
//        3. 区间划分问题（上下左右的格子可以给当前格子提供上下界的限制）
//        4. 一个单独格子不会依赖本行和本列的某些值
//        --> dp[i][j]和临近位置关系来优化枚举行为
//        生成choose和dp规模一样，存最优划分
public class LeetCode_1478_AllocateMailboxes {

	public static int minDistance1(int[] arr, int m) {
		
		int n = arr.length;
		if (m == n) {
			// 邮局数量和居民区数量一样，在每个居民区建一个邮筒即可。
			// 总距离就是0
			return 0;
		}
		// 保证居民区有序
		Arrays.sort(arr);
		// 0...n-1号房子在m个邮筒安排下的最佳总路程
		int[][] dp = new int[n][m + 1];
		// 第0列弃而不用，因为0个邮筒没法安排
		// 第一列
		// 表示必须要建一个邮筒的情况下，处理0...i号邮筒，此时，邮筒应该建在中间位置
		// 0号房子一个邮筒，安排策略就是在0号房子下安排这个邮筒，距离为0 dp[0][1] = 0
		for (int i = 1; i < n; i++) {
			// int bestPosition = ?;
			// dp[i][1] = 0...i号每个居民点到bestPosition的距离之和。
		}

		return dp[n - 1][m];
	}

	public static int minDistance2(int[] arr, int num) {
		if (arr == null || num < 1 || arr.length < num) {
			return 0;
		}
		Arrays.sort(arr);
		int N = arr.length;
		int[][] w = new int[N + 1][N + 1];
		for (int L = 0; L < N; L++) {
			for (int R = L + 1; R < N; R++) {
				w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
			}
		}
		int[][] dp = new int[N][num + 1];
		int[][] best = new int[N][num + 1];
		for (int i = 0; i < N; i++) {
			dp[i][1] = w[0][i];
			best[i][1] = -1;
		}
		for (int j = 2; j <= num; j++) {
			for (int i = N - 1; i >= j; i--) {
				int down = best[i][j - 1];
				int up = i == N - 1 ? N - 1 : best[i + 1][j];
				int ans = Integer.MAX_VALUE;
				int bestChoose = -1;
				for (int leftEnd = down; leftEnd <= up; leftEnd++) {
					int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
					int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
					int cur = leftCost + rightCost;
					if (cur <= ans) {
						ans = cur;
						bestChoose = leftEnd;
					}
				}
				dp[i][j] = ans;
				best[i][j] = bestChoose;
			}
		}
		return dp[N - 1][num];
	}

	// for test
	public static int[] randomSortedArray(int len, int range) {
		int[] arr = new int[len];
		for (int i = 0; i != len; i++) {
			arr[i] = (int) (Math.random() * range);
		}
		Arrays.sort(arr);
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int N = 30;
		int maxValue = 100;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N) + 1;
			int[] arr = randomSortedArray(len, maxValue);
			int num = (int) (Math.random() * N) + 1;
			int ans1 = minDistance1(arr, num);
			int ans2 = minDistance2(arr, num);
			if (ans1 != ans2) {
				printArray(arr);
				System.out.println(num);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");

	}

}
