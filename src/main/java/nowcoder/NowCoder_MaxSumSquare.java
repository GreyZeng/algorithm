/*
链接：https://www.nowcoder.com/questionTerminal/cb82a97dcd0d48a7b1f4ee917e2c0409
        来源：牛客网

        给定一个矩阵matrix，其中的值有正、有负、有0，返回子矩阵的最大累加和
        例如，矩阵matrix为：
        -90 48 78
        64 -40 64
        -81 - 7 66
        其中，最大的累加和的子矩阵为：
        48 78
        -40 64
        -7 66
        所以返回累加和209。
        例如，matrix为：
        -1 -1 -1
        -1 2 2
        -1 -1 -1
        其中，最大累加和的子矩阵为：
        2 2
        所以返回4
        [要求]
        时间复杂度为O(n^2m)O(n
        2
        m)，空间复杂度为O(nm)O(nm)


        输入描述:
        第一行有两个整数N，M。分别表示矩阵的行数/列数
        接下来N行，每行M个整数表示矩阵内的数


        输出描述:
        输出一个整数表示答案
        示例1
        输入
        3 3
        -90 48 78
        64 -40 64
        -81 -7 66
        输出
        209
        示例2
        输入
        3 3
        -1 -1 -1
        -1 2 2
        -1 -1 -1
        输出
        4

        备注:
       1<=M,N<=200
       -100<=arr[i][j]<=100
*/
package nowcoder;

import java.util.Scanner;

// 启发：数组的最大累加和
public class NowCoder_MaxSumSquare {
    public static int maxSum(int[][] matrix, int n, int m) {
        int max = maxSum(matrix[0]);
        for (int i = 0; i < n; i++) {
            max = Math.max(max, maxSum(matrix[i]));
            int[] t = new int[m];
            for (int j = 0; j < m; j++) {
                t[j] = matrix[i][j];
            }
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    t[k] = t[k] + matrix[j][k];
                }
                max = Math.max(max, maxSum(t));
            }
        }
        return max;
    }

    public static int maxSum(int[] arr) {
        int pre = arr[0];
        int max = pre;
        int cur;
        for (int i = 1; i < arr.length; i++) {
            cur = arr[i] + Math.max(pre, 0);
            max = Math.max(cur, max);
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
