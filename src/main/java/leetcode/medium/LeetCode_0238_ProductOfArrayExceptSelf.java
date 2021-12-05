// Given an array nums of n integers where n > 1,  
// return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

// Example:

// Input:  [1,2,3,4]
// Output: [24,12,8,6]
// Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

// Note: Please solve it without division and in O(n).

// Follow up:
// Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
package leetcode.medium;

public class LeetCode_0238_ProductOfArrayExceptSelf {

    // 后缀积数组维持右边的积，左边部分用变脸来维持前缀积
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

    // 扩展 : 如果仅仅是不能用除号，把结果直接填在nums里呢？
    // 解法：数一共几个0；每一个位得到结果就是，a / b，位运算替代 /
    
    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5};
        int[] result = productExceptSelf(nums);
        for (int i : result) {
            System.out.println(i + " ");
        }
    }

}
