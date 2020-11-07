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
public class LeetCode_0493_ReversePairs {

	public static int reversePairs(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		return p(nums, 0, nums.length - 1);
	}

	private static int p(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int M = L + ((R - L) >> 1);
		return p(arr, L, M) + p(arr, M + 1, R) + merge(arr, L, M, R);
	}

	private static int merge(int[] arr, int L, int M, int R) {
		int pair = 0;
		int startIndex = M; // 左边部分从M开始
		int rightEdge = R; // 右边部分从R开始
		while (startIndex >= L && rightEdge >= M + 1) { 
			if ((long) arr[startIndex] > 2 * (long) arr[rightEdge]) {
				pair += ((rightEdge - M));
				startIndex--;
			} else {
				rightEdge--;
			}
		}
		// ---end 在merge之前先统计//

		// 以下为mergeSort的原始代码
		int len = R - L + 1;
		int[] help = new int[len];
		int l = L;
		int mid = M + 1;
		int i = 0;
		while (l <= M && mid <= R) {
			help[i++] = arr[l] < arr[mid] ? arr[l++] : arr[mid++];
		}
		while (l <= M) {
			help[i++] = arr[l++];
		}
		while (mid <= R) {
			help[i++] = arr[mid++];
		}
		i = 0;
		for (int t : help) {
			arr[L + (i++)] = t;
		}
		return pair;
	}

}
