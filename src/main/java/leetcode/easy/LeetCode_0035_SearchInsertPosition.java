package leetcode.easy;

/**
 * Given a sorted array of distinct integers and a target value, return the
 * index if the target is found.
 * <p>
 * If not, return the index where it would be if it were inserted in order. You
 * must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * nums = [1,3,5,6], target = 5 Output: 2
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,3,5,6], target = 2 Output: 1
 * <p>
 * Example 3: Input: nums = [1,3,5,6], target = 7 Output: 4
 * <p>
 * Example 4: Input: nums = [1,3,5,6], target = 0 Output: 0
 * <p>
 * Example 5: Input: nums = [1], target = 0 Output: 0
 * <p>
 * Constraints: 1 <= nums.length <= 10^4
 * <p>
 * <p>
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * nums contains distinct values sorted in ascending order.
 * <p>
 * -10^4 <= target <=10^4
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * @author Grey
 * @date 2021年7月16日 下午9:31:05
 * @since
 */
public class LeetCode_0035_SearchInsertPosition {
    
    // >= 某个数的最左侧位置，如果找不到，则为nums.length
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int l = 0;
        int r = nums.length - 1;
        int ans = nums.length;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > target) {
                ans = mid;
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }
}
