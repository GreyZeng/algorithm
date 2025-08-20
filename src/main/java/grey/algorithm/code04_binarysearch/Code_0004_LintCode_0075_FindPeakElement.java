package grey.algorithm.code04_binarysearch;

// 数据量
// 1 <= nums.length <= 1000
// -2^31 <= nums[i] <= 2^31 - 1
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// 数组中至少包括三个数
// https://leetcode.com/problems/find-peak-element/
// https://www.lintcode.com/problem/75/
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 时间复杂度要求 O(logN)
// 有一个限制条件：即对于所有有效的 i 都有 arr[i] != arr[i + 1]
// 二分法
// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
public class Code_0004_LintCode_0075_FindPeakElement {

    public static int findPeak(int[] a) {
        if (null == a || a.length == 0) {
            return -1;
        }
        if (a.length == 1) {
            return 0;
        }
        if (a.length == 2) {
            return a[0] > a[1] ? 0 : 1;
        }
        // leetcode中数组至少有一个元素
        // lintcode中约束了数组中至少包括3个数
        if (a[0] > a[1]) {
            return 0;
        }
        if (a[a.length - 1] > a[a.length - 2]) {
            return a.length - 1;
        }
        // 两个端点都不是峰值
        int l = 1;
        int r = a.length - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (a[m] > a[m - 1] && a[m] > a[m + 1]) {
                return m;
            } else if (a[m] < a[m - 1]) {
                r = m - 1;
            } else {
                // a[m] < a[m + 1]
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 5, 7, 6};
        int p = findPeak(arr);
        System.out.println(p);
    }
}
