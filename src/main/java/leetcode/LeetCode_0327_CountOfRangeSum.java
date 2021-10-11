// Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.

// Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.

// Example 1:

// Input: nums = [-2,5,-1], lower = -2, upper = 2
// Output: 3
// Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2. 
// Example 2:

// Input: nums = [0], lower = 0, upper = 0
// Output: 1

// Constraints:

// 1 <= nums.length <= 10^5
// -2^31 <= nums[i] <= 2^31 - 1
// -10^5 <= lower <= upper <= 10^5
// The answer is guaranteed to fit in a 32-bit integer.
package leetcode;
// 方法1：归并排序

// 思路
// 1. 前缀和加速求区间和
// 2. 必须以i结尾的达标子数组有多少个

import java.util.HashSet;

// 方法2：有序表方式
// 思路：
// 子数组必须以i位置结尾，有多少是落在[a,b], 就可以通过前缀和
// 0 - 0
// 0 - 1
// ...
// 0 - i-1
// 有多少个累加和落在 [sum - a, sum -b] 上
// 前缀和加入到有序表

// 有序表提供add(num) [可以加入重复数字] 和 search(L,R)【L...R中有多少个，其实只需要提供<num的数有多少个这个接口加工而来】 两个接口即可
// 左滑不处理，右滑累加，每个数据项里面包含节点个数

public class LeetCode_0327_CountOfRangeSum {
	public static int countRangeSum(int[] nums, int lower, int upper) {
		int size = nums.length;
		long[] sum = new long[size];
		sum[0] = nums[0];
		for (int i = 1; i < size; i++) {
			sum[i] = nums[i] + sum[i - 1];
		}
		return p(sum, 0, size - 1, lower, upper);
	}

	public static int p(long[] sum, int i, int j, int lower, int upper) {
		if (i == j) {
			if (sum[i] >= lower && sum[j] <= upper) {
				return 1;
			}
			return 0;
		}
		int mid = i + ((j - i) >> 1);
		return p(sum, i, mid, lower, upper) + p(sum, mid + 1, j, lower, upper) + merge(sum, i, mid, j, lower, upper);
	}

	private static int merge(long[] sum, int i, int mid, int j, int lower, int upper) {
		// 单调性->滑动窗口
		int pair = 0;
		int L = i;
		int R = i;
		int S = mid + 1;
		while (S <= j) {
			long max = sum[S] - lower;
			long min = sum[S] - upper;
			while (L <= mid && sum[L] < min) {
				L++;
			}
			while (R <= mid && sum[R] <= max) {
				R++;
			}
			pair += (R - L);
			S++;
		}

		// mergeSort经典代码
		long[] helper = new long[j - i + 1];
		int l = i;
		int r = mid + 1;
		int index = 0;
		while (l <= mid && r <= j) {
			if (sum[l] > sum[r]) {
				helper[index++] = sum[r++];
			} else {
				helper[index++] = sum[l++];
			}
		}
		while (l <= mid) {
			helper[index++] = sum[l++];
		}
		while (r <= j) {
			helper[index++] = sum[r++];
		}
		int k = 0;
		for (long num : helper) {
			sum[i + (k++)] = num;
		}
		return pair;
	}

	// TODO 改有序表的方式实现。
	public static int countRangeSum2(int[] nums, int lower, int upper) {
		// 黑盒，加入数字（前缀和），不去重，可以接受重复数字
		// < num , 有几个数？
		SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
		long sum = 0;
		int ans = 0;
		treeSet.add(0);// 一个数都没有的时候，就已经有一个前缀和累加和为0，
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// [sum - upper, sum - lower]
			// [10, 20] ?
			// < 10 ? < 21 ?
			long a = treeSet.lessKeySize(sum - lower + 1);
			long b = treeSet.lessKeySize(sum - upper);
			ans += a - b;
			treeSet.add(sum);
		}
		return ans;
	}

	public static class SBTNode {
		public long key;
		public SBTNode l;
		public SBTNode r;
		public long size; // 不同key的size
		public long all; // 总的size

		public SBTNode(long k) {
			key = k;
			size = 1;
			all = 1;
		}
	}

	public static class SizeBalancedTreeSet {
		private SBTNode root;
		private HashSet<Long> set = new HashSet<>();

		private SBTNode rightRotate(SBTNode cur) {
			long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
			SBTNode leftNode = cur.l;
			cur.l = leftNode.r;
			leftNode.r = cur;
			leftNode.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
			// all modify
			leftNode.all = cur.all;
			cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
			return leftNode;
		}

		private SBTNode leftRotate(SBTNode cur) {
			long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
			SBTNode rightNode = cur.r;
			cur.r = rightNode.l;
			rightNode.l = cur;
			rightNode.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
			// all modify
			rightNode.all = cur.all;
			cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
			return rightNode;
		}

		private SBTNode maintain(SBTNode cur) {
			if (cur == null) {
				return null;
			}
			long leftSize = cur.l != null ? cur.l.size : 0;
			long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
			long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
			long rightSize = cur.r != null ? cur.r.size : 0;
			long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
			long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
			if (leftLeftSize > rightSize) {
				cur = rightRotate(cur);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (leftRightSize > rightSize) {
				cur.l = leftRotate(cur.l);
				cur = rightRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (rightRightSize > leftSize) {
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur = maintain(cur);
			} else if (rightLeftSize > leftSize) {
				cur.r = rightRotate(cur.r);
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			}
			return cur;
		}

		private SBTNode add(SBTNode cur, long key, boolean contains) {
			if (cur == null) {
				return new SBTNode(key);
			} else {
				cur.all++;
				if (key == cur.key) {
					return cur;
				} else { // 还在左滑或者右滑
					if (!contains) {
						cur.size++;
					}
					if (key < cur.key) {
						cur.l = add(cur.l, key, contains);
					} else {
						cur.r = add(cur.r, key, contains);
					}
					return maintain(cur);
				}
			}
		}

		public void add(long sum) {
			boolean contains = set.contains(sum);
			root = add(root, sum, contains);
			set.add(sum);
		}

		public long lessKeySize(long key) {
			SBTNode cur = root;
			long ans = 0;
			while (cur != null) {
				if (key == cur.key) {
					return ans + (cur.l != null ? cur.l.all : 0);
				} else if (key < cur.key) {
					cur = cur.l;
				} else {
					ans += cur.all - (cur.r != null ? cur.r.all : 0);
					cur = cur.r;
				}
			}
			return ans;
		}

		// > 7 8...
		// <8 ...<=7
		public long moreKeySize(long key) {
			return root != null ? (root.all - lessKeySize(key + 1)) : 0;
		}

	}
}
