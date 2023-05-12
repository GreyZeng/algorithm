package nowcoder;

// 笔记：https://www.cnblogs.com/greyzeng/p/15371414.html
// 链接：https://www.nowcoder.com/questionTerminal/7036f62c64ba4104a28deee98a6f53f6
// 来源：牛客网
//
// 有一个整型数组A，代表数值不同的纸牌排成一条线。玩家a和玩家b依次拿走每张纸牌，规定玩家a先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家a和玩家b都绝顶聪明，他们总会采用最优策略。请返回最后获胜者的分数。
//
// 给定纸牌序列A及序列的大小n，请返回最后分数较高者得分数(相同则返回任意一个分数)。保证A中的元素均小于等于1000。且A的大小小于等于300。
//
// 测试样例：
// [1,2,100,4],4
// 返回：101
public class NowCoder_Cards {
    // 暴力递归
    public static int cardGame(int[] A, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }
        return Math.max(first(A, n, 0, A.length - 1), second(A, n, 0, A.length - 1));
    }

    // 范围上的尝试模型
    // 先手函数
    public static int first(int[] A, int n, int start, int end) {
        if (start == end) {
            return A[start];
        }
        return Math.max(A[start] + second(A, n, start + 1, end), A[end] + second(A, n, start, end - 1));
    }

    // 后手函数
    public static int second(int[] A, int n, int start, int end) {
        if (start == end) {
            return 0;
        }
        return Math.min(first(A, n, start + 1, end), first(A, n, start, end - 1));
    }

    public static int cardGame2(int[] A, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return A[0];
        }
        if (n == 2) {
            return Math.max(A[0], A[1]);
        }
        int[][] firstMap = new int[n][n];
        int[][] secondMap = new int[n][n];
        // 对角线
        for (int i = 0; i < n; i++) {
            firstMap[i][i] = A[i];
        }
        // 对角线下班区域不用管
        // 对角线上半区域
        for (int i = 1; i < n; i++) {
            int r = 0;
            int c = i;
            while (c < n) {
                firstMap[r][c] = Math.max(A[r] + secondMap[r + 1][c], A[c] + secondMap[r][c - 1]);
                secondMap[r][c] = Math.min(firstMap[r + 1][c], firstMap[r][c - 1]);
                r++;
                c++;
            }
        }
        return Math.max(firstMap[0][n - 1], secondMap[0][n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(cardGame(arr, arr.length));
        System.out.println(cardGame2(arr, arr.length));

    }
}
