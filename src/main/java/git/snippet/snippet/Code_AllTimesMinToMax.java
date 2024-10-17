package git.snippet.snippet;

import java.util.Stack;

// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
// 链接：https://www.nowcoder.com/questionTerminal/e6e57ef2771541dfa2f1720e50bebc9a
// 区间中的最小数 * 区间所有数的和的最大值
public class Code_AllTimesMinToMax {
    public static int max2(int[] arr) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        // 前缀和数组优化
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int ans = arr[0] * arr[0];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int popIndex = stack.pop();
                // 结算
                ans =
                        Math.max(arr[popIndex] * (sum[i - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()])), ans);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            ans =
                    Math.max(
                            arr[popIndex] * (sum[arr.length - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()])),
                            ans);
        }
        return ans;
    }

    // 暴力方法
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
