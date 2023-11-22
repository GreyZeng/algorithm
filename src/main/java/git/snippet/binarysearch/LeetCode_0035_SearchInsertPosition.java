package git.snippet.binarysearch;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置，如果找不到，则返回数组长度
// https://leetcode.com/problems/search-insert-position/
public class LeetCode_0035_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int result = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }
}
