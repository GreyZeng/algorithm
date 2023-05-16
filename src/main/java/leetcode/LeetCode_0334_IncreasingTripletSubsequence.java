package leetcode;

// https://leetcode.cn/problems/increasing-triplet-subsequence/
// 最长递增子序列是否到达了三
// 参考
// 最长递增子序列(严格递增）
// https://leetcode-cn.com/problems/longest-increasing-subsequence/
// 笔记：https://www.cnblogs.com/greyzeng/p/16151833.html
public class LeetCode_0334_IncreasingTripletSubsequence {

    public static boolean increasingTriplet(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }

        int[] ends = new int[3];
        ends[0] = arr[0];
        int l = 0;
        int r = 0;
        int right = 0;
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                int m = (r + l) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }

            }
            right = Math.max(l, right);
            if (right == 2) {
                return true;
            }
            ends[l] = arr[i];
        }
        return false;
    }

}
