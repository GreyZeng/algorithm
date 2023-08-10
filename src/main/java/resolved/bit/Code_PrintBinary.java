package resolved.bit;

// 二进制与位运算
// 负数的二进制如何理解
// 1. 最高位是1
// 2. 除去最高位，其他位数取反以后，再加1
public class Code_PrintBinary {
    // 打印一个32位整数的二进制形式
    public static String printBinary(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append((n & (1 << i)) == 0 ? "0" : "1");
        }
        // System.out.println(sb);
        return sb.toString();
    }


    public static void main(String[] args) {
        int num = 0b10001010_11101010_10001000_11101010;
        System.out.println("要处理的数据是：");
        System.out.println("10001010111010101000100011101010");
        System.out.println("打印原始数的二进制：");
        String printNum = printBinary(num);
        System.out.println(printBinary(num));
        System.out.println("原始内容和打印内容是否一致（如果一致则正确）" + printNum.equals(Integer.toBinaryString(num)));
        System.out.println("左移一位：");
        printBinary(num << 1);
        System.out.println(">> 表示有符号右移一位：");
        printBinary(num >> 1);
        System.out.println(">>> 表示带着符号右移一位：");
        printBinary(num >>> 1);
        System.out.println("系统最大值的二进制：");
        printBinary(Integer.MAX_VALUE);
        System.out.println("系统最小值的二进制：");
        printBinary(Integer.MIN_VALUE);
        System.out.println("负的系统最小值的二进制(还是本身)：");
        printBinary(-Integer.MIN_VALUE);
        System.out.println("取反：");
        printBinary(~num);
    }
}
