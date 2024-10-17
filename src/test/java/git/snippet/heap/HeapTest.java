package git.snippet.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

import static git.snippet.common.Generator.copyArray;
import static git.snippet.common.Generator.generateRandomArray;

@DisplayName("堆结构测试")
public class HeapTest {
    @Test
    @DisplayName("堆排序测试")
    public void heapSortTest() {
        int times = 500000; // 测试的次数
        int maxSize = 100; // 数组的最大长度是100
        int maxValue = 100; // 数组元素的大小[-100,100]
        for (int i = 0; i < times; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr4 = copyArray(arr1);
            HeapSort.sort(arr1);
            Arrays.sort(arr4);
            Assertions.assertArrayEquals(arr1, arr4);
        }
    }

    @Test
    @DisplayName("大根堆测试")
    public void testHeap() {
        int value = 10000;
        int limit = 100;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MaxHeap my = new MaxHeap(curLimit);
            PriorityQueue<Integer> test = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    Assertions.fail();
                }
                if (my.isFull() != (test.size() == curLimit)) {
                    Assertions.fail();
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.add(curValue);
                } else if (my.isFull()) {
                    if (!Objects.equals(my.pop(), test.poll())) {
                        Assertions.fail();
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.add(curValue);
                    } else {
                        if (!Objects.equals(my.pop(), test.poll())) {
                            Assertions.fail();
                        }
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("加强堆测试")
    public void testGreaterHeap() {
        int value = 1000;
        int limit = 100;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            HeapGreater<Integer> myHeap = new HeapGreater<>((o1, o2) -> o2 - o1);
            PriorityQueue<Integer> rightHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (rightHeap.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    myHeap.push(curValue);
                    rightHeap.add(curValue);
                } else if (rightHeap.size() == curLimit) {
                    if (!Objects.equals(myHeap.pop(), rightHeap.poll())) {
                        Assertions.fail();
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        myHeap.push(curValue);
                        rightHeap.add(curValue);
                    } else {
                        if (!Objects.equals(myHeap.pop(), rightHeap.poll())) {
                            Assertions.fail();
                        }
                    }
                }
            }
        }
    }

}