package grey.algorithm.class02_bit;

public class Code_0002_Binary {
	// 二进制与位运算
	// 负数的二进制如何理解
	// 1. 最高位是1
	// 2. 除去最高位，其他位数取反以后，再加1
	// 打印一个32位整数的二进制形式
	public static void printBinary(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 31; i >= 0; i--) {
			if (((1 << i) & num) == 0) {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		// 非负数
		int a = 78;
		System.out.println(a);
		printBinary(a);
		System.out.println("使用系统API转换的结果\n" + Integer.toBinaryString(a));
		System.out.println("===a===");
		// 负数
		int b = -6;
		System.out.println(b);
		printBinary(b);
		System.out.println("使用系统API转换的结果\n" + Integer.toBinaryString(b));
		System.out.println("===b===");
		// 直接写二进制的形式定义变量
		int c = 0b1001110;
		System.out.println(c);
		printBinary(c);
		System.out.println("使用系统API转换的结果\n" + Integer.toBinaryString(c));
		System.out.println("===c===");
		// 直接写十六进制的形式定义变量
		// 0100 -> 4
		// 1110 -> e
		// 0x4e -> 01001110
		int d = 0x4e;
		System.out.println(d);
		printBinary(d);
		System.out.println("使用系统API转换的结果\n" + Integer.toBinaryString(d));
		System.out.println("===d===");
		// ~、相反数
		System.out.println(a);
		printBinary(a);
		printBinary(~a);
		int e = ~a + 1;
		System.out.println(e);
		printBinary(e);
		System.out.println("===e===");
		// int、long的最小值，取相反数、绝对值，都是自己
		int f = Integer.MIN_VALUE;
		System.out.println(f);
		printBinary(f);
		System.out.println(-f);
		printBinary(-f);
		System.out.println(~f + 1);
		printBinary(~f + 1);
		System.out.println("===f===");
		// | & ^
		int g = 0b0001010;
		int h = 0b0001100;
		printBinary(g | h);
		printBinary(g & h);
		printBinary(g ^ h);
		System.out.println("===g、h===");
		// 可以这么写 : int num = 3231 | 6434;
		// 可以这么写 : int num = 3231 & 6434;
		// 不能这么写 : int num = 3231 || 6434;
		// 不能这么写 : int num = 3231 && 6434;
		// 因为 ||、&& 是 逻辑或、逻辑与，只能连接boolean类型
		// 不仅如此，|、& 连接的两侧一定都会计算
		// 而 ||、&& 有穿透性的特点
		System.out.println("test1测试开始");
		boolean test1 = returnTrue() | returnFalse();
		System.out.println("test1结果，" + test1);
		System.out.println("test2测试开始");
		boolean test2 = returnTrue() || returnFalse();
		System.out.println("test2结果，" + test2);
		System.out.println("test3测试开始");
		boolean test3 = returnFalse() & returnTrue();
		System.out.println("test3结果，" + test3);
		System.out.println("test4测试开始");
		boolean test4 = returnFalse() && returnTrue();
		System.out.println("test4结果，" + test4);
		System.out.println("===|、&、||、&&===");
		// <<
		int i = 0b0011010;
		printBinary(i);
		printBinary(i << 1);
		printBinary(i << 2);
		printBinary(i << 3);
		System.out.println("===i << ===");
		// 非负数 >> >>>，效果一样
		printBinary(i);
		printBinary(i >> 2);
		printBinary(i >>> 2);
		System.out.println("===i >> >>>===");
		// 负数 >> >>>，效果不一样
		int j = 0b10110000000000000000000000000000;
		printBinary(j);
		printBinary(j >> 1);
		printBinary(j >> 2);
		printBinary(j >>> 1); // 带着符号右移
		printBinary(j >>> 2);
		System.out.println("===j >> >>>===");
		// 非负数 << 1，等同于乘以2
		// 非负数 << 2，等同于乘以4
		// 非负数 << 3，等同于乘以8
		// 非负数 << i，等同于乘以2的i次方
		// ...
		// 非负数 >> 1，等同于除以2
		// 非负数 >> 2，等同于除以4
		// 非负数 >> 3，等同于除以8
		// 非负数 >> i，等同于除以2的i次方
		// 只有非负数符合这个特征，负数不要用
		int k = 10;
		System.out.println(k);
		System.out.println(k << 1);
		System.out.println(k << 2);
		System.out.println(k << 3);
		System.out.println(k >> 1);
		System.out.println(k >> 2);
		System.out.println(k >> 3);
		System.out.println("===k===");
	}

	public static boolean returnTrue() {
		System.out.println("进入了returnTrue函数");
		return true;
	}

	public static boolean returnFalse() {
		System.out.println("进入了returnFalse函数");
		return false;
	}
}