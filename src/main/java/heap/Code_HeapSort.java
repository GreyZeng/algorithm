package heap;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 1. 先让整个数组都变成大根堆结构，建立堆的过程:
// a. 从上到下的方法，时间复杂度为O(N*logN)
// b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 【扩两倍估算复杂度法】
// 3. 把堆的大小减小成0之后，排序完成
// 堆排序额外空间复杂度O(1)
// 测评：https://www.lintcode.com/problem/464
public class Code_HeapSort {
  public static void heapSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // O(N*logN)
    // for (int i = 0; i < arr.length; i++) { // O(N)
    // heapInsert(arr, i); // O(logN)
    // }
    // O(N)
    for (int i = arr.length - 1; i >= 0; i--) {
      heapify(arr, i, arr.length);
    }
    int heapSize = arr.length;
    swap(arr, 0, --heapSize);
    // O(N*logN)
    while (heapSize > 0) { // O(N)
      heapify(arr, 0, heapSize); // O(logN)
      swap(arr, 0, --heapSize); // O(1)
    }
  }

  // arr[index]刚来的数，往上
  public static void heapInsert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  // 构造大根堆
  // arr[index]位置的数，能否往下移动
  public static void heapify(int[] arr, int index, int heapSize) {
    int left = index * 2 + 1;
    while (left < heapSize) {
      int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
      largest = arr[largest] > arr[index] ? largest : index;
      if (largest == index) {
        break;
      }
      swap(arr, largest, index);
      index = largest;
      left = index * 2 + 1;
    }
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // for test
  public static void comparator(int[] arr) {
    Arrays.sort(arr);
  }

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  // for test
  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  // for test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  // for test
  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // for test
  public static void main(String[] args) {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      heapSort(arr1);
      comparator(arr2);
      if (!isEqual(arr1, arr2)) {
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
