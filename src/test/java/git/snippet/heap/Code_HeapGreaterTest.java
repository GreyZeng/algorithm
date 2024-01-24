package git.snippet.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("加强堆结构测试")
public class Code_HeapGreaterTest {


    // 加强堆测试
    @Test
    @DisplayName("加强堆结构测试")
    public void testGreaterHeap() {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            Code_HeapGreater<Integer> my =
                    new Code_HeapGreater<>(
                            (o1, o2) -> o2 - o1);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (test.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (test.isFull()) {
                    if (my.pop() != test.pop()) {
                        Assertions.fail();
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            Assertions.fail();
                        }
                    }
                }
            }
        }
    }

}