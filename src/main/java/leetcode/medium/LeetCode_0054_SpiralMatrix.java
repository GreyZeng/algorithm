package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

// https://www.lintcode.com/problem/spiral-matrix/description
// https://leetcode-cn.com/problems/spiral-matrix/
// tips：
// 先打印外围圈圈,然后切换到内圈，用同样的方式打印内圈，依次循环
// 注意最后有可能是一条直线（横线竖线另外处理）
public class LeetCode_0054_SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 左上角点
        int a = 0, b = 0;
        // 右下角点
        int c = m - 1, d = n - 1;
        List<Integer> ans = new ArrayList<>();
        while (a <= c && b <= d) {
            spiral(matrix, a++, b++, c--, d--, ans);
        }
        return ans;
    }

    public static void spiral(int[][] matrix, int a, int b, int c, int d, List<Integer> ans) {
        if (a == c) {
            // 共行
            for (int i = b; i <= d; i++) {
                ans.add(matrix[a][i]);
            }
            return;
        }
        if (b == d) {
            // 共列
            for (int i = a; i <= c; i++) {
                ans.add(matrix[i][b]);
            }
            return;
        }
        for (int i = b; i < d; i++) {
            ans.add(matrix[a][i]);
        }
        for (int i = a; i < c; i++) {
            ans.add(matrix[i][d]);
        }
        for (int i = d; i > b; i--) {
            ans.add(matrix[c][i]);
        }
        for (int i = c; i > a; i--) {
            ans.add(matrix[i][b]);
        }
    }
}
