package 练习题.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 笔记：https://www.cnblogs.com/greyzeng/p/16748973.html
// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
// 示例 1：
//
// 输入：nums = [1,2,2]
// 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 示例 2：
//
// 输入：nums = [0]
// 输出：[[],[0]]
//
// 提示：
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/subsets-ii
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0090_SubsetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        p(nums, 0, new LinkedList<>(), lists);
        return lists;
    }

    public static void p(int[] nums, int i, LinkedList<Integer> pre, List<List<Integer>> result) {
        result.add(new ArrayList<>(pre));
        for (int s = i; s < nums.length; s++) {
            if (s > i && nums[s] == nums[s - 1]) {
                continue;
            }
            pre.add(nums[s]);
            p(nums, s + 1, pre, result);
            pre.remove(pre.size() - 1);
        }
    }
}
