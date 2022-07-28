package snippet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static snippet.SimpleSortMethod.bubbleSort;
import static snippet.SimpleSortMethod.insertionSort;
import static snippet.SimpleSortMethod.selectionSort;

class SimpleSortMethodTest {

    // for test
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

    private static int[] copyArray(int[] arr1) {
        if (arr1 == null) {
            return null;
        }
        int[] arr2 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        return arr2;
    }

    @Test
    public void test() {
        int times = 500000; // 测试的次数
        int maxSize = 100; // 数组的最大长度是100
        int maxValue = 100; // 数组元素的大小[-100,100]
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            bubbleSort(arr1);
            selectionSort(arr2);
            insertionSort(arr3);
            absRight(arr4);
            Assertions.assertArrayEquals(arr1, arr4);
            Assertions.assertArrayEquals(arr2, arr4);
            Assertions.assertArrayEquals(arr3, arr4);
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}