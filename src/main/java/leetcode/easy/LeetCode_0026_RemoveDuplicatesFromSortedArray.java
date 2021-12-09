/**
 * Given a sorted array nums, remove the duplicates in-place such that each
 * element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively.
 * <p>
 * It doesn't matter what you leave beyond the returned length. Example 2:
 * <p>
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * Your function should return length = 5, with the first five elements of nums
 * being modified to 0, 1, 2, 3, and 4 respectively.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means modification
 * to the input array will be known to the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy) int len =
 * removeDuplicates(nums);
 * <p>
 * // any modification to nums in your function would be known by the caller. //
 * using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) { print(nums[i]); }
 */
package leetcode.easy;

public class LeetCode_0026_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        int fill = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != nums[fill]) {
                nums[++fill] = nums[cur];
            }
        }
        return fill + 1;
    }
}
