package sort;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
// 流程如下
// 想让 arr[0...0] 上有序，这个范围只有一个数，当然是有序的。
// 想让 arr[0...1] 上有序，所以从 arr[1] 开始往前看，如果 arr[1] < arr[0]，就交换。否则什么也不做。
// 依此类推……
// 想让 arr[0...i] 上有序，所以从 arr[i] 开始往前看，arr[i] 这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
// 最后一步，
// 想让 arr[0...N-1] 上有序，arr[N-1] 这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
// 估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。如果数组本身是有序的，那么插入排序的过程不需要移动任何数字，
// 但是时间复杂度是以最坏情况估计，所以插入排序的时间复杂度仍然是 O(N^2)。
public class Code_InsertionSort {

    public static void insertionSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
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

            int[] arr3 = copyArray(arr1);
            insertionSort(arr3);
            Arrays.sort(arr1);
            if (!Arrays.equals(arr1, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
