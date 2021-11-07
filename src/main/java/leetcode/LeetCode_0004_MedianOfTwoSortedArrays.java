//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//        The overall run time complexity should be O(log (m+n)).
//
//
//
//        Example 1:
//
//        Input: nums1 = [1,3], nums2 = [2]
//        Output: 2.00000
//        Explanation: merged array = [1,2,3] and median is 2.
//        Example 2:
//
//        Input: nums1 = [1,2], nums2 = [3,4]
//        Output: 2.50000
//        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//        Example 3:
//
//        Input: nums1 = [0,0], nums2 = [0,0]
//        Output: 0.00000
//        Example 4:
//
//        Input: nums1 = [], nums2 = [1]
//        Output: 1.00000
//        Example 5:
//
//        Input: nums1 = [2], nums2 = []
//        Output: 2.00000
//
//
//        Constraints:
//
//        nums1.length == m
//        nums2.length == n
//        0 <= m <= 1000
//        0 <= n <= 1000
//        1 <= m + n <= 2000
//        -106 <= nums1[i], nums2[i] <= 106
package leetcode;

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
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 题目已经说明nums1和nums2不能同时为空
        if (null == nums1 || nums1.length == 0) {
            return median(nums2);
        }
        if (null == nums2 || nums2.length == 0) {
            return median(nums1);
        }
        // TODO
        return 0d;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1 == null ? 0 : nums1.length;
        int size2 = nums2 == null ? 0 : nums2.length;
        int size = size1 + size2;
        boolean even = (size & 1) == 0; // 是否为偶数
        if (size1 != 0 && size2 != 0) {
            if (even) {
                // （上中位数（size/2） + 下中位数(size/2+1)） / 2D;
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, (size / 2) + 1)) / 2D;
            }
            // 第(size/2) 小的那个数
            return findKthNum(nums1, nums2, size / 2 + 1);
        } else if (size1 != 0) {
            if (even) {
                return (double) (nums1[size / 2] + nums1[(size - 1) / 2]) / 2D;
            }
            return nums1[size / 2];
        } else if (size2 != 0) {
            if (even) {
                return (double) (nums2[size / 2] + nums2[((size - 1) / 2)]) / 2D;
            }
            return nums2[size / 2];
        }
        return 0;
    }

    // 获取两个长度不等的有序数组中第K小的数（K从1开始算）
    // 已知：getUpMedian:获取两个长度相等的排序数组merge后的上中位数
    // 假设longs表示长数组，shorts表示短数组
    // kth的范围包括：
    // 第一种情况 k小于等于短数组的长度
    // longs 取前kth个数
    // shorts 取前kth个数
    // 调用getUpMedian函数，拿到中位数
    // 例如：
    // shorts [1,3,5,7]
    // longs  [2,4,6,8,10,12]
    // 取第2小的数,客观上，第2小的数是2，
    // 可能存在的范围是shorts的前2个数中，也可能存在在longs的前2个数中
    // 除此之外，不能是其他范围，因为超过这个范围，就不止第2小了。

    // 第2种情况，k大于短数组长度但k小于等于长数组长度
    // shorts都有可能
    // longs 排除掉 前(kth - shorts.len - 1) 个数，以及[kth + 1, longs.len]区间的所有数
    // 手动验证一下long中第(kth - shorts.len)位置上的数是不是比shorts最后一个数大，
    // 如果是，longs中第(kth - shorts.len)位置上的数即为第Kth小的数
    // 如果不是，longs中第(kth - shorts.len)位置上的数即为第Kth小的数直接排除掉
    // longs 中剩余数和 shorts中所有数用一次getUpMedian方法求得的值即为第kth小的数
    // 例如：
    //  shorts [1,3,5,7]
    //  longs  [2,4,6,8,10,12,14,16，18]
    // 求第7小的数，此时
    // shorts中的数都有可能是第6小的数，
    // 但是，longs中，可以排除如下位置的数
    // 1. 从第8小的数开始一直到第longs.len小的数都可以排除。
    // 2. 从 7 - shorts.len - 1 即：7 - 4 - 1 = 3 ，longs中第3小之前的数都可以排除，
    // 排除完毕后，验证一下longs中第3小的数是不是比shorts中最后一个数大，如果是，则longs中第3小的数即位两个数组的第7小的数。
    // 如果不是，则longs中剩余可选的数继续和shorts调用getUpMedian方法。
    // 第3种情况 k大于 长数组长度 但是k小于等于 长数组长度+短数组长度
    // shorts 中排除掉前面(kth - longs.len)个数
    // longs 中排除掉前( kth - shorts.len - 1) 个数
    // 手动判断下shorts和longs中剩余数中最左边的数是不是第kth小的数，如果是，直接返回，如果不是，排除掉这两个最左边的数
    // shorts和longs剩余数用getUpMedian获取的结果即为答案
    // 举例说明
    // shorts [1,3,5,7,9,11] 长度是6
    // longs [2,4,6,8,10,12,14,16,18,20,22,24] 长度是12
    // 求第15小的数
    // shorts中可以排除掉前面2个数（15 - longs.len - 1）= 2 因为即便shorts中第1小的数比longs中所有数都大，它也只能算第13小的数，
    // 第2小的数即便比longs中所有数都大，也只能算全局第14小的数。
    // longs中可以排除掉第1一直到第8小（即：15 - shorts.len  - 1 = 8）的数，因为longs中第8小的数即便比shorts数所有数都大，也只能是全局第14小的数。
    // 进过排除后，shorts中和longs中可选范围为
    // [x,x,5,7,9,11]
    // [x,x,x,x,x,x,x,x,18,20,22,24]
    // 先手动判断一下，longs中的18和shorts中的5是否是第15小的数，如果是直接返回，如果不是，
    // shorts中[7,9,11] 和 longs中 [20,22,24] 使用getUpMedian获取的结果即为答案
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = longs == arr1 ? arr2 : arr1;
        int S = shorts.length;
        int L = longs.length;
        if (kth <= S) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth <= L) {
            if (longs[kth - S - 1] >= shorts[S - 1]) {
                return longs[kth - S - 1];
            }
            return getUpMedian(longs, kth - S, kth - 1, shorts, 0, S - 1);
        }
        if (shorts[kth - L - 1] >= longs[L - 1]) {
            return shorts[kth - L - 1];
        }
        if (longs[kth - S - 1] >= shorts[S - 1]) {
            return longs[kth - S - 1];
        }
        return getUpMedian(shorts, kth - L, S - 1, longs, kth - S, L - 1);
    }

    // 获取两个长度相等的有序数组merge后的上中位数
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

    public static void main(String[] args) {
        int[] shorts = {1, 3, 5, 7};
        int[] longs = {2, 4, 6, 8, 10, 12};
        int s2 = findKthNum(shorts, longs, 2);
        int s1 = findKthNum(shorts, longs, 1);
        // int s0 = findKthNum(shorts, longs, 0);
        System.out.println(s2);
        System.out.println(s1);
        // System.out.println(s0);
    }
}