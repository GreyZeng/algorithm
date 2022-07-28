package leetcode.medium;

// 二分查找
// 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
// 如果数组中不存在目标值 target，返回[-1, -1]。
// 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
// https://www.cnblogs.com/greyzeng/p/15690136.html
// https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {

    // O(logN)
    public static int[] searchRange(int[] arr, int t) {
        if (arr == null || arr.length < 1) {
            return new int[]{-1, -1};
        }
        return new int[]{findLeft(arr, t), findRight(arr, t)};
    }

    // 二分找到某个值以后，继续往左边找
    public static int findLeft(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > t) {
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    // 二分找到某个值以后，继续往右边找
    public static int findRight(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > t) {
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            } else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }
}
