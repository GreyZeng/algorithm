package leetcode.easy;

// 二分查找： 在一个有序数组中，找大于等于某个数最左侧的位置，如果找不到，则返回数组长度
// https://leetcode.cn/problems/search-insert-position/
public class LeetCode_0035_SearchInsertPosition {
    public static int searchInsert(int[] arr, int t) {
        int ans = arr.length;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] < t) {
                l = m + 1;
            } else {
                r = m - 1;
                ans = m;
            }
        }
        return ans;
    }
}
