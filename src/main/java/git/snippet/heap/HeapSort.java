package git.snippet.heap;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 1. 先让整个数组都变成大根堆结构，建立堆的过程:
// a. 从上到下的方法，时间复杂度为O(N*logN)
// b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 【扩两倍估算复杂度法】
// 3. 把堆的大小减小成0之后，排序完成
// 堆排序额外空间复杂度O(1)
// 测评：https://www.lintcode.com/problem/464
public class HeapSort {
    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n); // 构造大根堆
        }
        while (n > 0) {
            heapify(arr, 0, n);
            swap(arr, 0, --n);
        }
    }

    public static void heapify(int[] arr, int i, int n) {
        int j = i * 2 + 1;
        while (j < n) {
            int max = j + 1 < n && arr[j + 1] > arr[j] ? j + 1 : j;
            max = arr[max] > arr[i] ? max : i;
            if (max == i) {
                break;
            }
            swap(arr, max, i);
            i = max;
            j = i * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    public static void main(String[] args) {
        heapSortTest();
    }
    public static void heapSortTest() {
        System.out.println("test start");
        int times = 500000; // 测试的次数
        int maxSize = 100; // 数组的最大长度是100
        int maxValue = 100; // 数组元素的大小[-100,100]
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr4 = copyArray(arr1);
            HeapSort.sort(arr1);
            Arrays.sort(arr4);
            if(!arrayEquals(arr4, arr1)) {
                System.out.println("error");
                break;
            }
//            Assertions.assertArrayEquals(arr1, arr4);
        }
        System.out.println("test end");
    }
    public static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (null == arr1){
            return arr2 == null;
        }
        int l1 = arr1.length;
        int l2 = arr2.length;
        if (l1 != l2) {
            return false;
        }
        for (int i = 0; i < l1; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static int[] copyArray(int[] arr1) {
        if (arr1 == null) {
            return null;
        }
        int[] arr2 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        return arr2;
    }
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
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
}
