/*Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n^2.*/
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// 小根堆 按照值来组织
public class LeetCode_0378_KthSmallestElementInSortedMatrix {
	public static class Node {
		public int r;
		public int c;
		public int v;

		public Node(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}

	public static class MyComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.v - o2.v;
		}

	}

	public static int kthSmallest(int[][] matrix, int k) {
		int M = matrix.length;
		int N = matrix[0].length;
		boolean[][] set = new boolean[M][N];
		PriorityQueue<Node> queue = new PriorityQueue<>(new MyComparator());
		queue.offer(new Node(0, 0, matrix[0][0]));
		set[0][0] = true;
		int count = 0;
		Node n = null;
		while (!queue.isEmpty()) {
			n = queue.poll();
			if (++count == k) {
				return n.v;
			}
			if (n.c + 1 < N && !set[n.r][n.c + 1]) {
				set[n.r][n.c + 1] = true;
				queue.offer(new Node(n.r,n.c+1,matrix[n.r][n.c+1]));
			}
			if (n.r + 1 < M && !set[n.r + 1][n.c]) {
				set[n.r + 1][n.c] = true;
				queue.offer(new Node(n.r+1,n.c,matrix[n.r+1][n.c]));
			}
		}
		return n.v; 
	}

}
