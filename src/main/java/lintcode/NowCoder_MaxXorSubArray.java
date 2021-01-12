/*题目描述
数组异或和的定义：把数组中所有的数异或起来得到的值。给定一个整型数组arr，其中可能有正、有负，有零，求其中子数组的最大异或和。
输入描述:
输出包含两行，第一行一个整数n ,代表数组arr长度，第二个n个整数，代表数组arr
输出描述:
输出一个整数，代表其中子数组的最大异或和。
示例1
输入
复制
4
3 -28 -29 2
输出
复制
7
说明
{-28，-29}这个子数组的异或和为7，是所有子数组中最大的
备注:
时间复杂度O(nlog2n)O(nlog2n)，额外空间复杂度O(nlog2n)O(nlog2n)。*/
package lintcode;

import java.util.Scanner;

public class NowCoder_MaxXorSubArray {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		System.out.println(maxXor(arr,n));
		in.close();
	}
	// 
	public static int maxXor(int[] arr, int n) {
		return -1;
	}
}
