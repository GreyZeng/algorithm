package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.List;

// 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合
// ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
//
//
//
// 示例 1：
//
// 输入：candidates = [2,3,6,7], target = 7
// 输出：[[2,2,3],[7]]
// 解释：
// 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
// 7 也是一个候选， 7 = 7 。
// 仅有这两种组合。
// 示例 2：
//
// 输入: candidates = [2,3,5], target = 8
// 输出: [[2,2,2,2],[2,3,3],[3,5]]
// 示例 3：
//
// 输入: candidates = [2], target = 1
// 输出: []
//
//
// 提示：
//
// 1 <= candidates.length <= 30
// 2 <= candidates[i] <= 40
// candidates 的所有元素 互不相同
// 1 <= target <= 40
// 笔记：https://www.cnblogs.com/greyzeng/p/16819135.html
// https://leetcode.cn/problems/combination-sum/
public class LeetCode_0039_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] arr, int k) {
        return p(arr, arr.length, 0, k);
    }

    // 从i号位置开始及其后面的所有，帮我搞定target
    private static List<List<Integer>> p(int[] arr, int len, int i, int k) {

        if (i == len) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (k == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        for (int times = 0; times * arr[i] <= k; times++) {
            if (times * arr[i] == k) {
                List<Integer> t = new ArrayList<>();
                for (int j = 0; j < times; j++) {
                    t.add(arr[i]);
                }
                ans.add(t);
                return ans;
            }
            for (List<Integer> a : p(arr, len, i + 1, k - times * arr[i])) {
                for (int j = 0; j < times; j++) {
                    a.add(arr[i]);
                }
                ans.add(a);
            }
        }
        return ans;
    }
}
