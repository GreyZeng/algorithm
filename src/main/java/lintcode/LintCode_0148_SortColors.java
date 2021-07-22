// Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

// We will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

// You are not suppose to use the library's sort function for this problem.
// You should do it in-place (sort numbers in the original array).
// You are not allowed to use counting sort to solve this problem.

// 样例
// Example 1

// Input : [1, 0, 1, 2]
// Output : [0, 1, 1, 2]
// Explanation : sort it in-place
// 挑战
// Could you come up with an one-pass algorithm using only O(1) space?
package lintcode;

public class LintCode_0148_SortColors {
    public static void sortColors(int[] nums) {
        int size = nums.length;
        int L = -1;
        int R = size;
        int target = 1;
        int i = 0;
        while (i < R  ) {
            if (nums[i] > target) {
                swap(nums, i, --R);
            } else if (nums[i] < target) {
                swap(nums, i++, ++L);
            } else { 
                i++;
            }
        }
    }    
    public static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
    
}
