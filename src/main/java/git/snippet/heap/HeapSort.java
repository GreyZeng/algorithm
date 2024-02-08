package git.snippet.heap;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 1. 先让整个数组都变成大根堆结构，建立堆的过程:
// a. 从上到下的方法，时间复杂度为O(N*logN)
// b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 【扩两倍估算复杂度法】
// 3. 把堆的大小减小成0之后，排序完成
// 堆排序额外空间复杂度O(1)
// 测评：https://www.lintcode.com/problem/464
public class HeapSort {
    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n); // 构造大根堆
        }
        while (n > 0) {
            heapify(arr, 0, n);
            swap(arr, 0, --n);
        }
    }

    public static void heapify(int[] arr, int i, int n) {
        int j = i * 2 + 1;
        while (j < n) {
            int max = j + 1 < n && arr[j + 1] > arr[j] ? j + 1 : j;
            max = arr[max] > arr[i] ? max : i;
            if (max == i) {
                break;
            }
            swap(arr, max, i);
            i = max;
            j = i * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
