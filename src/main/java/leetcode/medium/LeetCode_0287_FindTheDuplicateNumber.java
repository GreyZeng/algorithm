package leetcode.medium;

//Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
//        There is only one duplicate number in nums, return this duplicate number.
//
//        Follow-ups:
//
//        How can we prove that at least one duplicate number must exist in nums?
//        Can you solve the problem without modifying the array nums?
//        Can you solve the problem using only constant, O(1) extra space?
//        Can you solve the problem with runtime complexity less than O(n2)?
//
//
//        Example 1:
//
//        Input: nums = [1,3,4,2,2]
//        Output: 2
//        Example 2:
//
//        Input: nums = [3,1,3,4,2]
//        Output: 3
//        Example 3:
//
//        Input: nums = [1,1]
//        Output: 1
//        Example 4:
//
//        Input: nums = [1,1,2]
//        Output: 1
//
//
//        Constraints:
//
//        2 <= n <= 3 * 104
//        nums.length == n + 1
//        1 <= nums[i] <= n
//        All the integers in nums appear only once except for precisely one integer which appears two or more times.
public class LeetCode_0287_FindTheDuplicateNumber {
    // 单链表入环节点就是重复数字
    public static int findDuplicate(int[] nums) {

        int s = nums[0];
        int f = nums[nums[0]];

        while (s != f) {
            s = nums[s];
            f = nums[nums[f]];
        }
        f = 0;
        while (s != f) {
            s = nums[s];
            f = nums[f];
        }
        return s;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 3, 4, 2};
        System.out.println(findDuplicate(arr));
    }
}
