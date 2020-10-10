package note;


/**
 * 冒泡排序
 * 过程：
 * 在arr[0～N-1]范围上：
 * arr[0]和arr[1]，谁大谁来到1位置；arr[1]和arr[2]，谁大谁来到2位置…arr[N-2]和arr[N-1]，谁大谁来到N-1位置
 * <p>
 * 在arr[0～N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到N-2位置
 * 在arr[0～N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到N-3位置
 * …
 * 最后在arr[0～1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到1位置
 * <p>
 * <p>
 * 估算：
 * 很明显，如果arr长度为N，每一步常数操作的数量，依然如等差数列一般
 * 所以，总的常数操作数量 = a*(N^2) + b*N + c (a、b、c都是常数)
 * <p>
 * 所以冒泡排序的时间复杂度为O(N^2)。
 */
// TODO 补对数器
public class Code_0003_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr != null && arr.length >= 2) {
            for (int i = arr.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        if (array == null || array.length < 2 || i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



}
