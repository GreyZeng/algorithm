package leetcode;

import java.util.ArrayList;
import java.util.List;

//Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//
//
//		Example 1:
//
//		Input: nums = [1,2,3]
//		Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//		Example 2:
//
//		Input: nums = [0,1]
//		Output: [[0,1],[1,0]]
//		Example 3:
//
//		Input: nums = [1]
//		Output: [[1]]
//
//
//		Constraints:
//
//		1 <= nums.length <= 6
//		-10 <= nums[i] <= 10
//		All the integers of nums are unique
public class LeetCode_0046_Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        p(nums, 0, ans);
        return ans;
    }

    // 所有index以后的数字都可以来到index位置进行替换
    // 生成的组合放在path里面
    public static void p(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length - 1) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                path.add(nums[i]);
            }
            ans.add(path);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, i, index);
                p(nums, index + 1, ans);
                // 恢复现场
                swap(nums, index, i);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
