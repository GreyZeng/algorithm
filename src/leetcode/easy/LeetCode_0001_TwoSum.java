package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Grey
 * @date 2021年7月13日 下午11:12:15
 * @since 1.8
 */
public class LeetCode_0001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
