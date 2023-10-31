package git.snippet.leetcode;

import java.util.Stack;

// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
// 输入：heights = [2,1,5,6,2,3]
// 输出：10
// 输入： heights = [2,4]
// 输出： 4
// 提示：
//
// 1 <= heights.length <=105
// 0 <= heights[i] <= 104
// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
// https://www.lintcode.com/problem/122/
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class LeetCode_0084_LargestRectangleInHistogram {
  // 单调栈
  // 找arr[m]左右两边比arr[m]小的离arr[m]最近的数是多少，假设为i，j，则
  // 必须以arr[i]位置为左边界的最大矩形为：arr[i]*(i - j - 1)
  // 左边越界则为-1，右边越界则为arr.length
  public static int largestRectangleArea(int[] arr) {
    if (arr == null || arr.length < 1) {
      return 0;
    }
    if (arr.length == 1) {
      return arr[0];
    }
    int max = arr[0];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        int m = stack.pop();
        max = Math.max(max, arr[m] * (i - (stack.isEmpty() ? -1 : stack.peek()) - 1));
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      int popIndex = stack.pop();
      max = Math.max(max, arr[popIndex] * (arr.length - (stack.isEmpty() ? -1 : stack.peek()) - 1));
    }
    return max;
  }

  // 用数组来模拟Stack，加速常数时间
  public static int largestRectangleArea2(int[] arr) {
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
