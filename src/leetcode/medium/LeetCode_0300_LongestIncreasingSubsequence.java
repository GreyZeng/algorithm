package leetcode.medium;

// 最长递增子序列(严格递增）
// https://leetcode-cn.com/problems/longest-increasing-subsequence/
// 笔记：https://www.cnblogs.com/greyzeng/p/16151833.html
public class LeetCode_0300_LongestIncreasingSubsequence {
    // O(N*logN)
    // ends数组，ends[i]找到的所有长度为i+1的递增子序列中最小结尾是什么
    // dp[i]数组, 必须以i结尾的，最长递增子序列有多长
    public static int lengthOfLIS(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        dp[0] = 1;
        ends[0] = arr[0];
        int ans = 1;
        int right = 0; // ends数组目前到了哪个位置
        int l = 0;
        int r = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            // 在ends数组中找到大于等于arr[i]的最左位置
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (ends[mid] > arr[i]) {
                    r = mid - 1;
                } else if (ends[mid] < arr[i]) {
                    l = mid + 1;
                } else {
                    // ends[mid] == arr[i]
                    r = mid - 1;
                }
            }
            right = Math.max(l, right);
            dp[i] = l + 1;
            ends[l] = arr[i];
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }


    // 暴力解(O(N^2))
    public static int lengthOfLIS2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int max = 1;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], arr[j] < arr[i] ? dp[j] + 1 : 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // 求必须以i位置结尾的最长递增子序列的长度
    // O(N*logN)解法
    public static int[] lis(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int N = arr.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = arr[0];
        int l = 0;
        int r = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            dp[i] = l + 1;
            ends[l] = arr[i];
        }


        int maxIndex = 0;
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > maxLen) {
                maxIndex = i;
                maxLen = dp[i];
            }
        }

        int[] res = new int[maxLen];
        res[--maxLen] = arr[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (arr[i] < arr[maxIndex] && dp[i] == dp[maxIndex] - 1) {
                res[--maxLen] = arr[i];
                maxIndex = i;
            }
        }
        return res;
    }

    // 求必须以i位置结尾的最长递增子序列的长度
    // 暴力解(O(N^2))
    public static int[] lis2(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        int N = arr.length;
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < N; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxIndex = 0;
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] > maxLen) {
                maxIndex = i;
                maxLen = dp[i];
            }
        }

        int[] res = new int[maxLen];
        res[--maxLen] = arr[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (arr[i] < arr[maxIndex] && dp[i] == dp[maxIndex] - 1) {
                res[--maxLen] = arr[i];
                maxIndex = i;
            }
        }

        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        printArray(arr);
        printArray(lis(arr));
        printArray(lis2(arr));

    }
}
