package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// 预处理一下原数组，遇到0变为-1，遇到1保持1，遇到其他变为0
// 求子数组之和为0的最大子数组长度
// https://leetcode.com/problems/contiguous-array/
public class LeetCode_0525_ContiguousArray {
    public static int findMaxLength(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        // 转换为累加和等于K的最长子数组长度
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}
