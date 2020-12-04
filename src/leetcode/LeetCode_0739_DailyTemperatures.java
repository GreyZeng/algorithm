/*
Given a list of daily temperatures T, return a list such that, for each day in the input,
tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

		For example,
		given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
		your output should be [1, 1, 4, 2, 1, 1, 0, 0].

		Note: The length of temperatures will be in the range [1, 30000].
		Each temperature will be an integer in the range [30, 100].
*/
package leetcode;


import java.util.ArrayList;
import java.util.Stack;

// 单调栈
public class LeetCode_0739_DailyTemperatures {

    public static int[] dailyTemperatures(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[] result = new int[n];
        Stack<ArrayList<Integer>> mono = new Stack<>();
        ArrayList<Integer> indexes;
        for (int i = 0; i < n; i++) {
            while (!mono.isEmpty() && arr[mono.peek().get(0)] < arr[i]) {
                indexes = mono.pop();
                for (int item : indexes) {
                    result[item] = i - item;
                }
            }
            if (!mono.isEmpty()  && arr[mono.peek().get(0)] == arr[i]) {
                mono.peek().add(i);
            } else {
                indexes = new ArrayList<>();
                indexes.add(i);
                mono.push(indexes);
            }
        }
        return result;
    }

}
