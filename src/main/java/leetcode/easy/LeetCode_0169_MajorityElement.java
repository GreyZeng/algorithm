package leetcode.easy;


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
// 水王问题
// https://www.cnblogs.com/greyzeng/p/14410280.html
public class LeetCode_0169_MajorityElement {
    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums1));
        System.out.println(majorityElement(nums2));
    }

    // 已知数组中一定有某个数个数大于N/2个，求这个数
    // tips:每次删除两个不同的数，这个数肯定会剩下来
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int cand = nums[0];
        int HP = 1;
        int i = 1;
        int limit = nums.length >> 1;
        while (i < nums.length) {
            if (cand == nums[i]) {
                HP++;
                // 如果某时，HP已经大于一半的数了，直接返回
                if (HP > limit) {
                    return nums[i];
                }
            } else {
                if (HP == 0) {
                    cand = nums[i];
                    HP++;
                } else {
                    HP--;
                }
            }
            i++;
        }
        if (HP == 0) {
            return -1;
        }
        int c = 0;
        for (int num : nums) {
            if (cand == num) {
                c++;
            }
        }
        if (c > limit) {
            return cand;
        } else {
            return -1;
        }
    }
}
