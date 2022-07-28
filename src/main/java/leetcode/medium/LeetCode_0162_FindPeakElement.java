package leetcode.medium;

// 数据量
//1 <= nums.length <= 1000
// -2^31 <= nums[i] <= 2^31 - 1
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// https://leetcode-cn.com/problems/find-peak-element/
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 即对于所有有效的 i 都有 arr[i] != arr[i + 1]
// 二分法
// 笔记：https://www.cnblogs.com/greyzeng/p/15690136.html
public class LeetCode_0162_FindPeakElement {
    public static int findPeakElement(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] > arr[1] ? 0 : 1;
        }
        int l = 0;
        int r = arr.length - 1;
        if (arr[l] > arr[l + 1]) {
            return l;
        }
        if (arr[r] > arr[r - 1]) {
            return r;
        }
        l = 1;
        r = arr.length - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > arr[m + 1] && arr[m] > arr[m - 1]) {
                return m;
            }
            if (arr[m] > arr[m + 1]) {
                // arr[m] < arr[m-1]
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
