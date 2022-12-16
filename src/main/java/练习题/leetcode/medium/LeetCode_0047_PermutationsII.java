package 练习题.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 打印数组的全部排列（有重复）
// 笔记：https://www.cnblogs.com/greyzeng/p/16749313.html
// https://leetcode-cn.com/problems/permutations-ii/
public class LeetCode_0047_PermutationsII {
  public static List<List<Integer>> permuteUnique(int[] arr) {
    List<List<Integer>> ans = new ArrayList<>();
    p(0, arr, ans);
    return ans;
  }

  private static void p(int i, int[] arr, List<List<Integer>> result) {
    if (i == arr.length - 1) {
      // 来到最后一个位置，收集答案
      result.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
      return;
    }
    boolean[] visited = new boolean[21];
    for (int index = i; index < arr.length; index++) {
      if (!visited[arr[index] + 10]) {
        visited[arr[index] + 10] = true;
        swap(index, i, arr);
        p(i + 1, arr, result);
        swap(index, i, arr);
      }
    }
  }

  private static void swap(int i, int j, int[] arr) {
    if (j == i) {
      return;
    }
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }
}
