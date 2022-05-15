package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-consecutive-sequence/
// 最长连续数组长度
public class LeetCode_0128_LongestConsecutiveSequence {

    // Hash表标记开始位置和长度
    public static int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int num : arr) {
            if (!map.containsKey(num)) {
                // 至少是一个长度为1的连续数组
                map.put(num, 1);
                // 由于num的到来，会让num-1位置上和num+1位置上（如果有的话）的最长连续数组变大
                int L = map.getOrDefault(num - 1, 0);
                int R = map.getOrDefault(num + 1, 0);
                int all = L + R + 1;
                max = Math.max(all, max);
                // 由于num的到来，会扩充两个位置的值，以num-L开头和以num-R结尾的
                map.put(num - L, all);
                map.put(num + R, all);
            }
        }
        return max;
    }
}
