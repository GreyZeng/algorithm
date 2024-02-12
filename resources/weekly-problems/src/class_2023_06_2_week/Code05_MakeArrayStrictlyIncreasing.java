package class_2023_06_2_week;

import java.util.Arrays;

// 给你两个整数数组 arr1 和 arr2
// 返回使 arr1 严格递增所需要的最小「操作」数（可能为 0）。
// 每一步「操作」中，你可以分别从 arr1 和 arr2 中各选出一个索引，
// 分别为 i 和 j，0 <= i < arr1.length 和 0 <= j < arr2.length，
// 然后进行赋值运算 arr1[i] = arr2[j]。如果无法让 arr1 严格递增，请返回 -1。
// 之前讲过这个题，能通过但不是最优解，所以我们直接补一个最优解
// 测试链接 : https://leetcode.cn/problems/make-array-strictly-increasing/
public class Code05_MakeArrayStrictlyIncreasing {

	public static int makeArrayIncreasing1(int[] arr1, int[] arr2) {
		// arr2 [3,7,7,2,3]
		Arrays.sort(arr2);
		// arr2 [2,3,3,7,7]
		int cnt = 1;
		for (int i = 1; i < arr2.length; i++) {
			if (arr2[i] != arr2[i - 1]) {
				cnt++;
			}
		}
		// cnt = 3, 去重之后，只有三个数字
		int[] help = new int[cnt];
		help[0] = arr2[0];
		for (int i = 1, j = 1; i < arr2.length; i++) {
			if (arr2[i] != arr2[i - 1]) {
				help[j++] = arr2[i];
			}
		}
		// help : arr2去重且排序的结果
		// 2 3 7
		int ans = process1(arr1, help, -1);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// 系统最小
	// -1     0 1 2 3 ...
	// arr1[i.....] 在i位置的数，一定不换的情况下
	// 后续至少要换几个数，能做到整体递增!
	public static int process1(int[] arr1, int[] arr2, int i) {
		if (i == arr1.length) {
			return 0;
		} else {
			// 没到终止位置
			// 当前的数字
			// i ==-1  Integer.MIN_VALUE
			// i > -1 arr1[i]
			// 当前的数字 Integer.MIN_VALUE
			// arr2 [ 3, 4, 5, 8, 10]
			//        0  1  2  3  4
			// f = 0
			int cur = i == -1 ? Integer.MIN_VALUE : arr1[i];
			// f == -1 >cur的数，在arr2中不存在
			// f != -1 >cur的数，在arr2中最左的位置
			int f = find(arr2, cur);
			int ans = Integer.MAX_VALUE;
			// 目前换了几个数了
			int times = 0;
			for (int j = i + 1; j <= arr1.length; j++) {
				//j 右边离i最近，下一个不换的位置
				// i(不换) i+1...  i+2... i+3...
				// i......j(不换)
				// cur : 当前来到的j位置的数的，前一个数
				// cur : 可能被arr2里的数字，替过了！
				if (j == arr1.length || cur < arr1[j]) {
					int next = process1(arr1, arr2, j);
					if (next != Integer.MAX_VALUE) {
						ans = Math.min(ans, times + next);
					}
				}
				if (f != -1 && f < arr2.length) {
					cur = arr2[f++];
					times++;
				} else {
					break;
				}
			}
			return ans;
		}
	}

	public static int find(int[] arr2, int num) {
		int l = 0;
		int r = arr2.length - 1;
		int m, ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr2[m] > num) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	// 记忆化搜索版
	public static int makeArrayIncreasing2(int[] arr1, int[] arr2) {
		Arrays.sort(arr2);
		int cnt = 1;
		for (int i = 1; i < arr2.length; i++) {
			if (arr2[i] != arr2[i - 1]) {
				cnt++;
			}
		}
		int[] help = new int[cnt];
		help[0] = arr2[0];
		for (int i = 1, j = 1; i < arr2.length; i++) {
			if (arr2[i] != arr2[i - 1]) {
				help[j++] = arr2[i];
			}
		}
		int[] dp = new int[arr1.length + 1];
		Arrays.fill(dp, -1);
		int ans = process2(arr1, help, -1, dp);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int process2(int[] arr1, int[] arr2, int i, int[] dp) {
		if (i == arr1.length) {
			return 0;
		} else {
			if (dp[i + 1] != -1) {
				return dp[i + 1];
			}
			int cur = i == -1 ? Integer.MIN_VALUE : arr1[i];
			int f = find(arr2, cur);
			int ans = Integer.MAX_VALUE;
			int times = 0;
			for (int j = i + 1; j <= arr1.length; j++) {
				if (j == arr1.length || cur < arr1[j]) {
					int next = process2(arr1, arr2, j, dp);
					if (next != Integer.MAX_VALUE) {
						ans = Math.min(ans, times + next);
					}
				}
				if (f != -1 && f < arr2.length) {
					cur = arr2[f++];
					times++;
				} else {
					break;
				}
			}
			dp[i + 1] = ans;
			return ans;
		}
	}

	// 迭代版的动态规划
	public static int makeArrayIncreasing3(int[] arr1, int[] arr2) {
		Arrays.sort(arr2);
		int m = 1;
		for (int i = 1; i < arr2.length; i++) {
			if (arr2[i] != arr2[m - 1]) {
				arr2[m++] = arr2[i];
			}
		}
		int n = arr1.length;
		int[] dp = new int[n + 2];
		for (int i = n - 1; i >= -1; i--) {
			int cur = i == -1 ? Integer.MIN_VALUE : arr1[i];
			int f = find3(arr2, m, cur);
			dp[i + 1] = Integer.MAX_VALUE;
			int times = 0;
			for (int j = i + 1; j <= n; j++) {
				if (j == n || cur < arr1[j]) {
					if (dp[j + 1] != Integer.MAX_VALUE) {
						dp[i + 1] = Math.min(dp[i + 1], times + dp[j + 1]);
					}
				}
				if (f != -1 && f < m) {
					cur = arr2[f++];
					times++;
				} else {
					break;
				}
			}
		}
		return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
	}

	public static int find3(int[] arr2, int size, int num) {
		int l = 0;
		int r = size - 1;
		int m, ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr2[m] > num) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

}
