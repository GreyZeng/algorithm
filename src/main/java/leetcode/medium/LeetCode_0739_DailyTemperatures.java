/*
Given a list of daily temperatures T, return a list such that, for each day in the input,
tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

		For example,
		given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
		your output should be [1, 1, 4, 2, 1, 1, 0, 0].

		Note: The length of temperatures will be in the range [1, 30000].
		Each temperature will be an integer in the range [30, 100].
*/
package leetcode.medium;


import java.util.ArrayDeque;
import java.util.Deque;

// 单调栈
// https://leetcode.cn/problems/daily-temperatures/
public class LeetCode_0739_DailyTemperatures {
    // 一个数右边离它最近的比它大的数是什么
    public static int[] dailyTemperatures(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        int n = arr.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int popIndex = stack.pop();
                ans[popIndex] = i - popIndex;
            }
            stack.push(i);
        }
        // 不需要弹，因为本身初始化就是0，而且栈中的元素本身就没有右边离它最近的比它大的数
        return ans;
    }

}
