package leetcode.medium;

// 给定一个只有0和1组成的二维数组，返回边框全是1（内部无所谓）的最大正方形面积
// 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
// 例如:
// 01111
// 01001
// 01001
// 01111
// 01011
// 其中边框全是1的最大正方形的大小为4*4，所以返回4
// tips:
// N*N 正方形
// 有N^4长方形 随便点一个点O(N^2), 随便点另外一个点O(N^2) 所以是O(N^4)
// 有N^3正方形 随便点一个点O(N^2), 边长的范围[0,N]，所以是O(N^3)
// 长方形需要两个点确定
// 正方形一个点+边长确定
// 准备两个矩阵：r矩阵和d矩阵，其中
// r[i][j] 右侧有多少个连续的1
// d[i][j] 下方有多少个连续的1
// https://leetcode.cn/problems/largest-1-bordered-square/
public class LeetCode_1139_Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }
}
