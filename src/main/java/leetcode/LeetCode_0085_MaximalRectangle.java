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
	// 一定要以某一行作为底的矩形中，最大矩形是多少
	// 和全局最大PK一下，如果比全局最大还大，则更新全局最大值
	// 最后返回全局最大值
	public static int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int[] help = new int[matrix[0].length];
	 
		int max = 0;
		int row = matrix.length;
		for (int i = 0; i < row; i++) {
			merge(help, matrix[i]);
			max = Math.max(max, getMax(help));
		}
		return max;
	}

	// 把m1叠加
	private static void merge(int[] help, char[] m2) {
		for (int i = 0; i < help.length; i++) {
			help[i] = m2[i] == '0' ? 0 :  (help[i] +  1);
		}
	}

	private static int getMax(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int m = stack.pop();
				if (stack.isEmpty()) {
					max = Math.max(max, (m + 1) * arr[m]);
				} else {
					max = Math.max(max, (i - m + 1) * arr[m] );
				}
				
			}
			if (stack.isEmpty()) {
				max = Math.max(arr[i] * (i + 1), max);
			} else {
				max = Math.max(arr[i] * (i - stack.peek()), max);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int i = stack.pop();
			if (stack.isEmpty()) {
				max = Math.max(arr[i] * (i + 1), max);
			} else {
				max = Math.max(arr[i] * (i - stack.peek()), max);
			}
			 
		}
		return max;
	}
	public static void main(String[] args) {
		char[][] rec = {{'0','0','1'} ,{'1','1','1'}};
		int res = maximalRectangle(rec);
		System.out.println(res);
	}

}
