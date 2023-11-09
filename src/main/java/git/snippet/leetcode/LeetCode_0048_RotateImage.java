package git.snippet.leetcode;

// 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
// 先处理外围的圈 然后同理依次处理每个内圈
// 每个圈分组，组内每次一个元素占据下一个元素的位置（N*N）就分（N-1）*（N-1）组
// 每个组的每个数可以通过组号来定位
// https://leetcode-cn.com/problems/rotate-image/
// https://www.lintcode.com/problem/rotate-image/description
public class LeetCode_0048_RotateImage {
    // matrix需要保证是正方形
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int zuoshangX = 0;
        int zuoshangY = 0;
        int youxiaX = n - 1;
        int youxiaY = n - 1;
        while (n > 0) {
            rotate(n, matrix, zuoshangX++, zuoshangY++, youxiaX--, youxiaY--);
            n -= 2;
        }
    }

    private static void rotate(
            int n, int[][] matrix, int zuoshangX, int zuoshangY, int youxiaX, int youxiaY) {
        int zu = n - 1;
        int youshangX = zuoshangX;
        int youshangY = youxiaY;
        int zuoxiaX = youxiaX;
        int zuoxiaY = zuoshangY;
        for (int i = 1; i <= zu; i++) {
            // 每组内部调整
            int tmp = matrix[zuoshangX][zuoshangY];
            matrix[zuoshangX][zuoshangY++] = matrix[zuoxiaX][zuoxiaY];
            matrix[zuoxiaX--][zuoxiaY] = matrix[youxiaX][youxiaY];
            matrix[youxiaX][youxiaY--] = matrix[youshangX][youshangY];
            matrix[youshangX++][youshangY] = tmp;
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(m);
        System.out.println("--");
    }
}
