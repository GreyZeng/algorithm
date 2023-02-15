package c二分;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找：在一个有序数组中，找某个数是否存在
// https://leetcode.cn/problems/binary-search/
public class LeetCode_0704_BinarySearch {

    public int search(int[] nums, int target) {
        if (null == nums || 0 == nums.length) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
