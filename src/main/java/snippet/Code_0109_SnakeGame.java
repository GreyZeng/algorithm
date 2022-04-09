
package snippet;

import java.util.Arrays;

//给定一个二维数组matrix，每个单元都是一个整数，有正有负。最开始的时候小Q操纵 一条长度为0的蛇, 从矩阵最左侧任选一个单元格进入地图，蛇每次只能够到达当前位 置的右上相邻，
// 右侧相邻和右下相邻的单元格。 蛇到达一个单元格后，自身的长度会
//        瞬间加上该单元格的数值，任何情况下长度为负则游戏结束。 小Q是个天才，他拥有一 个超能力， 可以在游戏开始的时候把地图中的某一个节点的值变为其相反数(注:最多 只能改变一个节点).
//        问在小Q游戏过程中，他的蛇最长长度可以到多少? 比如:
//        1 -4 10 3 -2 -1 2 -1 0 0 5 -2 最优路径为从最左侧的3开始，3 -> -4(利用能力变成4) -> 10。所以返回17。
public class Code_0109_SnakeGame {
    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] ans = process(matrix, i, j);
                res = Math.max(res, Math.max(ans[0], ans[1]));
            }
        }
        return res;
    }

    public static int zuo(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info cur = f(matrix, i, j);
                ans = Math.max(ans, Math.max(cur.no, cur.yes));
            }
        }
        return ans;
    }

    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    // 蛇从某一个最左列，且最优的空降点降落
    // 沿途走到(i,j)必须停！
    // 返回，一次能力也不用，获得的最大成长值
    // 返回，用了一次能力，获得的最大成长值
    // 如果蛇从某一个最左列，且最优的空降点降落，不用能力，怎么都到不了(i,j)，那么no = -1
    // 如果蛇从某一个最左列，且最优的空降点降落，用了一次能力，怎么都到不了(i,j)，那么yes = -1
    public static Info f(int[][] matrix, int i, int j) {
        if (j == 0) { // 最左列
            int no = Math.max(matrix[i][0], -1);
            int yes = Math.max(-matrix[i][0], -1);
            return new Info(no, yes);
        }
        // j > 0 不在最左列
        int preNo = -1;
        int preYes = -1;
        Info pre = f(matrix, i, j - 1);
        preNo = Math.max(pre.no, preNo);
        preYes = Math.max(pre.yes, preYes);
        if (i > 0) {
            pre = f(matrix, i - 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        if (i < matrix.length - 1) {
            pre = f(matrix, i + 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        int no = preNo == -1 ? -1 : (Math.max(-1, preNo + matrix[i][j]));
        // 能力只有一次，是之前用的！
        int p1 = preYes == -1 ? -1 : (Math.max(-1, preYes + matrix[i][j]));
        // 能力只有一次，就当前用！
        int p2 = preNo == -1 ? -1 : (Math.max(-1, preNo - matrix[i][j]));
        int yes = Math.max(Math.max(p1, p2), -1);
        return new Info(no, yes);
    }

    // 从假想的最优左侧到达(i,j)的旅程中
    // 0) 在没有使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    // 1) 在使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    public static int[] process(int[][] m, int i, int j) {
        if (j == 0) { // (i,j)就是最左侧的位置
            return new int[]{m[i][j], -m[i][j]};
        }
        int[] preAns = process(m, i, j - 1);
        // 所有的路中，完全不使用能力的情况下，能够到达的最好长度是多大
        int preUnuse = preAns[0];
        // 所有的路中，使用过一次能力的情况下，能够到达的最好长度是多大
        int preUse = preAns[1];
        if (i - 1 >= 0) {
            preAns = process(m, i - 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        if (i + 1 < m.length) {
            preAns = process(m, i + 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        // preUnuse 之前旅程，没用过能力
        // preUse 之前旅程，已经使用过能力了
        int no = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
        int yes = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
        if (preUnuse >= 0) {
            no = m[i][j] + preUnuse;
            yes = -m[i][j] + preUnuse;
        }
        if (preUse >= 0) {
            yes = Math.max(yes, m[i][j] + preUse);
        }
        return new int[]{no, yes};
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                int preUnuse = dp[i][j - 1][0];
                int preUse = dp[i][j - 1][1];
                if (i - 1 >= 0) {
                    preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
                }
                if (i + 1 < matrix.length) {
                    preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preUnuse >= 0) {
                    dp[i][j][0] = matrix[i][j] + preUnuse;
                    dp[i][j][1] = -matrix[i][j] + preUnuse;
                }
                if (preUse >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
                }
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
            int ans1 = zuo(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }

}
