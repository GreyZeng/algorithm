/*在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
        示例 1:

        输入:
        [
         [1,3,1],
         [1,5,1],
         [4,2,1]
        ]
        输出: 12
        解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
        提示：
        0 < grid.length <= 200
        0 < grid[0].length <= 200
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
package leetcode.cn;

// FIXME
public class LeetCodeCN_0047_MaxValue {
    public static int maxValue(int[][] grid) {
        if (null == grid) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        return p(grid, m, n, 0, 0);
    }

    // 从i位置开始只能向右边和下边走直到右下角能拿到的礼物最大值是多少
    public static int p(int[][] grid, int m, int n, int row, int col) {
        if (row == m - 1 && col == n - 1) {
            return grid[row][col];
        }
        // 最下面只能往右走
        if (row == m - 1) {
            return grid[row][col] + p(grid, m, n, row, col + 1);
        }
        // 最右边只能往下走
        if (col == n - 1) {
            return grid[row][col] + p(grid, m, n, row + 1, col);
        }
        // 普遍位置
        int downMax = 0;
        if (row + 1 < m) {
            downMax = p(grid, m, n, row + 1, col);
        }
        int rightMax = 0;
        if (col + 1 < n) {
            rightMax = p(grid, m, n, row, col + 1);
        }
        return Math.max(rightMax, downMax);
    }

}
