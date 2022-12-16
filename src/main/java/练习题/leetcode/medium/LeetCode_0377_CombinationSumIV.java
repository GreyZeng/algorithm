package 练习题.leetcode.medium;

import java.util.Arrays;

// TODO
// 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
// 题目数据保证答案符合 32 位整数范围。
// leetcode题目：https://leetcode.cn/problems/combination-sum-iv
// tips:
// 枚举第一块是什么
public class LeetCode_0377_CombinationSumIV {

  // 当前剩下的值是rest，
  // nums中所有的值，都可能作为分解rest的，第一块！全试一试
  // nums, 无重复，都是正
  // rest,
  public static int ways(int rest, int[] nums) {
    if (rest < 0) {
      return 0;
    }
    if (rest == 0) {
      return 1;
    }
    int ways = 0;
    for (int num : nums) {
      ways += ways(rest - num, nums);
    }
    return ways;
  }

  public static int[] dp = new int[1001];

  public static int combinationSum41(int[] nums, int target) {
    Arrays.fill(dp, 0, target + 1, -1);
    return process1(nums, target);
  }

  public static int process1(int[] nums, int rest) {
    if (rest < 0) {
      return 0;
    }
    if (dp[rest] != -1) {
      return dp[rest];
    }
    int ans = 0;
    if (rest == 0) {
      ans = 1;
    } else {
      for (int num : nums) {
        ans += process1(nums, rest - num);
      }
    }
    dp[rest] = ans;
    return ans;
  }

  // 剪枝 + 严格位置依赖的动态规划
  public static int combinationSum42(int[] nums, int target) {
    Arrays.sort(nums);
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int rest = 1; rest <= target; rest++) {
      for (int i = 0; i < nums.length && nums[i] <= rest; i++) {
        dp[rest] += dp[rest - nums[i]];
      }
    }
    return dp[target];
  }

}
