package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// 链接：https://www.nowcoder.com/questionTerminal/23ed40416d9c4c72816edb12daa3bdff

// 来源：牛客网

// 请编程实现一个整型数组的归并排序。本题会人工判断,请严格按照题目描述完成

// 输入描述:
// 一个无序的整型数组,输入格式见输入样例

// 输出描述:
// 一个有序的整型数组,输出格式见输出样例
// 示例1
// 输入
// [3, 1, 4, 5, 17, 2, 12]
// 输出
// [1, 2, 3, 4, 5, 12, 17]
/**
 * 归并排序 1）整体是递归，左边排好序+右边排好序+merge让整体有序
 * 
 * 2）让其整体有序的过程里用了排外序方法
 * 
 * 3）利用master公式来求解时间复杂度
 * 
 * 4）当然可以用非递归实现
 * 
 * 
 * T(N) = 2*T(N/2) + O(N^1)
 * 
 * 根据master可知时间复杂度为O(N*logN)
 * 
 * merge过程需要辅助数组，所以额外空间复杂度为O(N)
 * 
 * 归并排序的实质是把比较行为变成了有序信息并传递，比O(N^2)的排序快
 */
public class NowCoder_MergeSort {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int[] num = toArray(line);
		if (Math.random() < 0.5d) {
			mergeSort2(num);
		} else {
			mergeSort(num);
		}
		System.out.println(Arrays.toString(num));
		in.close();
	}

	// 递归版本
	private static void mergeSort(int[] num) {
		process(num, 0, num.length - 1);
	}

	// 迭代版本
	public static void mergeSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		// 步长
		int mergeSize = 1;
		while (mergeSize < N) { // log N
			// 当前左组的，第一个位置
			int L = 0;
			while (L < N) {
				if (mergeSize >= N - L) {
					break;
				}
				int M = L + mergeSize - 1;
				int R = M + Math.min(mergeSize, N - M - 1);
				merge(arr, L, M, R);
				L = R + 1;
			}
			// 防止溢出
			if (mergeSize > N / 2) {
				break;
			}
			mergeSize <<= 1;
		}
	}

	private static void process(int[] num, int L, int R) {
		if (L == R) {
			return;
		}
		int M = L + ((R - L) >> 1);
		process(num, L, M);
		process(num, M + 1, R);
		merge(num, L, M, R);
	}

	// L...mid有序
	// mid+1 ... r有序
	private static void merge(int[] num, int L, int M, int R) {
		int[] help = new int[R - L + 1];
		int L1 = L;
		int L2 = M + 1;
		int index = 0;
		while (L1 <= M && L2 <= R) {
			if (num[L1] <= num[L2]) {
				help[index++] = num[L1++];
			} else {
				help[index++] = num[L2++];
			}
		}
		while (L1 <= M) {
			help[index++] = num[L1++];
		}
		while (L2 <= R) {
			help[index++] = num[L2++];
		}
		index = 0;
		for (int v : help) {
			num[L + (index++)] = v;
		}
	}

	public static int[] toArray(String line) {
		String s = line.substring(1, line.length() - 1);
		String[] data = s.split(",");
		int[] arr = new int[data.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.valueOf(data[i].trim());
		}
		return arr;
	}
}
