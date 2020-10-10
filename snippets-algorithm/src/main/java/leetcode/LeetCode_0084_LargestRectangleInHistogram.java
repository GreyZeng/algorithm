package leetcode;

import java.util.Stack;

public class LeetCode_0084_LargestRectangleInHistogram {

    // 单调栈
    // 每个位置做高的矩形
    // 找到i离你最近的比你小的位置在哪里？
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int N = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int c = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - k - 1) * heights[c]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int c = stack.pop();
            int j = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, heights[c] * (N - j - 1));
        }
        return max;
    }

}
