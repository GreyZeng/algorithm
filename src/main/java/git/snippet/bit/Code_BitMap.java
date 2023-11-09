package git.snippet.bit;

import java.util.HashSet;
import java.util.Set;

// 位图
// 笔记：https://www.cnblogs.com/greyzeng/p/16634282.html
public class Code_BitMap {

    public static void main(String[] args) {
        System.out.println("test begin");
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
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    // 位图
    public static class BitMap {

        private final long[] bits;

        // 初始化
        public BitMap(int max) {
            // 准备多少个整数？ 0 ~ 63 需要1个整数
            // >> 6 就是 除以 64 >> 效率比除法高
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            // bits[num / 64] |= (1L << (num % 64));
            // num % 64 ---> num & 63 即：0b111111
            // 只适用于 2 的 n 次方
            // 注意：这里是1L非1，如果是1，因为要管64位
            bits[num >> 6] |= (1L << (num & 0b111111));
        }

        public void remove(int num) {
            bits[num >> 6] &= ~(1L << (num & 0b111111));
        }

        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 0b111111))) != 0;
        }
    }
}
