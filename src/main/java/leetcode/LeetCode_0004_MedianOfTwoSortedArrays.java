package leetcode;

public class LeetCode_0004_MedianOfTwoSortedArrays {
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
            return (double) findKthNum(nums1, nums2, size / 2 + 1);
        } else if (size1 != 0) {
            if (even) {
                return (double) (nums1[size / 2] + nums1[(size - 1) / 2]) / 2D;
            }
            return (double) nums1[size / 2];
        } else if (size2 != 0) {
            if (even) {
                return (double) (nums2[size / 2] + nums2[((size - 1) / 2)]) / 2D;
            }
            return (double) nums2[size / 2];
        }
        return 0;

    }

    // 获取两个长度不等的数组中第K小的数
    // 已知：getUpMedian:获取两个长度相等的排序数组merge后的上中位数
    // kth的范围包括：
    // 1. <= 短数组的长度
    // long 取前kth个数
    // short 取前kth个数
    // 调用getUpMedian函数，拿到中位数
    //
    // 2. > 短数组长度 <= 长数组长度
    // short都有可能
    // long 排除掉 前(kth - short.len - 1) 个数，以及[kth + 1, long.len]区间的所有数
    // 手动验证一下long中第(kth - short.len)位置上的数是不是比short最后一个数大，
    // 如果是，long中第(kth - short.len)位置上的数即为第Kth小的数
    // 如果不是，long中第(kth - short.len)位置上的数即为第Kth小的数直接排除掉
    // long 中剩余数和 short中所有数用一次getUpMedian方法求得的值即为第kth小的数
    //
    // 3. > 长数组长度 <= 长数组长度+短数组长度
    // short 中排除掉前面(kth - long.len)个数
    // long 中排除掉前( kth - short.len - 1) 个数
    // 手动判断下short和long中剩余数中最左边的数是不是第kth小的数，如果是，直接返回，如果不是，排除掉这两个最左边的数
    // short和long剩余数用getUpMedian
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = longs == arr1 ? arr2 : arr1;

        int S = shorts.length;
        int L = longs.length;
        if (kth <= S) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > S && kth <= L) {
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

    // 获取两个长度相等的排序数组merge后的上中位数
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