package c二分;

// 二分查找
// 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
// 如果数组中不存在目标值 target，返回[-1, -1]。
// 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
// https://www.cnblogs.com/greyzeng/p/16622554.html
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {

    // O(logN)
    public int[] searchRange(int[] nums, int target) {
        if (null == nums || 0 == nums.length) {
            return new int[]{-1, -1};
        }
        return new int[]{left(nums, target), right(nums, target)};
    }

    public int left(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public int right(int[] nums, int target) {
        int ans = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                ans = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
