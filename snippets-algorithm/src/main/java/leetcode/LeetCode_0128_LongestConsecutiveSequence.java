package leetcode;

import java.util.HashMap;

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
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int L = map.get(num - 1) == null ? 0 : map.get(num - 1);
                int R = map.get(num + 1) == null ? 0 : map.get(num + 1);
                int all = L + R + 1;
                map.put(num - L, all);
                map.put(num + R, all);
                max = Math.max(max, all);
            }
        }
        return max;
    }

}
