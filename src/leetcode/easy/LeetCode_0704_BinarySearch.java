package leetcode.easy;

// 二分查找：在一个有序数组中，找某个数是否存在
// https://leetcode.cn/problems/binary-search/
public class LeetCode_0704_BinarySearch {
    public int search(int[] arr, int t) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == t) {
                return m;
            } else if (arr[m] > t) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
