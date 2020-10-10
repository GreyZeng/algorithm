package leetcode;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump length at that
 * position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
 * to 1, then 3 steps to the last index. Example 2:
 *
 * Input: nums = [3,2,1,0,4] Output: false Explanation: You will always arrive
 * at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4 0 <= nums[i][j] <= 10^5
 */
public class LeetCode_0055_JumpGame {

	public static boolean canJump(int[] nums) {
		if (nums == null) {
			return false;
		}
		int N = nums.length;
		if (N == 0) {
			return false;
		}
		if (N == 1) {
			return true;
		}
		int max = nums[0];

		if (max >= N - 1) {
			return true;
		}
		for (int i = 1; i < N; i++) {
			if (max >= N - 1) {
				return true;
			}
			if (i > max) {
				return false;
			}
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}
}
