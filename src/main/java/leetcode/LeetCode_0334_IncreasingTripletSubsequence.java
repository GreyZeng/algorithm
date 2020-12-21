/*Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false*/
package leetcode;

// 最长递增子序列是否到达了三
public class LeetCode_0334_IncreasingTripletSubsequence {

	public static boolean increasingTriplet(int[] arr) {
		if (arr == null || arr.length < 3) {
			return false;
		}

		int[] ends = new int[3];
		ends[0] = arr[0];
		int l = 0;
		int r = 0;
		int right = 0;
		int N = arr.length;
		for (int i = 1; i < N; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				int m = (r + l) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}

			}
			right = Math.max(l, right);
			if (right == 2) {
				return true;
			}
			ends[l] = arr[i];
		}
		return false;
	}

}
