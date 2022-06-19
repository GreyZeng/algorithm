package leetcode.medium;

//You are a professional robber planning to rob houses along a street. 
// Each house has a certain amount of money stashed. 
// All houses at this place are arranged in a circle. 
// That means the first house is the neighbor of the last one. 
// Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
//
// 
//
//Example 1:
//
//Input: nums = [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
//Example 2:
//
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.
//Example 3:
//
//Input: nums = [0]
//Output: 0
// 
//
//Constraints:
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 1000
public class LeetCode_0213_HouseRobberII {
	public static int rob(int[] nums) {
		if (nums == null) {
			return 0;
		}
		int l = nums.length;
		if (l == 0) {
			return 0;
		}
		if (l == 1) {
			return nums[0];
		}
		if (l == 2) {
			return Math.max(nums[0], nums[1]);
		}
		if (l == 3) {
			return Math.max(nums[0],Math.max(nums[1],   nums[2]));
		}
		int pre = nums[0];
		int max = Math.max(nums[0], nums[1]);
		for (int i = 2; i < l - 1; i++) {
			int cur = Math.max(pre + nums[i], max);
			pre = max;
			max = cur;
		}
		int maxWithOutLast = max; 
		pre = nums[1];
		max = Math.max(nums[1], nums[2]);
		for (int i = 3; i < l ; i++) {
			int cur = Math.max(pre + nums[i], max);
			pre = max;
			max = cur;
		}
		return Math.max(max, maxWithOutLast);
	}

}
