package git.snippet.nowcoder;

import java.util.Scanner;

// 子矩阵的最大累加和
// 测评链接：https://www.nowcoder.com/questionTerminal/cb82a97dcd0d48a7b1f4ee917e2c0409
// 压缩数组技巧 O(N^2 * M) --> 考虑一下N和M的长度
// 启发：数组的最大累加和: https://leetcode-cn.com/problems/maximum-subarray/
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class NowCoder_MaxSumSquare {
    public static int maxSum(int[][] matrix) {
        // max用于记录全局最大值，它至少可以是: 必须是第0行的子数组的最大累加和
        int max = maxSubArray(matrix[0]);
        int[] tmp;
        for (int i = 1; i < matrix.length; i++) {
            // 必须以i位置为底的最大子矩阵累加和
            max = Math.max(max, maxSubArray(matrix[i]));
            tmp = matrix[i];
            for (int j = i + 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    tmp[k] += matrix[j][k];
                }
                max = Math.max(max, maxSubArray(tmp));
            }
        }
        return max;
    }

    // 求子数组的最大累加和
    public static int maxSubArray(int[] arr) {
        int pre = arr[0];
        int max = pre;
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i] + (Math.max(pre, 0));
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(maxSum(matrix));
        in.close();
    }
}
