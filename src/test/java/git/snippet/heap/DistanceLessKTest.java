package git.snippet.heap;

import git.snippet.common.Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("几乎有序的数组排序测试")
public class DistanceLessKTest {

    @Test
    @DisplayName("几乎有序的数组排序测试")
    public void sortedArrDistanceLessK() {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = Generator.randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = Generator.copyArray(arr);
            int[] arr2 = Generator.copyArray(arr);
            DistanceLessK.sortedArrDistanceLessK(arr1, k);
            Arrays.sort(arr2);
            Assertions.assertArrayEquals(arr1, arr2);
        }
    }
}