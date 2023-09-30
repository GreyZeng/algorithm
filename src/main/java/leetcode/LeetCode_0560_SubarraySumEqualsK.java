package leetcode;

import java.util.HashMap;

// 数组全为正数
// 累加和为K的子数组个数
// arr[L...R] = sum -> arr[0..R] - arr[0...L-1] = sum
// 子数组一定要以i结尾
// Map前缀和（key: 前缀和， value: 前缀和出现的最小位置）
// 初始化 Map.put(0,-1)
// （-100，3，1，2，100）
public class LeetCode_0560_SubarraySumEqualsK {
  public static int subarraySum(int[] nums, int k) {
    // 初始化前缀和数组
    HashMap<Integer, Integer> dp = new HashMap<>();
    dp.put(0, 1);
    int all = 0;
    int ans = 0;
    int g;
    for (int num : nums) {
      all += num;
      g = all - k;
      if (dp.containsKey(g)) {
        ans += dp.get(g);
      }
      if (!dp.containsKey(all)) {
        dp.put(all, 1);
      } else {
        dp.put(all, dp.get(all) + 1);
      }
    }
    return ans;
  }
}
