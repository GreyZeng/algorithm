package leetcode;

/**
 * Given an array of integers nums which is sorted in ascending order, and an
 * integer target, write a function to search target in nums. If target exists,
 * then return its index. Otherwise, return -1.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 9 Output: 4 Explanation: 9 exists in
 * nums and its index is 4 Example 2:
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 2 Output: -1 Explanation: 2 does not
 * exist in nums so return -1
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 10^4
 * 
 * -10^4 < nums[i], target < 10^4
 * 
 * All the integers in nums are unique. nums is sorted in ascending order.
 * 
 * @author Grey
 * @date 2021年7月16日 下午9:04:48
 * @since
 */
public class LeetCode_0704_BinarySearch {
	public int search(int[] nums, int target) {
		int L = 0;
		int R = nums.length - 1;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				L = mid + 1;
			} else {
				// nums[mid] > target
				R = mid - 1;
			}
		}
		return -1;
	}

}
