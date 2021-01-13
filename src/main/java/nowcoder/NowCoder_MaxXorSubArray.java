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
package nowcoder;

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

	// 暴力解，利用前缀异或和数组
	public static int maxXor(int[] arr, int n) {
		if (arr == null || n == 0) {
			return 0;
		}
		int[] eor = new int[n];
		eor[0] = arr[0];
		for (int i = 1; i < n; i++) {
			eor[i] = eor[i - 1] ^ arr[i];
		}
		int max = eor[0];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == 0) {
					max = Math.max(eor[j], max);
				} else {
					max = Math.max(eor[j] ^ eor[i - 1], max);
				}
			}
		}
		return max;
	}
	
	public static int maxEor(int[] arr, int n) {
		if (arr == null || n == 0) {
			return 0;
		}
		int[] eor = new int[n];
		eor[0] = arr[0];
		for (int i = 1; i < n; i++) {
			eor[i] = eor[i - 1] ^ arr[i];
		}
		int max = eor[0];	
		Trie trie = new Trie();
		for (int i = 0; i < n; i++) {
			// TODO
		}
		return max;
	}
	// TODO
	public static class Trie {
		// 给我一个num，我可以拿到这个num对应最大的异或和返回
		public static int maxEor(int num) {
			return -1;
		}
		// 将一个数加入前缀树
		public static void add(int num) {
			
		}
	}
}
