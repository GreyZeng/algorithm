package git.snippet.sort;

// 笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
// 选择排序
//    arr[0...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 0 号位置；
//    arr[1...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 1 号位置；
//    arr[2...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 2 号位置；
//    依此类推……
//    arr[N-1...N-1] 范围上，找到最小值位置，然后把最小值交换到 N-1 号位置；
public class Code_SelectionSort {

    public static void selectionSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
