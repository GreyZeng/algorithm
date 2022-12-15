package 练习题.leetcode.medium;

// 数据量
//1 <= nums.length <= 1000
// -2^31 <= nums[i] <= 2^31 - 1
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// https://leetcode-cn.com/problems/find-peak-element/
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 即对于所有有效的 i 都有 arr[i] != arr[i + 1]
// 二分法
// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
public class LeetCode_0162_FindPeakElement {
	public static int findPeakElement(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		if (arr.length == 1) {
			return 0;
		}
		if (arr[1] < arr[0]) {
			return 0;
		}
		if (arr[arr.length - 1] > arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int ans = -1;
		int l = 1;
		int r = arr.length - 2;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
				return mid;
			} else if (arr[mid] < arr[mid + 1]) {
				l = mid + 1;
			} else if (arr[mid] < arr[mid - 1]) {
				r = mid - 1;
			} else {
				// 题目要求下，不会走到这个分支
			}
		}
		return ans;
	}
}
