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
	public static void main(String[] args) {
		int[] A = {2, 4, 1, 3, 5};
		long result = reversePairs(A);
		System.out.println(result);
	}
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
		int[] help = new int[r - l + 1];
		int s1 = l;
		int s2 = mid + 1;
		int index = 0;
		long result = 0;
		while (s1 <= mid && s2 <= r) {
			if (A[s1] > A[s2]) {
				result += (r - s2 + 1);
				help[index++] = A[s1++];
			} else if (A[s1] < A[s2]) {
				help[index++] = A[s2++];
			} else {
				help[index++] = A[s2++];
				 
			}
		}
		while (s1 <= mid) {
			help[index++] = A[s1++];
		}
		while (s2 <= r) {
			help[index++] = A[s2++];
		}
		index = 0;
		for (int v : help) {
			A[l + (index++)] = v;
		}
		return result;
	}
}
