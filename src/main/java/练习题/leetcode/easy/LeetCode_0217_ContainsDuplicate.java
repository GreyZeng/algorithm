package 练习题.leetcode.easy;

import java.util.HashSet;

// Given an array of integers, find if the array contains any duplicates.
//
// Your function should return true if any value appears at least twice in the array, and it should
// return false if every element is distinct.
//
// Example 1:
//
// Input: [1,2,3,1]
// Output: true
// Example 2:
//
// Input: [1,2,3,4]
// Output: false
// Example 3:
//
// Input: [1,1,1,3,3,4,3,2,4,2]
// Output: true
public class LeetCode_0217_ContainsDuplicate {
  public static boolean containsDuplicate(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int i : nums) {
      if (set.contains(i)) {
        return true;
      } else {
        set.add(i);
      }
    }
    return false;
  }
  // TODO 利用堆排序，额外复杂度O(1)

}
