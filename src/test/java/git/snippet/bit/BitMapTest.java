package git.snippet.bit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("位图测试")
public class BitMapTest {
    @Test
    void testBitMap() {
        // System.out.println("test begin");
        int max = 70000;
        BitMap bitMap = new BitMap(max);
        Set<Integer> set = new HashSet<>();
        int testTime = 90000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.remove(num);
                set.remove(num);
            } else {
                assertEquals(bitMap.contains(num), set.contains(num));
            }
        }
        for (int num = 0; num <= max; num++) {
            assertEquals(bitMap.contains(num), set.contains(num));
        }
    }
}