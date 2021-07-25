package lintcode;

//描述
//        Find any position of a target number in a sorted array. Return -1 if target does not exist.
//
//        样例
//        Example 1:
//
//        Input: nums = [1,2,2,4,5,5], target = 2
//        Output: 1 or 2
//        Example 2:
//
//        Input: nums = [1,2,2,4,5,5], target = 6
//        Output: -1
//        挑战
//        O(logn) time
public class LintCode_0457_ClassicalBinarySearch {
    public int findPosition(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int N = nums.length;
        int l = 0;
        int r = N - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,4};
        int target = 6;
        System.out.println(new LintCode_0457_ClassicalBinarySearch().findPosition(nums,target));
    }
}
