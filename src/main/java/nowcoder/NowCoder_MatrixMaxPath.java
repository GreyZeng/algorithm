/*链接：https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
        来源：牛客网

        给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。然后从右下角出发，每一步只能往上或者往左走，再回到左上角。任何一个位置的数字，只能获得一遍。返回最大路径和。


        输入描述:
        第一行输入两个整数M和N，M,N<=200
        接下来M行，每行N个整数，表示矩阵中元素


        输出描述:
        输出一个整数，表示最大路径和
        示例1
        输入
        5 10
        1 1 1 1 1 0 0 0 0 0
        0 0 0 0 1 0 0 0 0 0
        0 0 0 0 1 0 0 0 0 1
        1 0 0 0 1 0 0 0 0 0
        0 0 0 0 1 1 1 1 1 1
        输出
        16
        示例2
        输入
        5 10
        1 1 1 1 1 0 0 0 0 0
        0 0 0 0 1 0 0 0 0 0
        0 0 0 0 1 0 0 0 0 1
        1 0 0 0 1 0 0 0 1 0
        0 0 0 0 1 1 1 1 0 1
        输出
        15*/
package nowcoder;

import java.util.Scanner;

/**
 * @author Young
 * @version 1.0
 * @date 2021/2/6 22:35
 */
public class NowCoder_MatrixMaxPath {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(max(matrix));
        in.close();
    }

    public static int max(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][][] dp = new int[m][n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return p(matrix, m, n, 0, 0, 0, dp);
    }

    // 第一个点：(x1,y1), 第二个点(x2,y2)
    // 重要限制：来到同一个位置时，只获得一份
    public static int p(int[][] matrix, int m, int n, int x1, int y1, int x2, int[][][] dp) {
        if (dp[x1][y1][x2] != -1) {
            return dp[x1][y1][x2];
        }
        if (x1 == m - 1 && y1 == n - 1) {
            dp[m - 1][n - 1][x2] = matrix[x1][y1];
            return dp[m - 1][n - 1][x2];
        }
        int y2 = getY2(m, n, x1, y1, x2);
        int next = -1;
        // 下右
        if (x1 < m - 1 && y2 < n - 1) {
            next = Math.max(next, p(matrix, m, n, x1 + 1, y1, x2, dp));
        }
        // 右下
        if (y1 < n - 1 && x2 < m - 1) {
            next = Math.max(next, p(matrix, m, n, x1, y1 + 1, x2 + 1, dp));
        }
        // 右右
        if (y1 < n - 1 && y2 < n - 1) {
            next = Math.max(next, p(matrix, m, n, x1, y1 + 1, x2, dp));
        }
        // 下下
        if (x1 < m - 1 && x2 < m - 1) {
            next = Math.max(next, p(matrix, m, n, x1 + 1, y1, x2 + 1, dp));
        }
        if (matrix[x1][y1] == -1 || matrix[x2][y2] == -1 || next == -1) {
            dp[x1][y1][x2] = -1;
            return dp[x1][y1][x2];
        }
        if (x1 == x2) {
            dp[x1][y1][x2] = matrix[x1][y1] + next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = matrix[x1][y1] + matrix[x2][y2] + next;
        return dp[x1][y1][x2];
    }

    private static int getY2(int m, int n, int x1, int y1, int x2) {
        return x1 + y1 - x2;
    }
}
