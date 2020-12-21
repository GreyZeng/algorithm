//链接：https://www.nowcoder.com/questionTerminal/5fe02eb175974e18b9a546812a17428e
//        来源：牛客网
//
//        给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
//        max(arr[i...j]) - min(arr[i...j]) <= num
//        max(arr[i...j])表示子数组arr[i...j]中的最大值，min[arr[i...j])表示子数组arr[i...j]中的最小值。
//
//
//        输入描述:
//
//        第一行输入两个数 n 和 num，其中 n 表示数组 arr 的长度
//        第二行输入n个整数XiX_iXi​，表示数组arr中的每个元素
//
//
//
//        输出描述:
//
//        输出给定数组中满足条件的子数组个数
//
//        示例1
//        输入
//
//        5 2
//        1 2 3 4 5
//
//        输出
//
//        12
//
//
//        备注:
//
//        1≤n≤1000000
//        −1000000≤arri≤1000000
//        0≤num≤20000000
package nowcoder;

import java.util.LinkedList;
import java.util.Scanner; 

public class NowCoder_AllLessNumSubArray {

	public static int getNum(int[] arr, int num) {
		LinkedList<Integer> pMax = new LinkedList<>();
		LinkedList<Integer> pMin = new LinkedList<>();
		int L = 0;
		int R = 0;
		int count = 0;
		while (L < arr.length) {
			while (R < arr.length) {
				while (!pMax.isEmpty() && arr[pMax.peekLast()] <= arr[R]) {
					pMax.pollLast();
				}
				pMax.addLast(R);
				while (!pMin.isEmpty() && arr[pMin.peekLast()] >= arr[R]) {
					pMin.pollLast();
				}
				pMin.addLast(R);
				if (arr[pMax.peekFirst()] - arr[pMin.peekFirst()] > num) {
					break;
				}
				R++;
			}
			count += (R - L);

			if (pMax.peekFirst() == L) {
				pMax.pollFirst();
			}
			if (pMin.peekFirst() == L) {
				pMin.pollFirst();
			}
			L++;
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lenOfArray = in.nextInt();
		int target = in.nextInt();
		int[] arr = new int[lenOfArray];
		for (int i = 0; i < lenOfArray; i++) {
			arr[i] = in.nextInt();
		}
		System.out.println(getNum(arr, target));
		in.close();
	}
}
