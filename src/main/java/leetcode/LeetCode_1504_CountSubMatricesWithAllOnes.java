package leetcode;

// tips:
// 必须以每一行做底的全为 1 的子矩阵是多少，然后求和即可
// N 为长的矩形一共包含的子矩阵有`(N*(N+1)) / 2`

// https://leetcode-cn.com/problems/count-submatrices-with-all-ones/
// i位置左右两边离他最近的比他小的位置是b,a，b位置上的数是Y，C位置上的数是Z
// 则i位置弹出的时候，一共要算：（X - max(Y,Z)) * ((L + 1) * L / 2)
// 笔记：https://www.cnblogs.com/greyzeng/p/16326526.html
public class LeetCode_1504_CountSubMatricesWithAllOnes {

    public int numSubmat(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] help = new int[matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    help[j] = matrix[0][j] == 1 ? 1 : 0;
                } else {
                    help[j] += matrix[i][j] == 1 ? 1 : (-help[j]);
                }
            }
            count += max(help);
        }
        return count;
    }

    public static int max(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int nums = 0;
        // 用固定数组来替代Java自带的栈结果
        int[] stack = new int[height.length];
        int si = -1;
        for (int i = 0; i < height.length; i++) {
            // si = -1 说明栈为空
            // 栈顶：height[stack[si]]
            while (si != -1 && height[stack[si]] >= height[i]) {
                int cur = stack[si--];
                if (height[cur] > height[i]) {
                    int left = si == -1 ? -1 : stack[si];
                    int n = i - left - 1;
                    int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                    nums += (height[cur] - down) * num(n);
                }
            }
            // 入栈
            stack[++si] = i;
        }
        while (si != -1) {
            int cur = stack[si--];
            int left = si == -1 ? -1 : stack[si];
            int n = height.length - left - 1;
            int down = left == -1 ? 0 : height[left];
            nums += (height[cur] - down) * num(n);
        }
        return nums;
    }

    public static int num(int n) {
        return ((n * (1 + n)) >> 1);
    }

}
