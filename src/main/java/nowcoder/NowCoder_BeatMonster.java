/*链接：https://www.nowcoder.com/questionTerminal/736e12861f9746ab8ae064d4aae2d5a9
来源：牛客网

开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
如果你当前的能力，小于i号怪兽的能力，你必须付出money[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
返回通过所有的怪兽，需要花的最小钱数。


输入描述:
第一行输入一个整数N，表示你的能力值N<=500
接下来N行，每行输入两个整数，表示怪兽的力量和需要的金钱


输出描述:
输出一个整数，需要花的最小钱数
示例1
输入
2
8 10
6 5
输出
10*/
package nowcoder;

import java.util.Scanner;

// TODO
public class NowCoder_BeatMonster {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] money = new int[n];
		int[] hp = new int[n];
		for (int i = 0; i < n; i++) {
			hp[i] = in.nextInt();
			money[i] = in.nextInt();
		}
		System.out.println(min(hp,money));
		
		in.close();
	}

	private static int min(int[] hp, int[] money) {
		
		return -1;
	}
}
