package git.snippet.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// 笔记：https://www.cnblogs.com/greyzeng/p/16389645.html
public class NowCoder_FirstUnFormedNum {
    // 最小不可组成和
    // https://www.nowcoder.com/questionTerminal/296c2c18037843a7b719cf4c9c0144e4
    public static int getFirstUnFormedNum(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max += arr[i];
            min = Math.min(min, arr[i]);
        }
        // 可以到的范围是[min,max]
        // dp[i][j] 0....i能否组成j
        boolean[][] dp = new boolean[arr.length][max + 1];
        // 第0行 除了下述位置，其他位置都是false
        dp[0][arr[0]] = true;
        for (int i = 0; i < dp.length; i++) {
            // 0..i上一个元素都不用，可以组成0这个累加和
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < max + 1; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        for (int i = min; i <= max; i++) {
            if (!dp[arr.length - 1][i]) {
                return i;
            }
        }
        return max + 1;
    }

    // 最小不可组成和-进阶，如果数组中肯定存在1这个值
    // https://www.nowcoder.com/questionTerminal/a689a05f75ff4caaa129b1f971aeb71e
    public static long unformedSum(long[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        long range = 1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nextInt = in.nextInt();
        long[] arr = new long[nextInt];
        for (int i = 0; i < nextInt; i++) {
            arr[i] = in.nextLong();
        }
        System.out.println(unformedSum(arr));
        in.close();
    }
}
