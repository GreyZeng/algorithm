package leetcode;

import java.util.Stack;

public class LeetCode_0084_LargestRectangleInHistogram {

	// 单调栈
	// 每个位置做高的矩形
	// 找到i离你最近的比你小的位置在哪里？
	public static int largestRectangleArea(int[] heights) {
		if (null == heights || heights.length == 0) {
			return 0;
		}
		int n = heights.length;
		if (n == 1) {
			return heights[0];
		}
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
				int v = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek();
				int right = i;
				int area = (right - left - 1) * heights[v];
				// System.out.println("值：" + heights[v] + " 左边极限"+ left + "右边极限"+right);
				max = Math.max(max, area);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int v = stack.pop();
			int left = stack.isEmpty() ? -1 : stack.peek();
			int right = heights.length;
			int area = (right - left - 1) * heights[v];
			max = Math.max(max, area);
			// System.out.println("值：" + heights[v] + " 左边极限"+ left + "右边极限"+right);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 6, 2, 3 };
		System.out.println(largestRectangleArea(arr));
	}

}
