package resolved.sort;

import java.util.Arrays;

// 冒泡排序
// 笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
// 在 arr[0...N-1] 范围上：
// arr[0] 和 arr[1]，谁大谁来到 1 号位置；
// arr[1] 和 arr[2]，谁大谁来到 2 号位置；
// 依此类推……
// arr[N-2] 和 arr[N-1]，谁大谁来到第 N-1 号位置上；
// 在 arr[0...N-2] 范围上，重复上面的过程，但最后一步是 arr[N-3] 和 arr[N-2] ，谁大谁来到第 N-2 号位置上；
// 在 arr[0...N-3] 范围上，重复上面的过程，但最后一步是 arr[N-4] 和 arr[N-3]，谁大谁来到第 N-3 号位置上；
// 依此类推……
// 最后在 arr[0...1] 范围上，重复上面的过程，但最后一步是 arr[0] 和 arr[1]，谁大谁来到第 1 号位置上；
public class Code_BubbleSort {

  public static void bubbleSort(int[] arr) {
    if (null == arr || arr.length <= 1) {
      return;
    }
    for (int i = arr.length - 1; i >= 0; i--) {
      // 以下每轮循环搞定一个i位置
      for (int k = 0; k < i; k++) {
        if (arr[k] > arr[k + 1]) {
          swap(arr, k, k + 1);
        }
      }
    }
  }

  public static void swap(int[] arr, int i, int j) {
    if (i != j) {
      arr[i] = arr[i] ^ arr[j];
      arr[j] = arr[i] ^ arr[j];
      arr[i] = arr[i] ^ arr[j];
    }
  }

  // for test
  private static int[] generateRandomArray(int maxSize, int maxValue) {
    // Math.random() -> [0,1)
    // Math.random() * N -> [0,N)
    // (int)(Math.random()*N) -> [0,N-1]
    int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
    for (int i = 0; i < arr.length; i++) {
      // [-? , +?]
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
    }
    return arr;
  }

  private static int[] copyArray(int[] arr1) {
    if (arr1 == null) {
      return null;
    }
    int[] arr2 = new int[arr1.length];
    System.arraycopy(arr1, 0, arr2, 0, arr1.length);
    return arr2;
  }

  public static void main(String[] args) {
    int times = 500000; // 测试的次数
    int maxSize = 100; // 数组的最大长度是100
    int maxValue = 100; // 数组元素的大小[-100,100]
    boolean succeed = true;
    for (int i = 0; i < times; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr4 = copyArray(arr1);
      bubbleSort(arr1);
      Arrays.sort(arr4);
      if (!Arrays.equals(arr1, arr4)) {
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
