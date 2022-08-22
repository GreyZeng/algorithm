package leetcode.medium;

// 荷兰国旗问题
// 仅0，1，2，所有的0放左边，所有的1放中间，所有的2放右边
//https://leetcode.com/problems/sort-colors/
public class LeetCode_0075_SortColors {

    public static void sortColors(int[] nums) {
        int l = -1;
        int r = nums.length;
        int i = 0;
        while (i < r) {
            if (nums[i] > 1) {
                swap(nums, i, --r);
            } else if (nums[i] < 1) {
                swap(nums, i++, ++l);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }
        nums[l] = nums[l] ^ nums[r];
        nums[r] = nums[l] ^ nums[r];
        nums[l] = nums[l] ^ nums[r];
    }
}
