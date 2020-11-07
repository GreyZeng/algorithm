/*在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

示例 1:

输入: [7,5,6,4]
输出: 5
 

限制：

0 <= 数组长度 <= 50000

*/
package leetcode;

// https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
// 归并排序
public class LeetCodeCN_0051_ReversePairs {

	public static int reversePairs(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		return p(nums, 0, nums.length - 1);
	}

	private static int p(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int M = L + ((R - L) >> 1);
		return p(arr, L, M) + p(arr, M + 1, R) + merge(arr, L, M, R);
	}

	private static int merge(int[] arr, int L, int M, int R) {
		int len = R - L + 1;
		int l = L;
		int mid = M + 1;
		int i = 0;
		int pair = 0;
		int[] help = new int[len];
		while (l <= M && mid <= R) {
			if (arr[l] > arr[mid]) {
				pair += ((R - mid) + 1);
			}
			help[i++] = arr[l] > arr[mid] ? arr[l++] : arr[mid++];
		}
		while (l <= M) {
			help[i++] = arr[l++];
		}
		while (mid <= R) {
			help[i++] = arr[mid++];
		}
		i = 0;
		for (int t : help) {
			arr[L + (i++)] = t;
		}
		return pair;
	}
}
