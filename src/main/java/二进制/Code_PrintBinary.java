package 二进制;

// 二进制与位运算
// 负数的二进制如何理解
// 1. 最高位是1
// 2. 除去最高位，其他位数取反以后，再加1
public class Code_PrintBinary {


    // 打印一个32位整数的二进制形式
    public String binary(int num) {
        // 0010
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            if (((1 << i) & num) == 0) {
                sb.append("0");
            } else {
                sb.append("1");
            }
        }
        return sb.toString();
    }
}
