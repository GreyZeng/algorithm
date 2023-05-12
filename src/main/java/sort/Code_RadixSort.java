package sort;

import java.util.Arrays;

// 笔记：https://www.cnblogs.com/greyzeng/p/16929142.html

/**
 * 基数排序 一般来讲，基数排序要求，样本是10进制的正整数, 流程如下
 *
 * <p>
 * 1. 找到最大值，这个最大值是几位的
 *
 * <p>
 * 2. 其他数不足这个位数的，用0补齐
 *
 * <p>
 * 3. 准备10个桶，每个桶是队列
 *
 * <p>
 * 4. 从个位依次进桶
 *
 * <p>
 * 5. 然后依次倒出
 *
 * <p>
 * 6. 根据十位数进桶
 *
 * <p>
 * 7. 依次倒出 .......
 *
 * <p>
 * 1) 桶排序思想下的排序都是不基于比较的排序
 *
 * <p>
 * 2) 时间复杂度为O(N)，额外空间复杂度O(M)
 *
 * <p>
 * 3) 应用范围有限，需要样本的数据状况满足桶的划分
 */
public class Code_RadixSort {

    // 非负数
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        // 最大值有几位
        int bits = 0;
        while (max != 0) {
            bits++;
            max /= 10;
        }
        // 存排序后的数组
        int[] help = new int[arr.length];
        for (int bit = 1; bit <= bits; bit++) {
            int[] count = new int[10];
            for (int num : arr) {
                count[digit(num, bit)]++;
            }
            // 前缀和
            for (int j = 1; j < 10; j++) {
                count[j] = count[j - 1] + count[j];
            }
            // 倒序遍历数组
            for (int i = arr.length - 1; i >= 0; i--) {
                int pos = digit(arr[i], bit);
                help[--count[pos]] = arr[i];
            }
            int m = 0;
            for (int num : help) {
                arr[m++] = num;
            }
        }
    }

    // 获取某个数在某一位上的值
    // 从1开始，从个位开始
    // 例如 num = 803，digit = 3，以下方法输出 8，即：803 的第三位（百位）上是8
    public static int digit(int num, int digit) {
        return ((num / (int) Math.pow(10, digit - 1)) % 10);
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
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
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
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        // int[] arr = generateRandomArray(maxSize, maxValue);
        // printArray(arr);
        // radixSort(arr);
        // printArray(arr);

    }
}
