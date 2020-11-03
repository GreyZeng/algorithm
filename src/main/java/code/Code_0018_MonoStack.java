package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 * 左边右边离它最近比它小的数 O（N）
 * 数组中有重复 Stack<List<Integer>>
 * 数组中无重复 Stack<Integer>
 * 栈底到栈顶从小到大
 * 弹出的时候，假设弹出的值是A，那么让它弹出的值就是它右边离它最近的最小值
 * 原先A压的是谁，那么谁就是A左边离它最近的最小值
 */
public class Code_0018_MonoStack {
    // 无重复的情况
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] result = new int[n][2];
        Stack<Integer> mono = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!mono.isEmpty() && arr[mono.peek()] > arr[i]) {
                int index = mono.pop();
                result[index][0] = mono.isEmpty() ? -1 : mono.peek();
                result[index][1] = i;
            }
            mono.push(i);
        }
        while (!mono.isEmpty()) {
            int index = mono.pop();
            result[index][0] = mono.isEmpty() ? -1 : mono.peek();
            result[index][1] = -1;
        }
        return result;
    }


    public static int[][] getNearLess(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] result = new int[n][2];
        Stack<List<Integer>> mono = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!mono.isEmpty() && !mono.peek().isEmpty() && arr[mono.peek().get(0)] > arr[i]) {
                List<Integer> indexes = mono.pop();
                for (int item : indexes) {
                    result[item][0] = mono.isEmpty() ? -1 : mono.peek().get(mono.peek().size() - 1);
                    result[item][1] = i;
                }
            }
            if (!mono.isEmpty() && !mono.peek().isEmpty() && arr[mono.peek().get(0)] == arr[i]) {
                mono.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                mono.add(list);
            }
        }
        while (!mono.isEmpty()) {
            List<Integer> list = mono.pop();
            for (int item : list) {
                result[item][0] = mono.isEmpty() ? -1 : mono.peek().get(mono.peek().size() - 1);
                result[item][1] = -1;
            }
        }
        return result;
    }
}
