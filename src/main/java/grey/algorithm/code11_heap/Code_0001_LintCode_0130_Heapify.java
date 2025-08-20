package grey.algorithm.code11_heap;

// 最小堆，每个元素A[i]，我们将得到A[i * 2 + 1] >= A[i]和A[i * 2 + 2] >= A[i]
// O(n)的时间复杂度完成堆化
// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// https://www.lintcode.com/problem/130/
//左孩子 2 * i + 1
//右孩子 2 * i + 2
//父节点 （i - 1）/ 2
public class Code_0001_LintCode_0130_Heapify {

    public void heapify(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = i;
            int left = 2 * index + 1;
            while (left < arr.length) {
                int best = left + 1 < arr.length && arr[left + 1] < arr[left] ? left + 1 : left;
                if (arr[best] >= arr[index]) {
                    break;
                }
                swap(arr, best, index);
                index = best;
                left = 2 * index + 1;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
