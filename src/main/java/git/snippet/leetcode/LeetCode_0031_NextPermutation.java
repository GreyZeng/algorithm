package git.snippet.leetcode;

// TODO
// 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
// 必须 原地 修改，只允许使用额外常数空间。
// 示例 1：
// 输入：nums = [1,2,3]
// 输出：[1,3,2]
// 示例 2：
// 输入：nums = [3,2,1]
// 输出：[1,2,3]
// 示例 3：
// 输入：nums = [1,1,5]
// 输出：[1,5,1]
// 示例 4：
// 输入：nums = [1]
// 输出：[1]
// leetcode题目 : https://leetcode.cn/problems/next-permutation/
public class LeetCode_0031_NextPermutation {

  public static void nextPermutation(int[] nums) {
    int N = nums.length;
    // 从右往左第一次降序的位置
    int firstLess = -1;
    for (int i = N - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        firstLess = i;
        break;
      }
    }
    if (firstLess < 0) {
      reverse(nums, 0, N - 1);
    } else {
      int rightClosestMore = -1;
      // 找最靠右的、同时比nums[firstLess]大的数，位置在哪
      // 这里其实也可以用二分优化，但是这种优化无关紧要了
      for (int i = N - 1; i > firstLess; i--) {
        if (nums[i] > nums[firstLess]) {
          rightClosestMore = i;
          break;
        }
      }
      swap(nums, firstLess, rightClosestMore);
      reverse(nums, firstLess + 1, N - 1);
    }
  }

  public static void reverse(int[] nums, int L, int R) {
    while (L < R) {
      swap(nums, L++, R--);
    }
  }

  public static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
