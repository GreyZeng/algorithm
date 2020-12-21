package code;

public class Code_0019_BinarySearch {

    // 在一个有序数组中，找某个数是否存在
    public static boolean exist(int[] sortedArr, int num) {
        if (null == sortedArr || sortedArr.length == 0) {
            return false;
        }
        int N = sortedArr.length;
        int L = 0;
        int R = N - 1;
        int M = 0;
        while (L < R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] == num) {
                return true;
            } else if (sortedArr[M] > num) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return sortedArr[L] == num;
    }

    // 在一个有序数组中，找>=某个数的最左位置
    public static int nearestLeft(int[] sortedArr, int num) {
        if (null == sortedArr || sortedArr.length == 0) {
            return -1;
        }
        int N = sortedArr.length;
        int L = 0;
        int R = N - 1;
        int index = -1;
        while (L <= R) {
            int M = (L + ((R - L) >> 1));
            if (sortedArr[M] >= num) {
                index = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return index;
    }

    // 在一个有序数组中，找<=某个数的最右位置
    public static int nearestRight(int[] sortedArr, int num) {
        if (null == sortedArr || sortedArr.length == 0) {
            return -1;
        }
        int N = sortedArr.length;
        int L = 0;
        int R = N - 1;
        int index = -1;
        while (L <= R) {
            int M = (L + ((R - L) >> 1));
            if (sortedArr[M] <= num) {
                index = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return index;
    }


    // 局部最小值问题
    public static int getLessIndex(int[] arr) {
        if (null == arr || arr.length == 0) {
            return -1;
        }

        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }
        int L = 1;
        int R = N - 2;
        int M = -1;
        while (L <= R) {
            M = (L + ((R - L) >> 1));
            if (arr[M] > arr[M + 1]) {
                L = M + 1;
            } else if (arr[M] > arr[M - 1]) {
                R = M - 1;
            } else {
                return M;
            }
        }
        return M;
    }
}
