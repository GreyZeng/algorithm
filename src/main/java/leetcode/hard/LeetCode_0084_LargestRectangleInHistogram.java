package leetcode.hard;

import java.util.Stack;

//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
//		求在该柱状图中，能够勾勒出来的矩形的最大面积。
//输入：heights = [2,1,5,6,2,3]
//		输出：10
//输入： heights = [2,4]
//		输出： 4
//提示：
//
//		1 <= heights.length <=105
//		0 <= heights[i] <= 104
// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
// https://www.lintcode.com/problem/122/
public class LeetCode_0084_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        // 可以用数组替代Stack
        Stack<Integer> stack = new Stack<>();
        int ans = arr[0];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int popIndex = stack.pop();
                // 结算
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, arr[popIndex] * (i - left - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            // 结算
            int right = arr.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, arr[popIndex] * (right - left - 1));
        }
        return ans;
    }
}
