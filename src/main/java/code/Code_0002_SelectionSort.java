package code;


/**
 * 选择排序
 * 过程：
 * arr[0～N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置。
 * arr[1～N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置。
 * arr[2～N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置。
 * …
 * arr[N-1～N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置。
 * <p>
 * 估算：
 * 很明显，如果arr长度为N，每一步常数操作的数量，如等差数列一般
 * 所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
 * <p>
 * 所以选择排序的时间复杂度为O(N^2)。
 */
public class Code_0002_SelectionSort {


    public static void selectionSort(int[] arr) {
        if (null != arr && arr.length >= 2) {
            for (int i = 0; i < arr.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < arr.length; j++) {
                    min = arr[j] < arr[min] ? j : min;
                }
                swap(arr, i, min);
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length < 2 || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
