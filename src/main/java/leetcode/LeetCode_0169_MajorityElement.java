package leetcode;


//Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
//
//		You may assume that the array is non-empty and the majority element always exist in the array.
//
//		Example 1:
//
//		Input: [3,2,3]
//		Output: 3
//		Example 2:
//
//		Input: [2,2,1,1,1,2,2]
//		Output: 2
public class LeetCode_0169_MajorityElement {

    // 已知数组中一定有某个数个数大于N/2个，求这个数
    // tips:每次删除两个不同的数，这个数肯定会剩下来
    public static int majorityElement(int[] nums) {
        int cand = nums[0];
        int HP = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cand == nums[i]) {
                HP++;
            } else if (HP != 0) {
                HP--;
            } else {
                HP = 1;
                cand = nums[i];
            }
        }
        return cand;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2};
        System.out.println(majorityElement(nums));

    }
}
