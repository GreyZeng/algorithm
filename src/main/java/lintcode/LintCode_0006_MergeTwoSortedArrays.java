package lintcode;

// How can you optimize your algorithm if one array is very large and the other is very small?
public class LintCode_0006_MergeTwoSortedArrays {
    // TODO 

    public int[] mergeSortedArray(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] helper = new int[m + n];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < m && j < n) {
            if (A[i] > B[j]) {
                helper[index++] = B[j++];
            } else if(A[i] < B[j]) {
                helper[index++] = A[i++];
            } else {
                if (m > n) {
                    // n小就先拷贝A[j]，使得更快跳出循环
                    helper[index++] = B[j++];
                } else {
                    // m小就先拷贝i，使得更快跳出循环
                    helper[index++] = A[i++];
                }
            }
        }
        while (i < m) {
            helper[index++] = A[i++];
        }
        while (j < n) {
            helper[index++] = B[j++];
        }
        return helper;
    }
    
}
