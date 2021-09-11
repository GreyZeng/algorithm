package lintcode;

import java.util.Stack;

//描述
//        给出的n个非负整数表示每个直方图的高度，每个直方图的宽均为1，在直方图中找到最大的矩形面积。
//
//        样例
//        样例 1：
//
//        输入：
//
//        height = [2,1,5,6,2,3]
//        输出：
//
//        10
//        解释：
//
//        第三个和第四个直方图截取矩形面积为2*5=10。
//
//        样例 2：
//
//        输入：
//
//        height = [1,1]
//        输出：
//
//        2
//        解释：
//
//        第一个和第二个直方图截取矩形面积为2*1=2。
//https://www.lintcode.com/problem/122/
public class LintCode_0122_LargestRectangleInHistogram {
    // 必须以某个矩形长度作为高的矩形最大面积是多少 ,枚举所有情况，答案必在其中
    // 对于一个普遍位置index，假设m和n是左右两边离他最近的比他小的线段长度，矩形面积 ： heights[index] * (m - n - 1)
    // 最大矩形面积
    public static int largestRectangleArea(int[] heights) {
        if (heights == null) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int max = heights[0];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int index = stack.pop();
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(heights[index] * (right - left - 1), max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            max = Math.max(max, heights[index] * (n - (stack.isEmpty() ? -1 : stack.peek()) - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,1};
        int i = largestRectangleArea(arr);
        System.out.println(i);
    }
}
