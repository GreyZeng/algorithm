package snippet;

// 二进制与位运算
public class Code_PrintBinary {
    // 打印一个32位整数的二进制形式
    public static void printBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 0b10001010_11101010_10001000_11101010;
        System.out.println("原始串：");
        printBinary(num);
        System.out.println("取反：");
        printBinary(~num);
        System.out.println("左移一位：");
        printBinary(num << 1);
        System.out.println("有符号右移一位：");
        printBinary(num >> 1);
        System.out.println("无符号右移一位：");
        printBinary(num >>> 1);
        System.out.println("系统最大值的二进制：");
        printBinary(Integer.MAX_VALUE);
        System.out.println("系统最小值的二进制：");
        printBinary(Integer.MIN_VALUE);
        System.out.println("负的系统最小值的二进制(还是本身)：");
        printBinary(-Integer.MIN_VALUE);
        System.out.println((-Integer.MIN_VALUE) == Integer.MIN_VALUE);
    }
}
