package leetcode;

/**
 * Given a sorted array of distinct integers and a target value, return the
 * index if the target is found.
 * 
 * If not, return the index where it would be if it were inserted in order. You
 * must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * 
 * Input:
 * 
 * nums = [1,3,5,6], target = 5 Output: 2
 * 
 * 
 * Example 2:
 * 
 * Input: nums = [1,3,5,6], target = 2 Output: 1
 * 
 * Example 3: Input: nums = [1,3,5,6], target = 7 Output: 4
 * 
 * Example 4: Input: nums = [1,3,5,6], target = 0 Output: 0
 * 
 * Example 5: Input: nums = [1], target = 0 Output: 0
 * 
 * Constraints: 1 <= nums.length <= 10^4
 * 
 * 
 * -10^4 <= nums[i] <= 10^4
 * 
 * nums contains distinct values sorted in ascending order.
 * 
 * -10^4 <= target <=10^4
 * 
 * @author Grey
 * @date 2021年7月16日 下午9:31:05
 * @since
 */
public class LeetCode_0035_SearchInsertPosition {

	public static int searchInsert(int[] nums, int target) {
		int L = 0;
		int R = nums.length - 1;
		int index = -1;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		if (index == -1) {
			return nums.length;
		}
		return L;
	}
}
