package leetcode;

import java.util.LinkedList;

// 求滑动窗口的最大值
// https://www.cnblogs.com/greyzeng/p/14463104.html
public class LeetCode_0239_SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        if (k == nums.length) {
            return new int[]{maxOfArr(nums)};
        }
        // k大小的窗口，有n-k+1个最大值
        int[] res = new int[nums.length - k + 1];
        // 从头到尾，由大到小
        LinkedList<Integer> qMax = new LinkedList<>();
        int i = 0;
        int r = 0;
        while (r < nums.length) {
            while (!qMax.isEmpty() && nums[qMax.getLast()] <= nums[r]) {
                qMax.pollLast();
            }
            qMax.addLast(r);
            if (qMax.peekFirst() == r - k) {
                qMax.pollFirst();
            }
            if (r >= k - 1) {
                res[i++] = nums[qMax.peekFirst()];
            }
            r++;
        }
        return res;
    }

    public static int maxOfArr(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            max = Math.max(n, max);
        }
        return max;
    }
}
