package snippet;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub， 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？ tips：前缀和数组可以用来求sub累加和
 */
public class Code_0056_AllTimesMinToMax {
	public static int max(int[] arr) {
		int[] preSum = preSum(arr);
		Stack<Integer> stack = new Stack<>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int v = stack.pop();
				max = Math.max(arr[v] * (preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])), max);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int v = stack.pop();
			max = Math.max(arr[v] * (preSum[arr.length - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])), max);
		}
		return max;
	}

	// 求前缀和数组 方便求累加和
	public static int[] preSum(int[] arr) {
		int[] pre = new int[arr.length];
		pre[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			pre[i] = pre[i - 1] + arr[i];
		}
		return pre;
	}

}
