package git.snippet.bit;

import git.snippet.bit.Code_PrintBinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("打印一个32位整数的二进制形式")
public class Code_PrintBinaryTest {

    @Test
    public void testBinary() {
        Code_PrintBinary test = new Code_PrintBinary();
        int num = 0b10001010_11101010_10001000_11101010;
        System.out.println("要处理的数据是：");
        System.out.println("10001010111010101000100011101010");
        System.out.println("打印原始数的二进制：");
        String printNum = test.binary(num);
        System.out.println(test.binary(num));
        Assertions.assertEquals(printNum, Integer.toBinaryString(num));
        System.out.println("原始内容和打印内容是否一致（如果一致则正确）" + printNum.equals(Integer.toBinaryString(num)));
        System.out.println("左移一位：");
        System.out.println(test.binary(num << 1));
        System.out.println(">> 表示有符号右移一位：");
        System.out.println(test.binary(num >> 1));
        System.out.println(">>> 表示带着符号右移一位：");
        System.out.println(test.binary(num >>> 1));
        System.out.println("系统最大值的二进制：");
        System.out.println(test.binary(Integer.MAX_VALUE));
        System.out.println("系统最小值的二进制：");
        System.out.println(test.binary(Integer.MIN_VALUE));
        System.out.println("负的系统最小值的二进制(还是本身)：");
        System.out.println(test.binary(-Integer.MIN_VALUE));
        System.out.println((-Integer.MIN_VALUE) == (Integer.MIN_VALUE));
        System.out.println("取反：");
        System.out.println(test.binary(~num));
    }
}