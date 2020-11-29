//Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
//
//        Since the answer may be large, return the answer modulo 10^9 + 7.
//
//
//
//        Example 1:
//
//        Input: [3,1,2,4]
//        Output: 17
//        Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
//        Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
//
//
//        Note:
//
//        1 <= A.length <= 30000
//        1 <= A[i] <= 30000
//
//
//
//
//        Example 1:
//
//        Input: arr = [3,1,2,4]
//        Output: 17
//        Explanation:
//        Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
//        Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//        Sum is 17.
//        Example 2:
//
//        Input: arr = [11,81,94,43,3]
//        Output: 444
//
//
//        Constraints:
//
//        1 <= arr.length <= 3 * 10^4
//        1 <= arr[i] <= 3 * 10^4
package leetcode;

import java.util.Stack;

// 单调栈
public class LeetCode_0907_SumOfSubarrayMinimums {

	public static int sumSubarrayMins(int[] arr) {
		int MOD = (int) 1e9 + 7;
		long max = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int v = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek();
				int right = i;
				long times = (v - left) * (right - v);
				max += arr[v] * times;

			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int v = stack.pop();
			int left = stack.isEmpty() ? -1 : stack.peek();
			int right = arr.length;
			long times = (v - left) * (right - v);
			max += arr[v] * times;

		}
		return (int) (max % MOD);
	}

	public static void main(String[] args) {
		int[] nums = { 11, 81, 94, 43 };
		System.out.println(sumSubarrayMins(nums));
	}
}
