//描述
//给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
//
//你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中。
//
// 
//样例
//样例 1:
//
//输入: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
//输出: true.
//样例 2:
//
//输入: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
//输出: false.
package lintcode;

// https://www.lintcode.com/problem/graph-valid-tree/description
public class LintCode_0178_GraphValidTree {

	// 如何判断环？ 如果一个node节点中两个点的代表点一样，说明出现了环，直接返回false
	class UnionFind {
		int[] parent, rank;
		int group;

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 1;
			}

			group = n;
		}

		public int find(int p) {
			if (p != parent[p]) {
				parent[p] = find(parent[p]);
			}

			return parent[p];
		}

		public void union(int p, int q) {
			int pRoot = find(p), qRoot = find(q);
			if (pRoot == qRoot) {
				return;
			}

			if (rank[pRoot] < rank[qRoot]) {
				parent[pRoot] = qRoot;
			} else if (rank[pRoot] > rank[qRoot]) {
				parent[qRoot] = pRoot;
			} else {
				parent[pRoot] = qRoot;
				rank[qRoot]++;
			}

			group--;
		}
	}

	public boolean validTree(int n, int[][] edges) {
		// write your code here
		if (n == 0) {
			return true;
		}

		if (n - 1 != edges.length) {
			return false;
		}

		UnionFind uf = new UnionFind(n);
		for (int[] edge : edges) {
			uf.union(edge[0], edge[1]);
		}

		return uf.group == 1;
	}
}
