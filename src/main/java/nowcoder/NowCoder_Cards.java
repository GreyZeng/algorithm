package nowcoder;

//链接：https://www.nowcoder.com/questionTerminal/7036f62c64ba4104a28deee98a6f53f6
//        来源：牛客网
//
//        有一个整型数组A，代表数值不同的纸牌排成一条线。玩家a和玩家b依次拿走每张纸牌，规定玩家a先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家a和玩家b都绝顶聪明，他们总会采用最优策略。请返回最后获胜者的分数。
//
//        给定纸牌序列A及序列的大小n，请返回最后分数较高者得分数(相同则返回任意一个分数)。保证A中的元素均小于等于1000。且A的大小小于等于300。
//
//        测试样例：
//        [1,2,100,4],4
//        返回：101
public class NowCoder_Cards {
    // 暴力递归
    public int cardGame(int[] A, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }
        return Math.max(f(A, n, 0, n - 1), s(A, n, 0, n - 1));
    }

    // 范围上的尝试
    // 先手函数
    public int f(int[] A, int n, int L, int R) {
        if (L == R) {
            // 只有一个数
            return A[R];
        }
        return Math.max(A[L] + s(A, n, L + 1, R), A[R] + s(A, n, L, R - 1));
    }

    // 后手函数
    public int s(int[] A, int n, int L, int R) {
        if (L == R) {
            // 只有一个数
            return 0;
        }
        return Math.min(f(A, n, L + 1, R), f(A, n, L, R - 1));
    }

    // 动态规划优化版本
    public int cardGame2(int[] A, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = A[i];
        }
        // i表示列
        for (int i = 1; i < n; i++) {
            int r = 0;
            int c = i;
            while (r < n - i || c < n) {
                s[r][c] = Math.min(f[r + 1][c], f[r][c - 1]);
                f[r][c] = Math.max(A[r] + s[r + 1][c], A[c] + s[r][c - 1]);
                r++;
                c++;
            }
        }
        return Math.max(f[0][n - 1], s[0][n - 1]);
    }
}
