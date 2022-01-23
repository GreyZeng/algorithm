package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a set of distinct integers, nums, return all possible subsets (the power set).
//
//		Note: The solution set must not contain duplicate subsets.
//
//		Example:
//
//		Input: nums = [1,2,3]
//		Output:
//		[
//		[3],
//		[1],
//		[2],
//		[1,2,3],
//		[1,3],
//		[2,3],
//		[1,2],
//		[]
//		]
// https://leetcode-cn.com/problems/subsets/description/
public class LeetCode_0078_Subsets {

    // 记得清除现场
    public static List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        process(0, nums, path, ans);
        return ans;
    }

    // 从i以及往后，nums所有的子集合存在ans里面
    public static void process(int i, int[] nums, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>(path);
            ans.add(list);
        } else {
            process(i + 1, nums, path, ans);
            // 选了i位置的值
            path.addLast(nums[i]);
            process(i + 1, nums, path, ans);
            path.removeLast();
        }
    }
}
