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
        int l = -1;
        int r = nums.length;
        int i = 0;
        while (i < r) {
            if (nums[i] < 1) {
                swap(nums, ++l, i++);
            } else if (nums[i] > 1) {
                swap(nums, --r, i);
            } else {
                // nums[i] == 1
                i++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 1, 0, 2, 2, 0, 2, 1 };
        sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

}
