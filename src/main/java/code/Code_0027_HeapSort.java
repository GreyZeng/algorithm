package code;


import java.util.Arrays;

public class Code_0027_HeapSort {
    public static void sort(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return;
        }
        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            heapify(arr, i, N);
        }
        while (N > 0) {
            heapify(arr, 0, N);
            swap(arr, --N, 0);
        }
    }


    public static void heapify(int[] arr, int i, int n) {
        int leftChild = 2 * i + 1;
        while (leftChild < n) {
            int largest = leftChild + 1 < n ? Math.max(arr[leftChild], arr[leftChild + 1]) : arr[leftChild];
            if (largest <= arr[i]) {
                break;
            }
            largest = largest == arr[leftChild] ? leftChild : leftChild + 1;
            swap(arr, largest, i);
            i = largest;
            leftChild = 2 * i + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

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

    private static void printArray(int[] arr1) {
        if (null == arr1) {
            return;
        }
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null) {
            return false;
        }
        if (arr2 == null) {
            return false;
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

    private static int[] copyArray(int[] arr1) {
        if (arr1 == null) {
            return null;
        }
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
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
            sort(arr1);
            absRight(arr2);
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






