package leetcode;

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
 *
 * @author Grey
 * @date 2021年7月16日 下午9:31:05
 * @since
 */
public class LeetCode_0035_SearchInsertPosition {
    // 大于等于target的最左边位置
    public int searchInsert(int[] nums, int target) {
        return nearestLeft(nums, nums.length, target);
    }

    public static int nearestLeft(int[] arr, int n, int target) {
        if (arr[n - 1] < target) {
            return n;
        }
        int l = 0;
        int r = n - 1;
        int res = n;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= target) {
                res = mid;
                r = mid - 1;
            } else {
                // arr[mid] < target
                l = mid + 1;
            }
        }
        return res;
    }
}
