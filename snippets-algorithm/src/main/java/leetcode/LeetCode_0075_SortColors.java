package leetcode;

//Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
//        Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
//        Note: You are not suppose to use the library's sort function for this problem.
//
//        Example:
//
//        Input: [2,0,2,1,1,0]
//        Output: [0,0,1,1,2,2]
//        Follow up:
//
//        A rather straight forward solution is a two-pass algorithm using counting sort.
//        First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
//        Could you come up with a one-pass algorithm using only constant space?
public class LeetCode_0075_SortColors {
    // 荷兰国旗问题
    public static void sortColors(int[] nums) {
        if (null == nums) {
            return;
        }
        int N = nums.length;
        if (N == 1) {
            return;
        }
        int L = -1;
        int R = N;
        for (int i = 0; i < R; i++) {
            if (nums[i] < 1) {
                swap(nums, ++L, i);
            } else if (nums[i] > 1) { 
                swap(nums, i--, --R);
            }
        }

    }

    private static void swap(int[] nums, int L, int R) {
        if (L == R) {
            return;
        }
        nums[L] = nums[L] ^ nums[R];
        nums[R] = nums[L] ^ nums[R];
        nums[L] = nums[L] ^ nums[R];
    }

    public static void main(String[] args) {
        int[] nums = {0	,1,-100,-1,4,2,6,0,2,1};
        sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }


}
