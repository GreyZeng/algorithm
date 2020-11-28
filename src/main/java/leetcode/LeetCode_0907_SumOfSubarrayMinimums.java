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

// TODO
// 单调栈
public class LeetCode_0907_SumOfSubarrayMinimums {
    public static int sumSubarrayMins(int[] arr) {
        return -1;
    }
}
