package lintcode.easy;

// How can you optimize your algorithm if one array is very large and the other is very small?
// https://www.lintcode.com/problem/6/
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
public class LintCode_0006_MergeTwoSortedArrays {
    // 常规解法
    public static int[] mergeSortedArray1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] res = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (A[i] > B[j]) {
                res[index++] = B[j++];
            } else {
                res[index++] = A[i++];
            }
        }
        while (i < m) {
            res[index++] = A[i++];
        }

        while (j < n) {
            res[index++] = B[j++];
        }
        return res;
    }

    // 如果一个数组很大，另一个数组很小优化算法
    public static int[] mergeSortedArray(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] bigger = m >= n ? A : B;
        int[] smaller = bigger == A ? B : A;
        int[] helper = new int[m + n];
        int from = 0;
        int to;
        int index = 0;
        for (int i = 0; i < smaller.length; i++) {
            int position = position(smaller[i], bigger, i);
            helper[position] = smaller[i];
            to = position - 1;
            while (from <= to) {
                helper[from++] = bigger[index++];
            }
            from = position + 1;
        }
        while (from < (m + n)) {
            helper[from++] = bigger[index++];
        }
        return helper;
    }

    // value在bigger的位置是多少
    public static int position(int value, int[] bigger, int offset) {
        int smallerThanMe = 0;
        int L = 0;
        int R = bigger.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (bigger[mid] > value) {
                R = mid - 1;
            } else if (bigger[mid] < value) {
                smallerThanMe = (mid + 1);
                L = mid + 1;
            } else {
                smallerThanMe = mid;
                R = mid - 1;
            }
        }
        return smallerThanMe + offset;
    }

}
