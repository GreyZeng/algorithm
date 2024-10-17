package git.snippet.mergesort;

// 合并两个有序数组
// https://leetcode.com/problems/merge-sorted-array/
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html

/**
 * @see LintCode_0006_MergeTwoSortedArrays
 */
public class LeetCode_0088_MergeSortedArray {
    // 逆向遍历
    // 谁大到最后一个位置
    // 依次递减
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[index--] = nums1[i] < nums2[j] ? nums2[j--] : nums1[i--];
        }
        while (j >= 0) {
            // 只需要继续判断 nums2 了
            // 因为 nums1 自然拍好了
            nums1[index--] = nums2[j--];
        }
    }
}
