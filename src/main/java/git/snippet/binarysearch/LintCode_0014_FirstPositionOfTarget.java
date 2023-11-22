package git.snippet.binarysearch;


// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找：在一个有序数组中，找某个数是否存在,返回目标值第一次出现的下标
// https://leetcode.com/problems/binary-search/
// https://www.lintcode.com/problem/14/
public class LintCode_0014_FirstPositionOfTarget {
    public int binarySearch(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int result = -1;
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
                right = mid - 1;
            }
        }
        return result;
    }
}
