package leetcode.hard;

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
// https://www.cnblogs.com/greyzeng/p/14463104.html
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
        // 头部进，尾部出
        // 从头到尾依次从大到小
        LinkedList<Integer> qMax = new LinkedList<>();
        int r = 0;
        int index = 0;
        while (r < n) {
            while (!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[r]) {
                // 给nums[r] 腾出位置
                qMax.pollLast();
            }
            // 现在qMax尾部的值一定大于r
            // 所以可以放心的把r加入到尾部中去
            qMax.addLast(r);
            // 如果此时双端队列中头部的值是过期下标
            // 比如r来到3位置，窗口k为3，那么过期下标就是0
            // 比如r来到4位置，窗口k为3，那么过期下标就是1
            // 比如r来到5位置，窗口k为3，那么过期下标就是2
            // 比如r来到6位置，窗口k为3，那么过期下标就是3
            // 比如r来到7位置，窗口k为3，那么过期下标就是4
            // ...
            if (qMax.peekFirst() == r - k) {
                // 弹出过期下标
                qMax.pollFirst();
            }

            // 窗口形成了，每次加入一个数收集一个答案
            if (r >= k - 1) {
                ans[index++] = nums[qMax.peekFirst()];
            }
            r++;
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

    public static void main(String[] args) {
        int[] num = {7, 2, 4};
        int k = 2;
        System.out.println(maxSlidingWindow(num, k));

    }

}
