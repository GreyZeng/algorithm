package leetcode.medium;
// https://www.cnblogs.com/greyzeng/p/15690136.html
// https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class LeetCode_0034_FindFirstAndLastPositionOfElementInSortedArray {
   
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }
        return new int[]{findLeft(nums, target), findRight(nums, target)};
    }

    public static int findLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    public static int findRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            } else {
                ans = mid;
                l = mid + 1;
            }
        }
        return ans;
    }
}
