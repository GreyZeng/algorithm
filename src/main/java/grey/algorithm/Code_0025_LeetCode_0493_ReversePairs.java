package grey.algorithm;

// 逆序对问题
// Given an integer array nums,
// return the number of reverse pairs in the array.
// A reverse pair is a pair (i, j) where:
// 0 <= i < j < nums.length and
// nums[i] > 2 * nums[j].
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// https://leetcode.com/problems/reverse-pairs/
// https://www.lintcode.com/problem/532/
// 方法1： 归并排序
// TODO 方法2： 树状数组
// 小和问题是一个数右边有多少个数比它自己大
// 降序对问题就是求一个数右边有多少个数比它小，可以从右往左来算
public class Code_0025_LeetCode_0493_ReversePairs {
	private static final int MAXN = 50001;
	private static final int[] help = new int[MAXN];

	public static int reversePairs(int[] arr) {
		if (null == arr || arr.length <= 1) {
			return 0;
		}
		return counts(arr, 0, arr.length - 1);
	}

	public static int counts(int[] arr, int l, int r) {
		if (l == r)
			return 0;
		int m = l + ((r - l) >> 1);
		return counts(arr, l, m) + counts(arr, m + 1, r) + merge(arr, l, m, r);
	}

	public static int merge(int[] arr, int l, int m, int r) {
		int ans = 0;
		int i = l; // 卡左边界
		int j = m + 1; // 卡右边界
		while (i <= m) {
			// 滑动窗口，不回退
			while (j <= r && (long)arr[i] > (long)arr[j] * 2) {
				j++;
			}
			ans += j - m - 1;
			i++;
		}
		int s = l;
		int e = m + 1;
		i = l;
		while (s <= m && e <= r) {
			if (arr[s] <= arr[e]) {
				help[i++] = arr[s++];
			} else {
				help[i++] = arr[e++];
			}
		}
		while (s <= m) {
			help[i++] = arr[s++];
		}
		while (e <= r) {
			help[i++] = arr[e++];
		}
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
		return ans;
	}

}
