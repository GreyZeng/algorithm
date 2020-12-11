//题目描述
//        有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
//
//        给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
//
//        示例1
//        输入
//        复制
//        [1,3,5,2,2],5,3
//        返回值
//        复制
//        2
package nowcoder;

/**
 * @author: Grey
 * @date: 2020/12/11
 **/
// ref LeetCode_0215_KthLargestElementInAnArray
public class NowCoder_0088_FindKth {

    public int findKth(int[] a, int n, int K) {
        return p(a, 0, n - 1, K - 1);
    }

    // 如果要排序的话，请返回第K大的数
    public int p(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (K >= range[0] && K <= range[1]) {
            return arr[K];
        } else if (K < range[0]) {
            return p(arr, L, range[0] - 1, K);
        } else {
            return p(arr, range[1] + 1, R, K);
        }
    }

    private int[] partition(int[] arr, int l, int r, int pivot) {
        int s = l - 1;
        int e = r + 1;
        int index = l;
        while (index < e) {
            if (arr[index] > pivot) {
                swap(arr, index++, ++s);
            } else if (arr[index] < pivot) {
                swap(arr, index, --e);
            } else {
                index++;
            }
        }
        return new int[]{s + 1, e - 1};
    }

    public void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
