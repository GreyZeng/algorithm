
package leetcode;

//给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
//        例如:
//        01111
//        01001
//        01001
//        01111
//        01011
//        其中边框全是1的最大正方形的大小为4*4，所以返回4
//        tips:
//        N*N 正方形
//        有N^4长方形 随便点一个点O(N^2), 随便点另外一个点O(N^2) 所以是O(N^4)
//        有N^3正方形 随便点一个点O(N^2), 边长的范围[0,N]，所以是O(N^3)
//        长方形需要两个点确定
//        正方形一个点+边长确定
// 准备两个矩阵：r矩阵和d矩阵，其中
//        r[i][j] 右侧有多少个连续的1
//        d[i][j] 下方有多少个连续的1
//https://leetcode.com/problems/largest-1-bordered-square/
public class LeetCode_1139_Largest1BorderedSquare {
    public static int largest1BorderedSquare(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        // i位置包括自己右边有几个连续的1
        int[][] rightOneTimes = new int[M][N];
        // i位置包括自己下面有几个连续的1
        int[][] downOneTimes = new int[M][N];
        int max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    rightOneTimes[i][j] = 0;
                } else {
                    rightOneTimes[i][j] = 1;
                    if (j != N - 1) {
                        rightOneTimes[i][j] = rightOneTimes[i][j] + rightOneTimes[i][j + 1];
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (grid[j][i] == 0) {
                    downOneTimes[j][i] = 0;
                } else {
                    downOneTimes[j][i] = 1;
                    if (j != M - 1) {
                        downOneTimes[j][i] = downOneTimes[j][i] + downOneTimes[j + 1][i];
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (rightOneTimes[i][j] != 0 && downOneTimes[i][j] != 0) {
                    int len = Math.min(rightOneTimes[i][j], downOneTimes[i][j]);
                    while (len > 0) {
                        int rightNextX = i;
                        int rightNextY = j + len - 1;
                        int downNextX = i + len - 1;
                        int downNextY = j;
                        if (downOneTimes[rightNextX][rightNextY] >= len && rightOneTimes[downNextX][downNextY] >= len) {
                            max = Math.max(len, max);
                            break;
                        }
                        len--;
                    }
                }
            }
        }

        return max * max;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 0, 1, 1, 1}, {0, 1, 1, 1, 1, 0, 1, 1}};
        System.out.println(largest1BorderedSquare(grid));
    }

}
