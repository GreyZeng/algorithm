package leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;

//嵌套信封问题
// 原型就是最长递增子序列
//一维从小到大，二维从大到小，二维数据拿出来，最长递增子序列就是嵌套层数
// 最长递增子序列
// https://leetcode-cn.com/problems/russian-doll-envelopes/
// 笔记：https://www.cnblogs.com/greyzeng/p/16151833.html
public class LeetCode_0354_RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (null == envelopes || 0 == envelopes.length || 0 == envelopes[0].length) {
            return 0;
        }
        int N = envelopes.length;
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        int[] res = new int[N];
        int i = 0;
        for (int[] e : envelopes) {
            res[i++] = e[1];
        }
        return lengthOfLIS(res);
    }

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
}
