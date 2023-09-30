package resolved.binarysearch;

// Given a sorted array (ascending order) and a target number, find the first index of this number
// in O(logn) time complexity.
public class LintCode_0014_FirstPositionOfTarget {
  public int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int l = 0;
    int r = nums.length - 1;
    int ans = -1;
    while (l <= r) {
      int m = l + ((r - l) >> 1);
      if (nums[m] == target) {
        ans = m;
        r = m - 1;
      } else if (nums[m] < target) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return ans;
  }
}
