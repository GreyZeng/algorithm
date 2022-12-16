
package 练习题.lintcode.medium;

import java.util.ArrayList;
import java.util.List;

// 给定一个有序无重复的数组nums， 和两个整数lower和upper， 返回[lower,upper]上所有缺失的数字段
// 示例1:
// nums = [0,1,3,50,75], lower = 0, upper = 99
// 输出:["2","4->49","51->74","76->99"]
// 示例2:
// nums = [], lower = 1, upper = 1
// 输出: ["1"]
// 示例3:
// nums = [], lower = -3, upper = -1
// 输出： ["-3->-1"]
// 示例4:
// nums = [-1], lower = -1, upper = -1
// 输出: []
// 示例5:
// nums = [-1], lower = -2, upper = -1
// 输出: ["-2"]
// Leetcode题目 : https://leetcode.com/problems/missing-ranges/
// leetcode 163
// lintcode 链接：https://www.lintcode.com/problem/missing-ranges/description
public class LintCode_0641_MissingRanges {

  public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> list = new ArrayList<>();
    for (int num : nums) {
      if (num > lower) {
        list.add(build(lower, num - 1));
      }
      if (num == upper) {
        return list;
      }
      lower = num + 1;
    }
    if (lower <= upper) {
      list.add(build(lower, upper));
    }
    return list;
  }

  public static String build(int s, int e) {
    if (s == e) {
      return s + "";
    }
    return s + "->" + e;
  }

}
