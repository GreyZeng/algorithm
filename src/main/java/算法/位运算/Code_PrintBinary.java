package 算法.位运算;

// 二进制与位运算
// 负数的二进制如何理解
// 1. 最高位是1
// 2. 除去最高位，其他位数取反以后，再加1
public class Code_PrintBinary {
  // 打印一个32位整数的二进制形式
  public static void printBinary(int num) {
    for (int i = 31; i >= 0; i--) {
      System.out.print((num & (1 << i)) == 0 ? "0" : "1");
    }
    System.out.println();
  }

  // 取一个数最右侧的1
  public static int leftMostOne(int num) {
    return num & (-num);
  }

  public static int leftMostOne2(int num) {
    return num & (~num + 1);
  }

  public static void main(String[] args) {
    printBinary(22);
    printBinary(leftMostOne(22));
    System.out.println(leftMostOne(22));
    boolean right = true;
    int times = Integer.MAX_VALUE;
    for (int i = 0; i < times; i++) {
      if (leftMostOne(i) != leftMostOne2(i)) {
        right = false;
        break;
      }
    }
    if (!right) {
      System.out.println("oops");
    } else {
      System.out.println("right");
    }

    int num = 0b10001010_11101010_10001000_11101010;
    System.out.println("要处理的数据是：");
    System.out.println("10001010111010101000100011101010");
    System.out.println("原始串：");
    printBinary(num);
    System.out.println("取反：");
    printBinary(~num);
    System.out.println("左移一位：");
    printBinary(num << 1);
    System.out.println(">> 表示有符号右移一位：");
    printBinary(num >> 1);
    System.out.println(">>> 表示无符号右移一位：");
    printBinary(num >>> 1);
    System.out.println("系统最大值的二进制：");
    printBinary(Integer.MAX_VALUE);
    System.out.println("系统最小值的二进制：");
    printBinary(Integer.MIN_VALUE);
    System.out.println("负的系统最小值的二进制(还是本身)：");
    printBinary(-Integer.MIN_VALUE);
    // System.out.println(-Integer.MIN_VALUE == Integer.MIN_VALUE);
  }
}
