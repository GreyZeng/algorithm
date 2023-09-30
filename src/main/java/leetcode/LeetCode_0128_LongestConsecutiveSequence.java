package leetcode;

import java.util.HashMap;
import java.util.Map;

// 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。请你设计并实现时间复杂度为O(n)
// 的算法解决此问题。
// tips:
// 最优解 可以做到O(N)
// 头map key:值 value：长度
// 尾map key:值 value：长度
// 依次看能否合并
//
// 还可以优化
// 只需要一张map
// key所在的连续区间，一共有多少长度
// key不需要删数据，遇到重复值不处理
// 不区分开头和结尾
// 脏数据不用管，也不需要删除，不会影响最后的解，只保证开头和结尾即可
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
