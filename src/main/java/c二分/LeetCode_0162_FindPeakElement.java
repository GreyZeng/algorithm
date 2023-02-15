package c二分;

// 数据量
// 1 <= nums.length <= 1000
// -2^31 <= nums[i] <= 2^31 - 1
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// https://leetcode.com/problems/find-peak-element/
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 时间复杂度要求 O(logN)
// 有一个限制条件：即对于所有有效的 i 都有 arr[i] != arr[i + 1]
// 二分法
// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
public class LeetCode_0162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] > nums[left + 1]) {
            return left;
        }
        if (nums[right] > nums[right - 1]) {
            return right;
        }
        left = left + 1;
        right = right - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
