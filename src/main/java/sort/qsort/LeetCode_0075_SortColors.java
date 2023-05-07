package sort.qsort;

// 笔记：https://www.cnblogs.com/greyzeng/p/16739515.html
// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
// https://leetcode.com/problems/sort-colors/
public class LeetCode_0075_SortColors {
    public void sortColors(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int l = -1;
        int r = nums.length;
        int i = 0;
        while (i < r) {
            int p = nums[i];
            if (p == 0) {
                // 小于区域
                swap(nums, i++, ++l);
            }
            if (p == 1) {
                // 等于区域，直接i+1
                i++;
            }
            if (p == 2) {
                // 大于区域
                swap(nums, i, --r);
            }
        }
    }

    public void swap(int[] nums, int l, int r) {
        if (l != r) {
            nums[l] = nums[l] ^ nums[r];
            nums[r] = nums[l] ^ nums[r];
            nums[l] = nums[l] ^ nums[r];
        }
    }

}
