package code;
 

/**
 * 插入排序
 * 过程：
 * 想让arr[0~0]上有序，这个范围只有一个数，当然是有序的。
 * 想让arr[0~1]上有序，所以从arr[1]开始往前看，如果arr[1]<arr[0]，就交换。否则什么也不做。
 * …
 * 想让arr[0~i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
 * 最后一步，想让arr[0~N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
 * <p>
 * 估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。
 */
// TODO 补对数器
public class Code_0001_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr != null && arr.length >= 2) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length < 2 || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
