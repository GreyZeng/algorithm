package binarysearch;

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
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int l = 1;
        int r = nums.length - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] > nums[m - 1] && nums[m] > nums[m + 1]) {
                return m;
            } else if (nums[m] <= nums[m - 1]) {
                r = m - 1;
            } else if (nums[m] <= nums[m + 1]) {
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(new LeetCode_0162_FindPeakElement().findPeakElement(arr));
        int[] arr2 = {1, 2, 3, 1};
        System.out.println(new LeetCode_0162_FindPeakElement().findPeakElement(arr2));
    }
}