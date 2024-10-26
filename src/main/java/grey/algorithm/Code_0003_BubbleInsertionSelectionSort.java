package grey.algorithm;

// 笔记：https://www.cnblogs.com/greyzeng/p/15186769.html
public class Code_0003_BubbleInsertionSelectionSort {

    // 冒泡排序
    // 在 arr[0...N-1] 范围上：
    // arr[0] 和 arr[1]，谁大谁来到 1 号位置；
    // arr[1] 和 arr[2]，谁大谁来到 2 号位置；
    // 依此类推……
    // arr[N-2] 和 arr[N-1]，谁大谁来到第 N-1 号位置上；
    // 在 arr[0...N-2] 范围上，重复上面的过程，但最后一步是 arr[N-3] 和 arr[N-2] ，谁大谁来到第 N-2 号位置上；
    // 在 arr[0...N-3] 范围上，重复上面的过程，但最后一步是 arr[N-4] 和 arr[N-3]，谁大谁来到第 N-3 号位置上；
    // 依此类推……
    // 最后在 arr[0...1] 范围上，重复上面的过程，但最后一步是 arr[0] 和 arr[1]，谁大谁来到第 1 号位置上；
    public static void bubbleSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
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

    // 插入排序
    // 想让 arr[0...0] 上有序，这个范围只有一个数，当然是有序的。
    // 想让 arr[0...1] 上有序，所以从 arr[1] 开始往前看，如果 arr[1] < arr[0]，就交换。否则什么也不做。
    // 依此类推……
    // 想让 arr[0...i] 上有序，所以从 arr[i] 开始往前看，arr[i] 这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
    // 最后一步，
    // 想让 arr[0...N-1] 上有序，arr[N-1] 这个数不停向左移动，一直移动到左边的数字不再比自己大，停止移动。
    // 估算时发现这个算法流程的复杂程度，会因为数据状况的不同而不同。如果数组本身是有序的，那么插入排序的过程不需要移动任何数字，
    // 但是时间复杂度是以最坏情况估计，所以插入排序的时间复杂度仍然是 O(N^2)。
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

    // 选择排序
    //    arr[0...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 0 号位置；
    //    arr[1...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 1 号位置；
    //    arr[2...N-1] 范围上，找到最小值所在的位置，然后把最小值交换到 2 号位置；
    //    依此类推……
    //    arr[N-1...N-1] 范围上，找到最小值位置，然后把最小值交换到 N-1 号位置；
    public static void selectionSort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
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
    public static void main(String[] args) {
        // 数组长度1~500，等概率随机
        int num = 500;
        // 每个值的大小在1~1024，等概率随机
        int value = 1024;
        // 测试次数
        int testTimes = 50000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * num);
            int[] arr = randomArray(n, value);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            selectionSort(arr1);
            bubbleSort(arr2);
            insertionSort(arr3);
            if (!sameArray(arr1, arr2) || !sameArray(arr1, arr3)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean sameArray(int[] arr1, int[] arr2) {
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

    private static int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    private static int[] randomArray(int n, int value) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }
}