package 算法.二分;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找：在一个有序数组中，找某个数是否存在
// https://leetcode.cn/problems/binary-search/
public class LeetCode_0704_BinarySearch {

    public static int search(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                // arr[m] == target
                return m;
            }
        }
        return -1;
    }
}
