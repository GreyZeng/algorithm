/*Given a non-empty array of unique positive integers A, consider the following graph:

        There are A.length nodes, labelled A[0] to A[A.length - 1];
        There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
        Return the size of the largest connected component in the graph.*/
package leetcode;

import java.util.*;

// TODO
public class LeetCode_0952_LargestComponentSizeByCommonFactor {
	public static int largestComponentSize(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			int num = (int) Math.sqrt(arr[i]);
			for (int j = 2; j <= num; j++) {
				
			}
		}
		return -1;
	}

	public static int gcd(int m, int n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	// 并查集
	public class UnionFind {

		public UnionFind(int[] arr) {

		}

		public void union(int i, int j) {

		}

		public boolean isSame(int i, int j) {
			return false;
		}

		public int maxSize() {
			return -1;
		}
	}
}
