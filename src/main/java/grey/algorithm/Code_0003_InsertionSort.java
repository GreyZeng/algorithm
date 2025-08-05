package grey.algorithm;

import java.util.Arrays;

// 插入排序：0~i范围上已经有序，新来的数从右到左滑到不再小的位置插入，然后继续
//笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
public class Code_0003_InsertionSort {

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
        if (null == arr || arr.length <= 1) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
            Arrays.sort(arr);
            insertionSort(copyArray);
            if (!sameValue(arr, copyArray)) {
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
