package binarysearch;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找：在一个有序数组中，找某个数是否存在,返回目标值所在的位置
// https://leetcode.com/problems/binary-search/
public class LeetCode_0704_BinarySearch {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
