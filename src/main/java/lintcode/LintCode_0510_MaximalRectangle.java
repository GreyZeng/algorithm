package lintcode;

import java.util.Stack;

// https://www.lintcode.com/problem/510/
// 给你一个二维矩阵，权值为False和True，找到一个最大的矩形，使得里面的值全部为True，输出它的面积
public class LintCode_0510_MaximalRectangle {
    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] help = new int[matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    help[j] = matrix[0][j] ? 1 : 0;
                } else {
                    help[j] += matrix[i][j] ? 1 : (-help[j]);
                }
            }
            max = Math.max(max, max(help));
        }
        return max;
    }

    public static int max(int[] help) {
        Stack<Integer> stack = new Stack<>();
        int n = help.length;
        int max = help[0];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && help[stack.peek()] > help[i]) {
                int index = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                max = Math.max(help[index] * (right - left - 1), max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = n;
            max = Math.max(max, help[index] * (right - left - 1));
        }
        return max;
    }
}
