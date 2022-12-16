package 练习题.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 笔记：https://www.cnblogs.com/greyzeng/p/16748973.html
// 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
// 示例 1：
// 输入：nums = [1,2,3]
// 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
// 示例 2：
// 输入：nums = [0]
// 输出：[[],[0]]
// 提示：
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/subsets
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0078_Subsets {
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    p(nums, 0, new LinkedList<>(), result);
    return result;
  }

  // i往后收集所有的子序列
  public static void p(int[] arr, int i, LinkedList<Integer> pre, List<List<Integer>> result) {
    if (i == arr.length) {
      List<Integer> ans = new ArrayList<>(pre);
      result.add(ans);
    } else {
      // 不要i位置
      p(arr, i + 1, pre, result);
      pre.addLast(arr[i]);
      // 要i位置
      p(arr, i + 1, pre, result);
      pre.removeLast();
    }
  }
}
