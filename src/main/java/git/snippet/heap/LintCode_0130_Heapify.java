package git.snippet.heap;

// 最小堆，每个元素A[i]，我们将得到A[i * 2 + 1] >= A[i]和A[i * 2 + 2] >= A[i]
// O(n)的时间复杂度完成堆化
// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// https://www.lintcode.com/problem/130/
public class LintCode_0130_Heapify {
    public void heapify(int[] a) {
        for (int index = a.length - 1; index >= 0; index--) {
            int i = index;
            int leftChildIndex = 2 * i + 1;
            while (leftChildIndex < a.length) {
                int minIndex = leftChildIndex + 1 < a.length && a[leftChildIndex] > a[leftChildIndex + 1] ? leftChildIndex + 1 : leftChildIndex;
                minIndex = a[i] < a[minIndex] ? i : minIndex;
                if (minIndex == i) {
                    break;
                }
                swap(a, i, minIndex);
                i = minIndex;
                leftChildIndex = 2 * i + 1;
            }
        }
    }
    public void swap(int[] arr, int i, int j) {
        if (i != j && arr != null && arr.length > 1) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
