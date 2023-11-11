package git.snippet.binarysearch;

// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置，如果找不到，则返回数组长度
// https://leetcode.com/problems/search-insert-position/
public class LeetCode_0035_SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int ans = nums.length;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                ans = m;
                r = m - 1;
            } else if (nums[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                // nums[m] < target
                l = m + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        System.out.println(searchInsert(nums, 5));
        System.out.println(searchInsert(nums, 2));
        System.out.println(searchInsert(nums, 7));
    }
}
