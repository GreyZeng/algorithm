package c二分;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置，如果找不到，则返回数组长度
// https://leetcode.cn/problems/search-insert-position/
public class LeetCode_0035_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int ans = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
