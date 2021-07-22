package lintcode;

/**
 * 描述 Two numbers in the array, if the previous number is greater than the
 * following number, then the two numbers form a reverse order pair. Give you an
 * array, find out the total number of reverse order pairs in this array.
 * Summary: if a [i] > a [j] and i < j, a [i] and a [j] form a reverse order
 * pair.
 * 
 * 样例
 * 
 * Example1
 * 
 * Input: A = [2, 4, 1, 3, 5] Output: 3 Explanation: (2, 1), (4, 1), (4, 3) are
 * reverse pairs
 * 
 * Example2
 * 
 * Input: A = [1, 2, 3, 4] Output: 0 Explanation: No reverse pair
 * 
 * @author Grey
 * @date 2021年7月22日 下午1:33:22
 * @since
 */
public class LintCode_0532_ReversePairs {

	public static long reversePairs(int[] A) {
		if (null == A || A.length < 2) {
			return 0;
		}
		int size = A.length;
		return p(A, 0, size - 1);
	}

	public static long p(int[] A, int L, int R) {
		if (L == R) {
			return 0l;
		}
		int mid = ((R - L) >> 1) + L;
		return p(A, L, mid) + p(A, mid + 1, R) + merge(A, L, mid, R);
	}

	public static long merge(int[] A, int l, int mid, int r) {
		int[] helper = new int[r - l + 1];
		int i = l;
		int j = mid + 1;
		int index = 0;
		long pair = 0;
		while (i <= mid && j <= r) {
			if (A[i] > A[j]) {
				pair += (r - j + 1);
				helper[index++] = A[i++];
				
			} else {
				helper[index++] = A[j++];
			}
		}
		while (i <= mid) {
			helper[index++] = A[i++];
		}
		while (j <= r) {
			helper[index++] = A[j++];
		}
		int k = 0;
		for (int num : helper) {
			A[l + (k++)] = num;
		}
		return pair;
	}
}
