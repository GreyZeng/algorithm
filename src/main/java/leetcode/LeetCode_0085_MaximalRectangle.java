// 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

// 示例:

// 输入:
// [
//   ["1","0","1","0","0"],
//   ["1","0","1","1","1"],
//   ["1","1","1","1","1"],
//   ["1","0","0","1","0"]
// ]
// 输出: 6
// 暴力解，N * N 的矩阵，内部有N^4次方
// 最优解 O(N^2)
package leetcode;

import java.util.Stack;

public class LeetCode_0085_MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] help = new int[matrix[0].length];
        int max = 0;
        for (char[] chars : matrix) {
            merge(help, chars);
            max = Math.max(max, getMax(help));
        }
        return max;
    }

    private static void merge(int[] help, char[] m2) {
        for (int i = 0; i < help.length; i++) {
            help[i] = m2[i] == '0' ? 0 : (help[i] + 1);
        }
    }

    private static int getMax(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int m = stack.pop();
                int r = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(arr[m] * (i - r - 1), max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int m = stack.pop();
            int r = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(arr[m] * (arr.length - 1 - r), max);
        }
        return max;
    }
}
