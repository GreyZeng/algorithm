package leetcode.medium;

// https://leetcode-cn.com/problems/find-peak-element/
// https://www.cnblogs.com/greyzeng/p/15690136.html
// 返回局部高点的位置，如果有多个，返回一个峰值位置即可
// 对于所有有效的 i 都有 nums[i] != nums[i + 1]
// 二分
public class LeetCode_0162_FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int l = 0;
        int r = nums.length - 1;
        if (nums[l] > nums[l + 1]) {
            return l;
        }
        if (nums[r] > nums[r - 1]) {
            return r;
        }
        l = l + 1;
        r = r - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            }
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) {
                r = mid - 1;
            }
        }
        return -1;
    }
}
