package snippet;

import java.util.Arrays;

/**
 * 插入排序
 * 冒泡排序
 * 选择排序
 *
 * @author Grey
 * @see <a href="https://www.cnblogs.com/greyzeng/p/15186769.html">简单排序（冒泡排序，插入排序，选择排序）</a>
 */
public class Code_0001_Sort {

    /**
     * <b>插入排序</b>流程
     * <p>
     * 想让arr[0...0]上有序，这个范围只有一个数，当然是有序的。
     * <p>
     * 想让arr[0...1]上有序，所以从arr[1]开始往前看，如果arr[1] < arr[0]，就交换。否则什么也不做。
     * <p>
     * 依此类推……
     * <p>
     * 想让arr[0...i]上有序，所以从arr[i]开始往前看，arr[i]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
     * <p>
     * 最后一步，
     * <p>
     * 想让arr[0...N-1]上有序， arr[N-1]这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
     * <p>
     * 估算时<b>发现这个算法流程的复杂程度，会因为数据状况的不同而不同</b>。
     * <p>
     * 如果数组本身是有序的，那么插入排序的过程不需要移动任何数字，
     * <p>
     * 但是时间复杂度是以最坏情况估计，所以插入排序的时间复杂度仍然是<code>O(N^2)</code>
     *
     * @param arr 未排序数组
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j + 1, j);
                }
            }
        }
    }

    /**
     * 选择排序
     * <p>
     * arr[0...N-1]范围上，找到最小值所在的位置，然后把最小值交换到0位置；
     * <p>
     * arr[1...N-1]范围上，找到最小值所在的位置，然后把最小值交换到1位置；
     * <p>
     * arr[2...N-1]范围上，找到最小值所在的位置，然后把最小值交换到2位置；
     * <p>
     * 依此类推……
     * <p>
     * arr[N-1...N-1]范围上，找到最小值位置，然后把最小值交换到N-1位置；
     * <p>
     * 所以选择排序的时间复杂度为<code>O(N^2)</code>
     *
     * @param arr 未排序数组
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[j] < arr[min] ? j : min;
            }
            swap(arr, i, min);
        }
    }

    /**
     * 冒泡排序
     * <p>
     * 在arr[0...N-1]范围上：
     * <p>
     * arr[0]和arr[1]，谁大谁来到1位置；
     * <p>
     * arr[1]和arr[2]，谁大谁来到2位置；
     * <p>
     * 依此类推……
     * <p>
     * arr[N-2]和arr[N-1]，谁大谁来到第N-1个位置上；
     * <p>
     * 在arr[0...N-2]范围上，重复上面的过程，但最后一步是arr[N-3]和arr[N-2]，谁大谁来到第N-2个位置上；
     * <p>
     * 在arr[0...N-3]范围上，重复上面的过程，但最后一步是arr[N-4]和arr[N-3]，谁大谁来到第N-3个位置上；
     * <p>
     * 依此类推……
     * <p>
     * 最后在arr[0...1]范围上，重复上面的过程，但最后一步是arr[0]和arr[1]，谁大谁来到第一个位置上；
     * <p>
     * 时间复杂度<code>O(N^2)</code>
     *
     * @param arr 未排序数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
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

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    // for test
    public static void absRight(int[] arr) {
        Arrays.sort(arr);
    }

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
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            bubbleSort(arr1);
            selectionSort(arr2);
            insertionSort(arr3);
            absRight(arr4);
            if (!Arrays.equals(arr1, arr4) || !Arrays.equals(arr2, arr4) || !Arrays.equals(arr3, arr4)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
