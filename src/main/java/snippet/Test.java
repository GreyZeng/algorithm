package snippet;

public class Test {
    public static void main(String[] args) {
        System.out.println(Integer.toUnsignedLong(Integer.MIN_VALUE));
    }
    private static long getUnsignedInt(int data) {
        // data & 0xFFFFFFFF 和 data & 0xFFFFFFFFL 结果是不同的，需要注意，有可能与 JDK 版本有关
        return data & 0xFFFFFFFFL;
    }
}
