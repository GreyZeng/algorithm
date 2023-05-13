
package mergesort;

// 逆序对问题
//Given an integer array nums, 
// return the number of reverse pairs in the array.
//A reverse pair is a pair (i, j) where:
//0 <= i < j < nums.length and
//nums[i] > 2 * nums[j].
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// https://leetcode.com/problems/reverse-pairs/
// 方法1： 归并排序
// TODO 方法2： 树状数组
// 小和问题是一个数右边有多少个数比它自己大
// 降序对问题就是求一个数右边有多少个数比它小，可以从右往左来算
public class LeetCode_0493_ReversePairs {

	public int reversePairs(int[] nums) {
		if (nums.length <= 1) {
			return 0;
		}
		return count(nums, 0, nums.length - 1);
	}

	public int count(int[] nums, int l, int r) {
		if (l == r) {
			return 0;
		}
		int m = ((r - l) >> 1) + l;
		return count(nums, l, m) + count(nums, m + 1, r) + merge(nums, l, m, r);
	}

	public int merge(int[] nums, int l, int m, int r) {
		int ans = 0;
		int ls = l;
		int rs = m + 1;
		while (ls <= m && rs <= r) {
			if ((long) nums[ls] > (long) nums[rs] + (long) nums[rs]) {
				ans += (r - rs + 1);
				ls++;
			} else {
				rs++;
			}
		}
		int[] help = new int[r - l + 1];
		int i = 0;
		ls = l;
		rs = m + 1;
		while (ls <= m && rs <= r) {
			if (nums[ls] >= nums[rs]) {
				help[i++] = nums[ls++];
			} else {
				help[i++] = nums[rs++];
			}
		}

		while (ls <= m) {
			help[i++] = nums[ls++];
		}
		while (rs <= r) {
			help[i++] = nums[rs++];
		}
		for (i = 0; i < help.length; i++) {
			nums[l + i] = help[i];
		}
		return ans;
	}
}
