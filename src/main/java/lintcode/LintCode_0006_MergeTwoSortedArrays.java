package lintcode;

// How can you optimize your algorithm if one array is very large and the other is very small?
public class LintCode_0006_MergeTwoSortedArrays {
    public static int[] mergeSortedArray(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] bigger = m >= n ? A : B;
        int[] smaller = bigger == A ? B : A;
        int[] helper = new int[m + n];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < smaller.length; i++) {
            helper[position(smaller[i], bigger, i)] = smaller[i];
        }
        fillBigger(helper, bigger);
        return helper;
    }

    public static int position(int value, int[] A, int offset) {
        int smallerThanMe = 0;
        int L = 0;
        int R = A.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (A[mid] > value) {
                R = mid - 1;
            } else if (A[mid] < value) {
                smallerThanMe = (mid + 1);
                L = mid + 1;
            } else {
                smallerThanMe = mid;
                R = mid - 1;
            }
        }
        // System.out.println(value + " 在A中有"+(smallerThanMe) +"个数比他小，它自己的偏移量是 " +
        // offset);
        return smallerThanMe + offset;
    }

    public static void fillBigger(int[] helper, int[] A) {
        int index = 0;
        for (int i = 0; i < helper.length; i++) {
            if (helper[i] == Integer.MIN_VALUE) {
                helper[i] = A[index++];
            }
        }
    }

    public static void printArr(int[] arr) {
        for (int n : arr) {
            System.out.print(n + "  ");

        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = { 1, 5 };
        int[] B = { 2, 3 };
        int[] R = mergeSortedArray(A, B);
        printArr(R);
    }

}
