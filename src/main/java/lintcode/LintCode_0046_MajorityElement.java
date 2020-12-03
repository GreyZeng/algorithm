package lintcode;

import java.util.List;

public class LintCode_0046_MajorityElement {
    public int majorityNumber(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        if (nums.size() == 1) {
            return nums.get(0);
        }
        int[] arr = new int[nums.size()];
        for (int i = 0 ; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        return majorityElement(arr);
        // write your code here
    }
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
