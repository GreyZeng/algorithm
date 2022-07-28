
package leetcode.hard;
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数
//        进阶，在两个都有序的数组中找整体第K小的数，可以做到O(log(Min(M,N)))
// 寻找两个正序数组中的中位数
// 笔记：https://www.cnblogs.com/greyzeng/p/16324785.html
// https://leetcode.com/problems/median-of-two-sorted-arrays/
public class LeetCode_0004_MedianOfTwoSortedArrays {
    // 解法1 O(M+N)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 题目已经说明nums1和nums2不能同时为空
        if (null == nums1 || nums1.length == 0) {
            return median(nums2);
        }
        if (null == nums2 || nums2.length == 0) {
            return median(nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (nums1[i] >= nums2[j]) {
                nums[index++] = nums2[j++];
            } else {
                nums[index++] = nums1[i++];
            }
        }
        while (i < m) {
            nums[index++] = nums1[i++];
        }
        while (j < n) {
            nums[index++] = nums2[j++];
        }
        return median(nums);
    }

    public static double median(int[] arr) {
        int len = arr.length;
        if ((len & 1) == 1) {
            // 奇数
            return arr[len / 2];
        }
        return ((arr[len / 2] + arr[(len - 1) / 2]) / 2d);
    }

    // 最优解 O(log(M+N))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 题目已经说明nums1和nums2不能同时为空
        if (null == nums1 || nums1.length == 0) {
            return median(nums2);
        }
        if (null == nums2 || nums2.length == 0) {
            return median(nums1);
        }
        int m = nums1.length;
        int n = nums2.length;

        if (((m + n) & 1) == 1) {
            return findKth(nums1, nums2, (m + n) / 2 + 1);
        }
        return (findKth(nums1, nums2, (m + n) / 2) + findKth(nums1, nums2, ((m + n) / 2 + 1))) / 2d;
    }

    public static int findKth(int[] nums1, int[] nums2, int k) {
        int[] longs = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorts = nums1 == longs ? nums2 : nums1;
        int maxLen = longs.length;
        int minLen = shorts.length;
        // k<= minLen
        // longs截取前k个数，shorts截取前k个数，两个长度相等的数组取上中位数
        if (k <= minLen) {
            return getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
        }
        // k > minLen 且 k <= maxLen
        // 到这里一定k > minLen
        if (k <= maxLen) {
            // 可以排除
            // longs中比第k小的数还大的所有数都可以排除，即下标为：[k....maxLen - 1]
            // longs中第1小到第(k - minLen - 1)小的数都可以排除
            // shorts中所有数都有可能


            // 手动验证
            // longs中第 (k - minLen) 大的数是否比shorts中最后一个数大，如果是，这个数直接就是结果
            if (longs[k - minLen - 1] >= shorts[minLen - 1]) {
                return longs[k - minLen - 1];
            }
            // 其他的数
            return getUpMedian(shorts, 0, minLen - 1, longs, k - minLen, k - 1);
        }
        // k > maxLen 且 k <= maxLen + minLen
        // 到这里一定 k > maxLen 且 k <= maxLen + minLen


        // 可以排除
        // shorts中从第1小一直到第k - maxLen - 1小的数都可以排除
        // longs中第1小的数一直到第k - minLen - 1小的数都可以排除


        // 手动验证
        if (shorts[minLen - 1] <= longs[k - minLen - 1]) {
            return longs[k - minLen - 1];
        }
        if (longs[maxLen - 1] <= shorts[k - maxLen - 1]) {
            return shorts[k - maxLen - 1];
        }
        // 其他的数

        return getUpMedian(shorts, k - maxLen, minLen - 1, longs, k - minLen, maxLen - 1);
    }

    // 获取两个长度相等的有序数组merge后的上中位数
    // 如果是偶数，取上中位数
    // 调用该方法的时候保证[s1...e1] 和 [s2...e2]等长
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        if (s1 == e1) {
            return Math.min(A[s1], B[s2]);
        }
        int mid1 = (s1 + e1) / 2;
        int mid2 = (s2 + e2) / 2;
        if (A[mid1] == B[mid2]) {
            return A[mid1];
        }
        boolean even = ((e1 - s1) & 1) != 0; // 是否是偶数
        if (even) {
            if (A[mid1] > B[mid2]) {
                return getUpMedian(A, s1, mid1, B, mid2 + 1, e2);
            } else {
                return getUpMedian(A, mid1 + 1, e1, B, s2, mid2);
            }
        } else {
            if (A[mid1] > B[mid2]) {
                if (B[mid2] > A[mid1 - 1]) {
                    return B[mid2];
                }
                return getUpMedian(A, s1, mid1 - 1, B, mid2 + 1, e2);
            } else {
                if (A[mid1] > B[mid2 - 1]) {
                    return A[mid1];
                }
                return getUpMedian(A, mid1 + 1, e1, B, s2, mid2 - 1);
            }
        }
    }

}