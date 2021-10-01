//链接：https://www.nowcoder.com/questionTerminal/b178fcef3ed4448c99d7c0297312212d
//        来源：牛客网
//
//        在你面前有一个n阶的楼梯，你一步只能上1阶或2阶。
//        请问计算出你可以采用多少种不同的方式爬完这个楼梯。
//
//        输入描述:
//        一个正整数n(n<=100)，表示这个楼梯一共有多少阶
//
//
//        输出描述:
//        一个正整数，表示有多少种不同的方式爬完这个楼梯
//        示例1
//        输入
//        5
//        输出
//        8
package nowcoder;

import java.math.BigInteger;
import java.util.Scanner;

//一个人可以一次往上迈1个台阶，也可以迈2个台阶
//
//        返回这个人迈上N级台阶的方法数
public class NowCoder_Stair {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); 
		in.close();
		if (n <= 2) {
			System.out.println(n);
			return;
		}
		BigInteger f1 = BigInteger.valueOf(1);
		BigInteger f2 = BigInteger.valueOf(2);
		BigInteger result = null;
		
		for (int i = 3; i < n + 1; i++) {
			result = f1.add(f2);
			f1 = f2;
			f2 = result;
		}
		System.out.println(result);
	}
	
	
}
