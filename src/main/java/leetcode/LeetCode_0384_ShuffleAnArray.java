package leetcode;

// 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是等可能的。
//
// 实现 Solution class:
//
// Solution(int[] nums) 使用整数数组 nums 初始化对象
// int[] reset() 重设数组到它的初始状态并返回
// int[] shuffle() 返回数组随机打乱后的结果
//
//
// 示例 1：
//
// 输入
// ["Solution", "shuffle", "reset", "shuffle"]
// [[[1, 2, 3]], [], [], []]
// 输出
// [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
//
// 解释
// Solution solution = new Solution([1, 2, 3]);
// solution.shuffle(); // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
// solution.reset(); // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
// solution.shuffle(); // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
//
//
// 提示：
//
// 1 <= nums.length <= 50
// -10^6 <= nums[i] <= 10^6
// nums 中的所有元素都是 唯一的
// 最多可以调用 10^4 次 reset 和 shuffle
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/shuffle-an-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0384_ShuffleAnArray {

  private class Solution {
    public int[] origin;
    public int[] helper;

    public Solution(int[] arr) {
      origin = arr;
      helper = new int[arr.length];
      System.arraycopy(arr, 0, helper, 0, arr.length);
    }

    public int[] reset() {
      return origin;
    }

    // 0...i随机选一个位置的值和i上的值进行交换
    public int[] shuffle() {
      int index = origin.length - 1;
      for (int i = origin.length - 1; i >= 1; i--) {
        swap(helper, (int) (Math.random() * origin.length), index--);
      }
      return helper;
    }

    public void swap(int[] arr, int i, int j) {
      if (i != j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
      }
    }
  }
}
