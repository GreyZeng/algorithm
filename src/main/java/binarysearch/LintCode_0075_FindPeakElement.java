package binarysearch;

// https://www.lintcode.com/problem/75/
public class LintCode_0075_FindPeakElement {

    public int findPeak(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] > arr[1] ? 0 : 1;
        }
        // arr contains more than 3 elements
        if (arr[0] > arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > arr[m - 1] && arr[m] > arr[m + 1]) {
                return m;
            } else if (arr[m] > arr[m - 1]) {
                // arr[m] <= arr[m+1]
                l = m + 1;
            } else {
                // arr[m] <= arr[m-1]
                r = m - 1;
            }
        }
        return -1;
    }


}
