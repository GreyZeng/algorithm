package leetcode;

import java.util.ArrayList;
import java.util.List;

//Given a collection of distinct integers, return all possible permutations.
//
//Example:
//
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// 记得恢复现场
public class LeetCode_0046_Permutations {

	public static List<List<Integer>> permute(int[] nums) {
		if (null == nums) {
			return null;
		}
		List<List<Integer>> res = new ArrayList<>();
		process(nums, 0, res);
		return res;

	}

	public static void process(int[] nums, int index, List<List<Integer>> res) {
		if (index == nums.length) {
			List<Integer> path = new ArrayList<>();
			for (int x : nums) {
				path.add(x);
			}
			res.add(path);
		} else {
			for (int i = index; i < nums.length; i++) {
				swap(nums, i, index);
				process(nums, index + 1, res);
				swap(nums, i, index);
			}
		}
	}

	private static void swap(int[] nums, int i, int index) {
		int t = nums[i];
		nums[i] = nums[index];
		nums[index] = t;

	}

}
