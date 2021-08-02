// Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.

// Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.

// Example 1:

// Input: nums = [-2,5,-1], lower = -2, upper = 2
// Output: 3
// Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
// Example 2:

// Input: nums = [0], lower = 0, upper = 0
// Output: 1

// Constraints:

// 1 <= nums.length <= 10^5
// -2^31 <= nums[i] <= 2^31 - 1
// -10^5 <= lower <= upper <= 10^5
// The answer is guaranteed to fit in a 32-bit integer.
package leetcode;
// 方法1：归并排序

// 1. 前缀和加速求区间和
// 2. 必须以i结尾的达标子数组有多少个

// 方法2：有序表方式 [TODO]
public class LeetCode_0327_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int size = nums.length;
        long[] sum = new long[size];
        sum[0] = nums[0];
        for (int i = 1; i < size; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        return p(sum, 0, size - 1, lower, upper);
    }

    public static int p(long[] sum, int i, int j, int lower, int upper) {
        if (i == j) {
            if (sum[i] >= lower && sum[j] <= upper) {
                return 1;
            }
            return 0;
        }
        int mid = i + ((j - i) >> 1);
        return p(sum, i, mid, lower, upper) + p(sum, mid + 1, j, lower, upper) + merge(sum, i, mid, j, lower, upper);
    }

    private static int merge(long[] sum, int i, int mid, int j, int lower, int upper) {
        // 单调性->滑动窗口
        int pair = 0;
        int L = i;
        int R = i;
        int S = mid + 1;
        while (S <= j) {
            long max = sum[S] - lower;
            long min = sum[S] - upper;
            while (L <= mid && sum[L] < min) {
                L++;
            }
            while (R <= mid && sum[R] <= max) {
                R++;
            }
            pair+=(R - L);
            S++;
        }

        // mergeSort经典代码
        long[] helper = new long[j - i + 1];
        int l = i;
        int r = mid+1;
        int index = 0;
        while (l <= mid && r <= j) {
            if (sum[l] > sum[r]) {
                helper[index++] = sum[r++];
            } else {
                helper[index++] = sum[l++];
            }
        }
        while (l <= mid) {
            helper[index++] = sum[l++];
        }
        while (r <= j) {
            helper[index++] = sum[r++];
        }
        int k = 0;
        for (long num : helper) {
            sum[i + (k++)] = num;
        }
        return pair;
    }
}
