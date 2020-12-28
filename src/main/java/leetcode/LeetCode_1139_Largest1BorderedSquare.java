// Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.


// Example 1:

// Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
// Output: 9
// Example 2:

// Input: grid = [[1,1,0,0]]
// Output: 1


// Constraints:

// 1 <= grid.length <= 100
// 1 <= grid[0].length <= 100
// grid[i][j] is 0 or 1
package leetcode;

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
