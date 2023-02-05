package 练习题.leetcode.hard;

// TODO
// 给定数组 nums 由正整数组成，找到三个互不重叠的子数组的最大和。每个子数组的长度为k，我们要使这3*k个项的和最大化。返回每个区间起始索引的列表（索引从 0
// 开始）。如果有多个结果，返回字典序最小的一个。
// https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
// 子数组的累加和，使用dp 0…i随意选，得到的最大值是多少。
// 从右往左，从左往右
public class LeetCode_0689_MaximumSumOf3NonOverlappingSubarrays {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] range = new int[N];
        int[] left = new int[N];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;
        left[k - 1] = 0;
        int max = sum;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i];
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        int[] right = new int[N];
        right[N - k] = N - k;
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
        }
        int a = 0;
        int b = 0;
        int c = 0;
        max = 0;
        for (int i = k; i < N - 2 * k + 1; i++) { // 中间一块的起始点 (0...k-1)选不了 i == N-1
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[]{a, b, c};
    }

}
