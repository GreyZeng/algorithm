package lintcode;

// 简单版
//Given two sorted integer arrays A and B, merge B into A as one sorted array.

// You may assume that A has enough space
//  (size that is greater or equal to m + n) to hold additional elements from B. 
// The number of elements initialized in A and B are m and n respectively.
public class LintCode_0064_MergeTwoSortedArrays {
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int[] helper = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (A[i] > B[j]) {
                helper[index++] = B[j++];
            } else {
                helper[index++] = A[i++];
            }
        }
        while (i < m) {
            helper[index++] = A[i++];
        }
        while (j < n) {
            helper[index++] = B[j++];
        }

        for (int k = 0; k < (m + n); k++) {
            A[k] = helper[k];
        }
    }
}
