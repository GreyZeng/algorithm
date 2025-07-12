package git.snippet.leetcode;

import java.util.Map;
import java.util.HashMap;

// https://leetcode.com/problems/two-sum/
public class LeetCode_0001_TwoSum {
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] {map.get(target - nums[i]),i};
			} 
			map.put(nums[i], i);
		}
		return new int[] {-1,-1};
	}
	public static void main(String[] args) {
		int[] arr = {2,3,4,5};
		int target = 9;
		int[] twoSum = twoSum(arr, target);
		for (int v : twoSum) {
			System.out.println(v);
		}
	}
}
