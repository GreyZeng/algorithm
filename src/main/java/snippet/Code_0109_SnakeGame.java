package snippet;


import java.util.Arrays;

//给定一个二维数组matrix，每个单元都是一个整数，有正有负。
// 最开始的时候小Q操纵一条长度为0的蛇,
// 从矩阵最左侧任选一个单元格进入地图，
// 蛇每次只能够到达当前位置的右上相邻，右侧相邻和右下相邻的单元格。
// 蛇到达一个单元格后，自身的长度会瞬间加上该单元格的数值，任何情况下长度为负则游戏结束。
// 小Q是个天才，他拥有一个超能力， 可以在游戏开始的时候把地图中的某一个节点的值变为其相反数(注:最多只能改变一个节点).
// 问在小Q游戏过程中，他的蛇最长长度可以到多少?
// 业务限制的模型。
public class Code_0109_SnakeGame {

    public static int walk1(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) {
            return 0;
        }
        int max = Math.max(m[0][0], -m[0][0]);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                Info info = process(m, i, j);
                max = Math.max(max, Math.max(info.no, info.yes));
            }
        }
        return max;
    }

    // 从最左侧任意位置到(i,j)位置可以获取的最大长度是多少
    // 有两种方法：1. 使用一次能力 2. 不使用能力
    // 如果是-1，则表示无法到达
    public static Info process(int[][] m, int i, int j) {
        // 已经到左侧了
        if (j == 0) {
            return new Info(Math.max(-1, -m[i][0]), Math.max(-1, m[i][0]));
        }
        int preYes = -1;
        int preNo = -1;
        Info pre = process(m, i, j - 1);
        preNo = Math.max(preNo, pre.no);
        preYes = Math.max(preYes, pre.yes);
        if (i - 1 >= 0) {
            pre = process(m, i - 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        if (i + 1 < m.length) {
            pre = process(m, i + 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        int no = preNo == -1 ? -1 : (Math.max(-1, preNo + m[i][j]));
        int p1 = preYes == -1 ? -1 : (Math.max(-1, preYes + m[i][j]));
        int p2 = preNo == -1 ? -1 : (Math.max(-1, preNo - m[i][j]));
        int yes = Math.max(p1, p2);
        return new Info(yes, no);
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }

    public static int walk2(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Math.max(-m[0][0], m[0][0]);
        int[][][] dp = new int[m.length][m[0].length][2];
        for (int i = 0; i < m.length; i++) {
            dp[i][0][0] = Math.max(-1, -m[i][0]);
            dp[i][0][1] = Math.max(-1, m[i][0]);
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < m[0].length; j++) {
            for (int i = 0; i < m.length; i++) {
                int preYes = -1;
                int preNo = -1;
                preNo = Math.max(preNo, dp[i][j - 1][1]);
                preYes = Math.max(preYes, dp[i][j - 1][0]);
                if (i - 1 >= 0) {
                    preNo = Math.max(dp[i - 1][j - 1][1], preNo);
                    preYes = Math.max(dp[i - 1][j - 1][0], preYes);
                }
                if (i + 1 < m.length) {
                    preNo = Math.max(dp[i + 1][j - 1][1], preNo);
                    preYes = Math.max(dp[i + 1][j - 1][0], preYes);
                }
                int no = preNo == -1 ? -1 : (Math.max(-1, preNo + m[i][j]));
                int p1 = preYes == -1 ? -1 : (Math.max(-1, preYes + m[i][j]));
                int p2 = preNo == -1 ? -1 : (Math.max(-1, preNo - m[i][j]));
                int yes = Math.max(p1, p2);
                dp[i][j][0] = yes;
                dp[i][j][1] = no;
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }

        return max;
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk1(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int[] ints : matrix) {
                    System.out.println(Arrays.toString(ints));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }

}
