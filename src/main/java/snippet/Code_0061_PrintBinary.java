package snippet;

// 打印一个数的二进制形式
public class Code_0061_PrintBinary {

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void printBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 0b0000_1110_1010;
        printBinary(num);
        printBinary(num << 1);
        printBinary(Integer.MAX_VALUE);
        printBinary(Integer.MIN_VALUE);
        // 32位
//		int num = 4;
//
//		print(num);
//		
//		
//		int test = 1123123;
//		print(test);
//		print(test<<1);
//		print(test<<2);
//		print(test<<8);
//		
//		
//		int a = Integer.MAX_VALUE;
//		System.out.println(a);

//		print(-1);
//		int a = Integer.MIN_VALUE;
//		print(a);

//		int b = 123823138;
//		int c = ~b;
//		print(b);
//		print(c);

//		print(-5);

//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);

//		int a = 12319283;
//		int b = 3819283;
//		print(a);
//		print(b);
//		System.out.println("=============");
//		print(a | b);
//		print(a & b);
//		print(a ^ b);

//		int a = Integer.MIN_VALUE;
//		print(a);
//		print(a >> 1);
//		print(a >>> 1);
//		
//		int c = Integer.MIN_VALUE;
//		int d = -c ;
//		
//		print(c);
//		print(d);

    }

}
