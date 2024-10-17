package git.snippet.bit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static git.snippet.bit.Code_GetMaxWithoutCompare.getMax1;
import static git.snippet.bit.Code_GetMaxWithoutCompare.getMax2;

@DisplayName("使用位运算技巧比较两个数大小")
public class Code_GetMaxWithoutCompareTest {

    @Test
    public void testGetMax() {
        int min = -999999;
        int max = 9999999;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int a = (int) ((max - min) * Math.random() + min);
            int b = (int) ((max - min) * Math.random() + min);
            if (a == b) {
                continue;
            }
            Assertions.assertEquals(Math.max(a, b), getMax1(a, b));
            Assertions.assertEquals(Math.max(a, b), getMax2(a, b));
        }
    }
}