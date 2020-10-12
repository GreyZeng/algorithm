/*Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

        Example:

        Input: [0,1,0,3,12]
        Output: [1,3,12,0,0]
        Note:

        You must do this in-place without making a copy of the array.
        Minimize the total number of operations.*/
package leetcode;

public class LeetCode_0283_MoveZeroes {
    // 双指针
    // 一个在-1位置，一个在0位置
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int N = nums.length;
        int base = -1;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                swap(nums, i, ++base);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (nums == null || nums.length < 2 || i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {5,0,3,2,0,4,0};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

}
