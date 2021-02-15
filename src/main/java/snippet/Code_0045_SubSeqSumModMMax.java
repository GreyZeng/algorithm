package snippet;

import java.util.HashSet;
import java.util.TreeSet;

//给定一个非负数组arr，和一个正数m
//        返回arr的所有子序列中累加和%m之后的最大值。
//        tips:
//        方法1：
//        二维表(n*sum)
//        方法2：
//        二维表（n*m）：列是数组下标，行0...m-1
//        0到i上随意组合，在i%m后能否组成j
//        方法3（分治）：
//        二维表(n*m（真实的余数）):如果n很小，可以分解两半，暴力求每一半的所有可能子数组的累加和，放入一个List中，List加工出每个值模m后的List‘
//        左边部分的数量 + 右边部分的数量 + 左边和右边整合的List
public class Code_0045_SubSeqSumModMMax {
    // 暴力解法
    // 穷举所有子序列的和然后%m取最大值
    public static int max(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        int i = 0;
        int sum = 0;
        process(arr, i, sum, set);
        int max = 0;
        for (int s : set) {
            max = Math.max(max, s % m);
        }
        return max;
    }

    // 0...i-1任意组合，sum收集子序列的累加和
    private static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
        } else {
            process(arr, i + 1, sum + arr[i], set);
            process(arr, i + 1, sum, set);
        }
    }

    // 适用于sum比较小的时候
    // O(n*sum)
    // dp[i][j] 0...i号位置任意选择，是否可以恰好可以拼成j这个累加和
    public static int max2(int[] arr, int m) {
        int N = arr.length;
        int sum = arr[0];
        for (int i = 1; i < N; i++) {
            sum += arr[i];
        }
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            // 什么都不选，累加和就是0，所以dp[...][0] = true;
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                // 不用i位置的的值
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    // 说明有可能在dp[i - 1][j - arr[i]] 中有记录
                    dp[i][j] = dp[i][j] | dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < sum + 1; i++) {
            if (dp[N - 1][i]) {
                ans = Math.max(ans, i % m);
            }
        }
        return ans;
    }

    // 适用于m比较小的时候
    // O(n*m)
    // dp[i][j] 0...i随意选择，是否可以得到子序列累加和恰好%m等于j
    public static int max3(int[] arr, int m) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] % m >= 0) {
                    dp[i][j] = dp[i][j] | dp[i - 1][j - arr[i] % m];
                } else {
                    dp[i][j] = dp[i][j] | dp[i - 1][m + j - arr[i] % m];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[n - 1][i]) {
                ans = Math.max(ans, i);
            }
        }
        return ans;
    }

    // m和sum都比较大的时候，但是arr的长度很短
    // O(2^{N/2})
    // 如果 arr1里能搞定余数0, 就看arr2里最接近99的数
    // 如果 arr1里能搞定余数1, 就看arr2里最接近98的数
    // 如果 arr1里能搞定余数2, 就看arr2里最接近97的数
    // 如果 arr1里能搞定余数3, 就看arr2里最接近96的数
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process4(arr, 0, 0, mid, m, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        int ans = 0;
        for (Integer leftMod : sortSet1) {
            ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }

    // 从index出发，最后有边界是end+1，arr[index...end]
    public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            sortSet.add(sum % m);
        } else {
            process4(arr, index + 1, sum, end, m, sortSet);
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }
}
