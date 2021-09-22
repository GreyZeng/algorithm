package snippet;

import java.util.Arrays;
import java.util.Comparator;
// 长度为N的数组arr，一定可以组成N^2个数值对。
// 例如arr = [3,1,2]，
// 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
// 也就是任意两个数都有数值对，而且自己和自己也算数值对。
// 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。
// 所以上面的数值对排序的结果为：
// (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)

// 给定一个数组arr，和整数k，返回第k小的数值对。
/**
 * @author Young
 * @version 1.0
 * @date 2021/1/23 16:24
 */
public class Code_0015_KMinPair {
    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class MyComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
        }
    }

    public static int[] kthMinPair1(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        Pair[] pair = new Pair[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pair[index++] = new Pair(arr[i], arr[j]);
            }
        }
        Arrays.sort(pair, new MyComparator());
        return new int[]{pair[k - 1].x, pair[k - 1].y};
    }

    // O(N*logN)解法
    public static int[] kthMinPair2(int[] arr, int k) {
        // 第一维的数据 应该是arr中如果排序后，排在 (k-1)/n位置上的数
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        Arrays.sort(arr);
        int first = arr[(k - 1) / n];
        int less = 0;
        int equal = 0;
        for (int i = 0; i < n && arr[i] <= first; i++) {
            if (arr[i] < first) {
                less++;
            } else {
                equal++;
            }
        }
        int rest = k - (less * n);
        int second = arr[(rest - 1) / equal];
        return new int[]{first, second};
    }

    // O(N)解法
    public static int[] kthMinPair3(int[] arr, int k) {
        // 第一维的数据 应该是arr中如果排序后，排在 (k-1)/n位置上的数
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        Arrays.sort(arr);
        int first = getKMin(arr, (k - 1) / n);
        int less = 0;
        int equal = 0;
        for (int i = 0; i < n && arr[i] <= first; i++) {
            if (arr[i] < first) {
                less++;
            } else {
                equal++;
            }
        }
        int rest = k - (less * n);
        int second = getKMin(arr, (rest - 1) / equal);
        return new int[]{first, second};
    }

    private static int getKMin(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            int[] range = partition(arr, L, R, pivot);
            if (value > range[1]) {
                L = range[1] + 1;
            } else if (value < range[0]) {
                R = range[0] - 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
