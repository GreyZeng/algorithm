package git.snippet.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static git.snippet.sort.Code_InsertionSort.insertionSort;
import static git.snippet.common.Generator.copyArray;
import static git.snippet.common.Generator.generateRandomArray;

@DisplayName("插入排序")
public class Code_InsertionSortTest {

    @Test
    public void insertionSortTest() {
        int times = 500000; // 测试的次数
        int maxSize = 100; // 数组的最大长度是100
        int maxValue = 100; // 数组元素的大小[-100,100]
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr4 = copyArray(arr1);
            insertionSort(arr1);
            Arrays.sort(arr4);
            Assertions.assertArrayEquals(arr1, arr4);
        }
    }
}