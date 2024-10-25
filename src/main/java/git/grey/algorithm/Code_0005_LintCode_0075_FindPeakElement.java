package git.grey.algorithm;

// 数据量
// 1 <= nums.length <= 1000
// -2^31 <= nums[i] <= 2^31 - 1
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// https://leetcode.com/problems/find-peak-element/
// https://www.lintcode.com/problem/75/
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 时间复杂度要求 O(logN)
// 有一个限制条件：即对于所有有效的 i 都有 arr[i] != arr[i + 1]
// 二分法
// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
public class Code_0005_LintCode_0075_FindPeakElement {
    public int findPeak(int[] a) {
        if (null == a || a.length <= 2) return -1;
        // 到这，a至少包含三个元素
        if (a[0] > a[1]) return 0;
        if (a[a.length - 1] > a[a.length - 2]) return a.length - 1;
        int l = 1;
        int r = a.length - 2;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
                return mid;
            } else if (a[mid] < a[mid + 1]) {
                l = mid + 1;
            } else if (a[mid] < a[mid - 1]) {
                r = mid - 1;
            }
            // 题目保证每个位置和相邻位置的数不一样
        }
        // 如果保证有峰值，不会执行到这里
        return -1;
    }
}
