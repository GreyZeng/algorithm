package git.snippet.monostack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 笔记见：https://www.cnblogs.com/greyzeng/p/16326526.html
 *
 * @see NowCoder_MonotonousStack 单调栈 左边右边离它最近比它小的数 O（N） 数组中有重复 Stack<List<Integer>> 数组中无重复
 * Stack<Integer> 栈底到栈顶从小到大 弹出的时候，假设弹出的值是A，那么让它弹出的值就是它右边离它最近的最小值 原先A压的是谁，那么谁就是A左边离它最近的最小值
 * https://www.nowcoder.com/questionTerminal/2a2c00e7a88a498693568cef63a4b7bb
 * https://www.nowcoder.com/questionTerminal/e3d18ffab9c543da8704ede8da578b55
 */
public class Code_MonoStack {
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        // 栈底到栈顶从小到大
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int index = stack.pop();
                res[index][0] = stack.isEmpty() ? -1 : stack.peek();
                res[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            res[index][1] = -1;
            res[index][0] = stack.isEmpty() ? -1 : stack.peek();
        }
        return res;
    }

    public static int[][] getNearLess(int[] arr) {
        int[][] result = new int[arr.length][2];
        // 重复的元素压入一批
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> selected = stack.pop();
                // 原先是结算一个，现在是结算一批
                for (int popIndex : selected) {
                    result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    result[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            for (int popIndex : list) {
                result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                result[popIndex][1] = -1;
            }
        }
        return result;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
