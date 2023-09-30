package heap;

// 最小堆，每个元素A[i]，我们将得到A[i * 2 + 1] >= A[i]和A[i * 2 + 2] >= A[i]
// O(n)的时间复杂度完成堆化
// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// https://www.lintcode.com/problem/130/
public class LintCode_0130_Heapify {
  public void heapify(int[] a) {
    if (null == a || a.length <= 1) {
      return;
    }
    for (int i = a.length - 1; i >= 0; i--) {
      heapify(a, i, a.length);
    }
  }

  public void heapify(int[] arr, int i, int len) {
    int leftChildIndex = 2 * i + 1;
    while (leftChildIndex < len) {
      int min =
          leftChildIndex + 1 < len && arr[leftChildIndex + 1] < arr[leftChildIndex]
              ? leftChildIndex + 1
              : leftChildIndex;
      min = arr[min] > arr[i] ? i : min;
      if (min == i) {
        break;
      }
      swap(arr, min, i);
      i = min;
      leftChildIndex = 2 * min + 1;
    }
  }

  public void swap(int[] arr, int i, int j) {
    if (i != j) {
      arr[i] = arr[i] ^ arr[j];
      arr[j] = arr[i] ^ arr[j];
      arr[i] = arr[i] ^ arr[j];
    }
  }
}
