//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
//
//        Example 1:
//        Input: [0,1]
//        Output: 2
//        Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
//        Example 2:
//        Input: [0,1,0]
//        Output: 2
//        Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//        Note: The length of the given binary array will not exceed 50,000.
package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// 预处理一下原数组，遇到0变为-1，遇到1保持1，遇到其他变为0
// 求子数组之和为0的最大子数组长度
public class LeetCode_0525_ContiguousArray {
    public static int findMaxLength(int[] nums) {
        int N = nums.length;
        int cur = 0;
        for (; cur < N; cur++) {
            if (nums[cur] == 0) {
                nums[cur] = -1;
            }
        }
        // 累加和为0的最长数组长度
        Map<Integer, Integer> map = new HashMap<>(N);
        map.put(0, -1);
        int sum = 0;
        int max = 0;
        for (cur = 0; cur < N; cur++) {
            sum += nums[cur];
            if (!map.containsKey(sum)) {
                map.put(sum, cur);
            }
            max = Math.max(max, cur - map.get(sum));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 1, 0, 1, 0};
        System.out.println(findMaxLength(nums));
    }
}
