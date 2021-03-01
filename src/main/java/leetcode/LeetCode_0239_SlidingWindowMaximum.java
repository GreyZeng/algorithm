package leetcode;

import java.util.Deque;
import java.util.LinkedList;

//You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//        Return the max sliding window.
//
//
//
//        Example 1:
//
//        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//        Output: [3,3,5,5,6,7]
//        Explanation:
//        Window position                Max
//        ---------------               -----
//        [1  3  -1] -3  5  3  6  7       3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//        Example 2:
//
//        Input: nums = [1], k = 1
//        Output: [1]
//        Example 3:
//
//        Input: nums = [1,-1], k = 1
//        Output: [1,-1]
//        Example 4:
//
//        Input: nums = [9,11], k = 2
//        Output: [11]
//        Example 5:
//
//        Input: nums = [4,-2], k = 2
//        Output: [4]
//
//
//        Constraints:
//
//        1 <= nums.length <= 10^5
//        -10^4 <= nums[i] <= 10^4
//        1 <= k <= nums.length
public class LeetCode_0239_SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            return nums;
        }
        if (k == n) {
            return new int[]{maxOfArr(nums, n)};
        }
        // 数组长度是n，窗口是k，则结果数组长度为n - k + 1
        int[] ans = new int[n - k + 1];
        LinkedList<Integer> qMax = new LinkedList<>();
        int r = 0;
        while (r < n) {
            // TODO

        }

        return ans;

    }

    private static int maxOfArr(int[] nums, int n) {
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}
