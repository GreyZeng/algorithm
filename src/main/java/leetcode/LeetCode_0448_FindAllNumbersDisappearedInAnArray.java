package leetcode;

import java.util.ArrayList;
import java.util.List;

// 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
// 示例 1：
// 输入：nums = [4,3,2,7,8,2,3,1]
// 输出：[5,6]
// 示例 2：
// 输入：nums = [1,1]
// 输出：[2]
// 提示：
// n == nums.length
// 1 <= n <= 10^5
// 1 <= nums[i] <= n
// 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
// Leetcode题目 : https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// tips: 下标循环怼
public class LeetCode_0448_FindAllNumbersDisappearedInAnArray {
  // 0号位置必须是1
  // 1号位置必须是2
  public List<Integer> findDisappearedNumbers(int[] arr) {
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != i + 1) {
        cycle(arr, i);
      }
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != i + 1) {
        ans.add(i + 1);
      }
    }
    return ans;
  }

  private void cycle(int[] arr, int i) {
    while (arr[i] != i + 1) {
      int t = arr[i];
      if (arr[t - 1] == t) {
        break;
      }
      swap(arr, i, t - 1);
    }
  }

  public void swap(int[] arr, int i, int j) {
    if (i != j) {
      arr[i] = arr[i] ^ arr[j];
      arr[j] = arr[i] ^ arr[j];
      arr[i] = arr[i] ^ arr[j];
    }
  }
}
