/*Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.*/
package leetcode;

// TODO 方法1： 树状数组
// 方法2： 归并排序
// 小和问题是一个数右边有多少个数比它自己大
// 降序对问题就是求一个数右边有多少个数比它小，可以从右往左来算
public class LeetCode_0493_ReversePairs {

	public static int reversePairs(int[] A) {
		if (null == A || A.length < 2) {
			return 0;
		}
		int size = A.length;
		return p(A, 0, size - 1);
	}

	public static int p(int[] A, int L, int R) {
		if (L == R) {
			return 0;
		}
		int mid = ((R - L) >> 1) + L;
		return p(A, L, mid) + p(A, mid + 1, R) + merge(A, L, mid, R);
	}

	public static int merge(int[] A, int l, int mid, int r) {
		int s = l;
		int e = mid + 1;
		int pair = 0;
		while (s <= mid && e <= r) {
			if ((long) A[s] - (long) A[e] > (long) (A[e])) {
				pair += (r - e + 1);
				s++;
			} else {
				e++;
			}
		}
		int[] helper = new int[r - l + 1];
		int i = l;
		int j = mid + 1;
		int index = 0;

		while (i <= mid && j <= r) {
			if (A[i] > A[j]) {
				helper[index++] = A[i++];
			} else {
				helper[index++] = A[j++];
			}
		}
		while (i <= mid) {
			helper[index++] = A[i++];
		}
		while (j <= r) {
			helper[index++] = A[j++];
		}
		int k = 0;
		for (int num : helper) {
			A[l + (k++)] = num;
		}

		return pair;
	}

}
