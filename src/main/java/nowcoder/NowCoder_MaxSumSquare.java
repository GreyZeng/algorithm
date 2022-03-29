package nowcoder;

import java.util.Scanner;

// 子矩阵的最大累加和
// 测评链接：https://www.nowcoder.com/questionTerminal/cb82a97dcd0d48a7b1f4ee917e2c0409
// 压缩数组技巧 O(N^2 * M) --> 考虑一下N和M的长度
// 启发：数组的最大累加和: https://leetcode-cn.com/problems/maximum-subarray/
public class NowCoder_MaxSumSquare {
    public static int maxSum(int[][] matrix, int n, int m) {
        int[] t;
        int max = maxSubArray(matrix[0]);
        for (int i = 0; i < n; i++) {
            max = Math.max(maxSubArray(matrix[i]), max);
            t = matrix[i];
            for (int k = i + 1; k < n; k++) {
                for (int j = 0; j < m; j++) {
                    t[j] += matrix[k][j];
                }
                max = Math.max(max, maxSubArray(t));
            }
        }
        return max;
    }

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
        System.out.println(maxSum(matrix, n, m));
        in.close();
    }

}
