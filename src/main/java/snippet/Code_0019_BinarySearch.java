package snippet;

public class Code_0019_BinarySearch {
   
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
}
