package leetcode.hard;

import java.util.LinkedList;

// 求滑动窗口的最大值
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
