package resolved.sort;

import java.util.Arrays;

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
        int min = 0;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int k = i + 1; k < arr.length; k++) {
                if (arr[k] < arr[min]) {
                    min = k;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
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
            selectionSort(arr1);
            Arrays.sort(arr2);
            if (!Arrays.equals(arr2, arr1)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
