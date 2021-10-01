package nowcoder;



//链接：https://www.nowcoder.com/questionTerminal/d5c1e5ffbe124306a3a2ec5fe4139021
//来源：牛客网
//
//牛牛有一块"2*n"的空白瓷砖并且有足够多的"1*2"和"2*3"两种类型的地毯(地毯可以旋转).现在他想在满足以下条件: 地毯之间不能相互重叠,地毯不能铺出瓷砖外以及不能有空隙下铺满整个瓷砖.问你一共有多少种不同的方案并且结果模上10007输出.
//
//输入描述:
//
//第一行输入一个正整数 T .表示有 T 组数据.
//接下来 T 行,每行输入一个正整数 n.
//1<= T <= 100
//1<= n <= 100000
//
//
//
//输出描述:
//
//输出 T 行,每一行对应每组数据的输出.
//
//示例1
//输入
//
//4
//1
//2
//3
//5
//
//输出
//
//1
//2
//4
//13
import java.util.Scanner;
public class NowCoder_Ceramic {
	public static final int MOD = 10007;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] arr = new int[T];
		for (int i = 0; i < T; i++) {
			arr[i] = in.nextInt();
		}
		for (int n: arr) {
			System.out.println(ways(n));
		}
		in.close();
	}
	private static long ways(int n) {
		if (n <= 2) {
			return n;
		}
		if (n ==3) {
			return 4; 
		}
		long a = 1;
		long b = 2;
		long c = 4;
		long r = 0;
		for (int i = 4; i <= n; i++) {
			r = (a + b + c)%MOD;
			a = b;
			b = c;
			c = r;
		}
		return r;
	}
	// TODO 优化
	

}
