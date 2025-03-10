package grey.algorithm;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/16928076.html

/**
 * 计数排序 一般来讲，计数排序要求，样本是整数，且范围比较窄 桶排序思想下的排序：计数排序 & 基数排序
 *
 * <p>1)桶排序思想下的排序都是不基于比较的排序
 *
 * <p>2)时间复杂度为O(N)，额外空间负载度O(M)
 *
 * <p>3)应用范围有限，需要样本的数据状况满足桶的划分
 */
// TODO
public class Code_0037_CountSort {

    // arr必须非负数，如果含有负数，必须每个元素先减去最小的那个负数，然后执行下述排序
    // 排好以后，各项再加上那个最小的负数
    public static void sort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] help = new int[max + 1];
        for (int n : arr) {
            help[n]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i] != 0) {
                help[i]--;
                arr[index++] = i;
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            sort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
