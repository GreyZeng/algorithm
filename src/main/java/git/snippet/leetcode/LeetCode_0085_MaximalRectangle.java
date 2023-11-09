package git.snippet.leetcode;

// 找出只包含1的最大矩形的面积
// https://leetcode-cn.com/problems/maximal-rectangle/
// https://www.lintcode.com/problem/510/
// 暴力解，N * N 的矩阵，内部有N^4次方个矩形
// 最优解 O(N^2)
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class LeetCode_0085_MaximalRectangle {
    public static int maximalRectangle(char[][] m) {
        int[] help = new int[m[0].length];
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            merge(help, m[i]);
            max = Math.max(max, largestRectangleArea(help));
        }
        return max;
    }

    public static void merge(int[] help, char[] m) {
        for (int i = 0; i < help.length; i++) {
            help[i] = m[i] == '0' ? 0 : help[i] + 1;
        }
    }

    public static int largestRectangleArea(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int max = arr[0];
        int[] stack = new int[arr.length];
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            while (index != -1 && arr[stack[index]] >= arr[i]) {
                int popIndex = stack[index--];
                max = Math.max(max, arr[popIndex] * (i - (index == -1 ? -1 : stack[index]) - 1));
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int popIndex = stack[index--];
            max = Math.max(max, arr[popIndex] * (arr.length - (index == -1 ? -1 : stack[index]) - 1));
        }
        return max;
    }
}
