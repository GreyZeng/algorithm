package grey.algorithm.code11_heap;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/16933830.html
// 1. 先让整个数组都变成大根堆结构，建立堆的过程:
// a. 从上到下的方法，时间复杂度为O(N*logN)
// b. 从下到上的方法，时间复杂度为O(N)
// 2. 把堆的最大值和堆末尾的值交换， 然后减少堆的大小之后，再去调整堆， 一直周而复始，时间复杂度为O(N*logN) 【扩两倍估算复杂度法】
// 3. 把堆的大小减小成0之后，排序完成
// 堆排序额外空间复杂度O(1)
// 测评：https://www.lintcode.com/problem/464
//测评链接：https://www.luogu.com.cn/problem/P1177
public class Code_0003_HeapSort {
    public static void heapSort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 大根堆
        // 此时，最大元素已经在0号位置
        int size = arr.length;
        while (size > 0) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    public static void heapSort2(int[] arr) {
        int n = arr.length;
        // O(N)
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        // 注意：这里要保存一个变量，因为n在循环里面会变化
        int size = n;
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int i, int size) {
        int l = 2 * i + 1;
        while (l < size) {
            int best = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
            best = arr[i] > arr[best] ? i : best;
            if (best == i) {
                break;
            }
            swap(arr, best, i);
            i = best;
            l = 2 * i + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        // 数组长度1~500，等概率随机
        int num = 500;
        // 每个值的大小在1~1024，等概率随机
        int value = 1024;
        // 测试次数
        int testTimes = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(num, value);
            int[] copyArray = copyArray(arr);
            int[] copyArray2 = copyArray(arr);
            Arrays.sort(arr);
            heapSort1(copyArray);
            heapSort2(copyArray2);
            if (!sameValue(arr, copyArray)) {
                System.out.println("出错了！");
                break;
            }
            if (!sameValue(arr, copyArray2)) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean sameValue(int[] arr1, int[] arr2) {
        if (null == arr1) {
            return null != arr2;
        }
        if (null == arr2) {
            return null != arr1;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateArray(int num, int value) {
        int[] arr = new int[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int[] copyArray = new int[arr.length];
        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = arr[i];
        }
        return copyArray;
    }

}
