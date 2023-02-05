package 算法.二分;

// 二分查找
// 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
// 如果数组中不存在目标值 target，返回[-1, -1]。
// 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
// https://www.cnblogs.com/greyzeng/p/16622554.html
// https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {

    // O(logN)
    public static int[] searchRange(int[] arr, int t) {
        if (arr == null || arr.length < 1) {
            return new int[]{-1, -1};
        }
        return new int[]{left(arr, t), right(arr, t)};
    }

    public static int left(int[] arr, int t) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int ans = -1;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == t) {
                ans = m;
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            } else {
                // arr[m] > t
                r = m - 1;
            }
        }
        return ans;
    }

    public static int right(int[] arr, int t) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int ans = -1;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == t) {
                ans = m;
                l = m + 1;
            } else if (arr[m] < t) {
                l = m + 1;
            } else {
                // arr[m] > t
                r = m - 1;
            }
        }
        return ans;
    }
}
