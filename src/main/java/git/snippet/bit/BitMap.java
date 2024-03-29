package git.snippet.bit;

// 位图
// 笔记：https://www.cnblogs.com/greyzeng/p/16634282.html
public class BitMap {

    private final long[] bits;

    // 初始化
    public BitMap(int max) {
        // 准备多少个整数？ 0 ~ 63 需要1个整数
        // >> 6 就是 除以 64 ，>> 效率比除法高
        // bits = new long[(max + 64) >> 6];
        bits = new long[(max + 64) / 64];
    }

    public void add(int num) {
        bits[num / 64] |= (1L << (num % 64));
        // num % 64 ---> num & 63 即：0b111111
        // 只适用于 2 的 n 次方
        // 注意：这里是1L非1，如果是1，因为要管64位
        // 所以上述公式也可以写成如下
        // bits[num >> 6] |= (1L << (num & 0b111111));
    }

    public void remove(int num) {
        bits[num / 64] &= ~(1L << (num % 64));
        // bits[num >> 6] &= ~(1L << (num & 0b111111));
    }

    public boolean contains(int num) {
        // return (bits[num >> 6] & (1L << (num & 0b111111))) != 0;
        return (bits[num / 64] & (1L << (num % 64))) != 0;
    }
}