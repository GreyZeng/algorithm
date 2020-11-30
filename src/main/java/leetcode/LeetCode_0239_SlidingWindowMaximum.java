package leetcode;

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
    // 滑动窗口最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] ans = new int[nums.length - k + 1];
        // 存下标，防止过期
        LinkedList<Integer> qMax = new LinkedList<>();
        int R = 0; // 有效位置的下一个位置
        int index = 0;
        while (R < nums.length) {
            while (!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[R]) {
                qMax.pollLast();
            }
            qMax.addLast(R);
            // 因为R保存的是过期位置的下一个下一个位置，所以，一定会有第一个过期下标出现
            if (qMax.peekFirst() == R - k) {
                qMax.pollFirst();
            }
            if (R >= k - 1) {
                ans[index++] = nums[qMax.peekFirst()];
            }
            R++;
        }
        return ans;
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        //int[] arr = {9, 10};
        int k = 3;
        int[] a = maxSlidingWindow(arr, k);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
