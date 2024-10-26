package grey.algorithm;


// 笔记：https://www.cnblogs.com/greyzeng/p/16622554.html
// 二分查找：在一个有序数组中，找某个数是否存在,返回目标值第一次出现的下标
// https://leetcode.com/problems/binary-search/
// https://www.lintcode.com/problem/14/
public class Code_0004_LintCode_0014_FirstPositionOfTarget {
    public int binarySearch(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                // lintcode中要求找满足条件的第一个，所以这里要先记录满足条件的，继续找左边有没有更符合要求的数据
                // leetcode 中，题目已经说明了，答案是唯一的，所以到这里可以直接返回。
                ans = mid;
                r = mid - 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
