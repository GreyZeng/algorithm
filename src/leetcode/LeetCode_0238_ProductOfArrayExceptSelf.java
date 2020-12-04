// Given an array nums of n integers where n > 1,  
// return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

// Example:

// Input:  [1,2,3,4]
// Output: [24,12,8,6]
// Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

// Note: Please solve it without division and in O(n).

// Follow up:
// Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
package leetcode;

public class LeetCode_0238_ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] result = new int[N];
        result[0] = 1;
        result[1] = nums[0];
        for (int i = 2; i < N; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int t = 1;
        for (int i = N - 2; i >= 0; i--) {
            t = t * nums[i + 1];
            result[i] = result[i] * t;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        int[] result = productExceptSelf(nums);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }

}
