package 简单排序;

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
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
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

}
