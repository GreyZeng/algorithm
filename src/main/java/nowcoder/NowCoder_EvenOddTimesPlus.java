package nowcoder;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/863d84dc0b2d4e639dc17f12701ed3ac
 * 来源：牛客网
 * 
 * 给定一个数字arr，其中只有两个数字出现了奇数次，其它数字都出现了偶数次，按照从小到大顺序输出这两个数。
 * 
 * 输入描述: 第一行输入一个n， 第二行输入n个数
 * 
 * 
 * 输出描述: 输出出现奇数次的两个数，按照从小到大的顺序。 示例1 输入 4 1 1 2 3 输出 2 3 示例2 输入 6 11 22 11 23 23
 * 45 输出 22 45
 * 
 * @author Grey
 * @date 2021年7月16日 下午11:46:03
 * @since
 */
public class NowCoder_EvenOddTimesPlus {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = in.nextInt();
		}
		int[] targets = oddNum(arr);
		System.out.println(targets[0] + " " + targets[1]);
		in.close(); 
	}

	private static int[] oddNum(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		// 假设出现奇数的两个数是a和b，
		// 那么eor得到的结果是：a^b
		// onlyOneRightest表示提取eor最右侧的1
		// 这个最右侧的位置a和b一定不同（假设a这个位置是1，b这个位置是0）
		int onlyOneRigthest = eor & ((~eor) + 1);
		// 此时，数组可以分成两批数
		// 一批数和a一样，都是这个位置为1的
		// 另外一批数和b一样，都是这个位置为0的
		int[] result = new int[2];
		int a = 0;
		int b = 0;
		for (int num : arr) {
			if ((onlyOneRigthest & num) != 0) {
				a ^= num;
			} else {
				b ^= num;
			}
		}
 		result[0] = Math.min(a, b);
 		result[1] = Math.max(a, b);
		return result;
	}
}
