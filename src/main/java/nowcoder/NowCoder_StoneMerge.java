package nowcoder;

// 四边形不等式：合并石子问题
// https://ac.nowcoder.com/acm/problem/51170
// 摆放着n堆石子。现要将石子有次序地合并成一堆
//规定每次只能选相邻的2堆石子合并成新的一堆，
//并将新的一堆石子数记为该次合并的得分
//求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
//eg: [1,4,2,3]
//
//        arr[L...R]怎么合并最优 范围上的尝试
//        dp N*N dp[i][j] --> 最优代价
//
//        Code_0071_StoneMerge

import java.util.Scanner;

public class NowCoder_StoneMerge {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        int minCost = min2(arr);
        System.out.println(minCost);
        in.close();
    }

    public static int min1(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int n = arr.length;
        int[] sum = sum(arr);
        return p1(arr, 0, n - 1, sum);
    }

    // L...R范围内，最小代价
    public static int p1(int[] arr, int L, int R, int[] sum) {
        if (L == R) {
            return 0;
        }
        if (L == R - 1) {
            return arr[L] + arr[R];
        }
        // 最后一块和谁合并
        // 前面L...R-1先合并，后面和arr[R]合并
        int minCost = arr[L] + p1(arr, L + 1, R, sum) + rangeSum(sum, L + 1, R);
        int start = L + 1;
        while (start < R) {
            minCost = Math.min(minCost, p1(arr, L, start, sum) + rangeSum(sum, L, start) + ((start + 1 == R) ? arr[R] : rangeSum(sum, start + 1, R) + p1(arr, start + 1, R, sum)));
            start++;
        }
        return minCost;
    }


    public static int[] sum(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int rangeSum(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int[] sum = sum(arr);
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = arr[i] + arr[i + 1];
        }
        for (int i = n - 3; i >= 0; i--) {
            int j = n - 1;
            int k = i;
            while (j >= n - i - 1) {
                dp[k][j] = dp[k + 1][j] + arr[k] + rangeSum(sum, k + 1, j);
                int start = k + 1;
                while (start < j) {
                    dp[k][j] = Math.min(dp[k][j], dp[k][start] + rangeSum(sum, k, start) + ((start + 1 == j) ? arr[j] : rangeSum(sum, start + 1, j) + dp[start + 1][j]));
                    start++;
                }
                k--;
                j--;
            }
        }
        return dp[0][n - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N]; // 最优划分
        int[][] best = new int[N][N]; // 最优划分点 对角线没有划分点，可以忽略
        // 以下循环填倒数第二条对角线的值
        for (int i = 0; i < N - 1; i++) {
            best[i][i + 1] = i;
            dp[i][i + 1] = rangeSum(s, i, i + 1);
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                best[L][R] = choose;
                dp[L][R] = next + rangeSum(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

//    public static void main(String[] args) {
//        int N = 15;
//        int maxValue = 100;
//        int testTime = 1000;
//        System.out.println("测试开始");
//        int[] arr = null;
//        int ans1 = 0;
//        int ans2 = 0;
//        int ans3 = 0;
//
//        for (int i = 0; i < testTime; i++) {
//            int len = (int) (Math.random() * N);
//            arr = randomArray(len, maxValue);
//            ans1 = min1(arr);
//            ans2 = min2(arr);
//            ans3 = min3(arr);
//            if (ans1 != ans2 || ans1 != ans3) {
//                System.out.println("Oops!");
//                break;
//            }
//        }
////        for (int m : arr) {
////            System.out.print(" " + m);
////        }
////        System.out.println();
////        System.out.println(ans1);
////        System.out.println(ans2);
////        System.out.println(ans3);
//        System.out.println("测试结束");
//    }

}
