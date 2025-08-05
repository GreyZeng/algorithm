package grey.algorithm;

import java.util.Arrays;

//冒泡排序：0 ~ i范围上，相邻位置较大的数滚下去，最大值最终来到i位置，然后0~i-1范围上继续
//笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
public class Code_0003_BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            // 每次搞定i位置的数
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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
            bubbleSort(copyArray);
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
