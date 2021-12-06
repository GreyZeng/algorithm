package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//        Your algorithm should run in O(n) complexity.
//
//        Example:
//
//        Input: [100, 4, 200, 1, 3, 2]
//        Output: 4
//        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
public class LeetCode_0128_LongestConsecutiveSequence {

    // Hash表标记开始位置和长度
    // 两个hash表 ，一个表示头<num, 数量> 一个表示尾<num, 数量>
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
                int L = map.get(n - 1) == null ? 0 : map.get(n - 1);
                int R = map.get(n + 1) == null ? 0 : map.get(n + 1);
                int all = L + R + 1;
                map.put(n - L, all);
                map.put(n + R, all);
                max = Math.max(all, max);
            }
        }
        return max;
    }
}
