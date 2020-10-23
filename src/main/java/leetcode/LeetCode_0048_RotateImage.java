package leetcode;

public class LeetCode_0048_RotateImage {

    // 先处理外围的圈 然后同理依次处理每个内圈
    // 每个圈分组，组内每次一个元素占据下一个元素的位置（N*N）就分（N-1）*（N-1）组
    // 每个组的每个数可以通过组号来定位
    public static void rotate(int[][] matrix) {
        int M = matrix.length;
        int LTx = 0;
        int LTy = 0;
        int RBx = M - 1;
        int RBy = M - 1;
        while (LTx <= RBx) {
            rotate(M, matrix, LTx++, LTy++, RBx--, RBy--);
            M -= 2;
        }
    }

    private static void rotate(int M, int[][] matrix, int LTx, int LTy, int RBx, int RBy) {
        int f;
        for (int i = 0; i < M - 1; i++) {
            f = matrix[LTx][LTy + i];
            matrix[LTx][LTy + i] = matrix[RBx - i][LTy];
            matrix[RBx - i][LTy] = matrix[RBx][RBy - i];
            matrix[RBx][RBy - i] = matrix[LTx + i][RBy];
            matrix[LTx + i][RBy] = f;
        }
    }
}
