package resolved.binarysearch;

// 二分查找
// 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
// 如果数组中不存在目标值 target，返回[-1, -1]。
// 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
// https://www.cnblogs.com/greyzeng/p/16622554.html
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {
  public int[] searchRange(int[] nums, int target) {
    return new int[] {left(nums, target), right(nums, target)};
  }

  private int left(int[] nums, int target) {
    if (nums == null || nums.length < 1) {
      return -1;
    }
    int l = 0;
    int r = nums.length - 1;
    int ans = -1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (nums[m] == target) {
        ans = m;
        r = m - 1;
      } else if (nums[m] > target) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return ans;
  }

  private int right(int[] nums, int target) {
    if (nums == null || nums.length < 1) {
      return -1;
    }
    int l = 0;
    int r = nums.length - 1;
    int ans = -1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (nums[m] == target) {
        ans = m;
        l = m + 1;
      } else if (nums[m] < target) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return ans;
  }
}
