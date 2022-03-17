package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
//        解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
//         
//
//        示例 1：
//
//        输入：nums = [1,2,2]
//        输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
//        示例 2：
//
//        输入：nums = [0]
//        输出：[[],[0]]
//         
//
//        提示：
//
//        1 <= nums.length <= 10
//        -10 <= nums[i] <= 10
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/subsets-ii
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class LeetCode_0090_SubsetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        p(nums, 0, new LinkedList<>(), lists);
        return lists;
    }

    public static void p(int[] nums, int start, LinkedList<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            p(nums, i + 1, list, result);
            list.remove(list.size() - 1);

        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        List<List<Integer>> lists = subsetsWithDup(arr);
        System.out.println(lists);
    }
}
