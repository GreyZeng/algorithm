/*Given a non-empty array of unique positive integers A, consider the following graph:

        There are A.length nodes, labelled A[0] to A[A.length - 1];
        There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
        Return the size of the largest connected component in the graph.

        给定一个由不同正整数的组成的非空数组 A，考虑下面的图：

有 A.length 个节点，按从 A[0] 到 A[A.length - 1] 标记；
只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i] 和 A[j] 之间才有一条边。
返回图中最大连通组件的大小。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-component-size-by-common-factor
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
        */
package leetcode;

import java.util.*;

public class LeetCode_0952_LargestComponentSizeByCommonFactor {
	public static int largestComponentSize(int[] arr) {
		UnionFind uf = new UnionFind(arr.length);
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int num = (int) Math.sqrt(arr[i]);
			for (int j = 1; j <= num; j++) {
				if (arr[i] % j == 0) {
					if (j != 1) {
						if (!map.containsKey(j)) {
							map.put(j, i);
						} else {
							uf.union(map.get(j), i);
						}

					}
					int other = arr[i] / j;
					if (other != 1) {
						if (!map.containsKey(other)) {
							map.put(other, i);
						} else {
							uf.union(map.get(other), i);
						}
					}
				}
			}
		}
		return uf.maxSize();
	}
 
	// 并查集
	public static class UnionFind {
		private int[] parents; // parents[i] = j arr[i]的父亲是arr[j]
		private int[] sizes; // sizes[i] = X arr[i]所在的集合大小为X

		public UnionFind(int len) {
			parents = new int[len];
			sizes = new int[len];
			for (int i = 0; i < len; i++) {
				parents[i] = i;
				sizes[i] = 1;
			}
		}

		public int size() {
			int ans = 0;
			for (int i = 0; i < sizes.length; i++) {
				ans += sizes[i] != 0 ? 1 : 0;
			}
			return ans;
		}

		public int maxSize() {
			int ans = 0;
			for (int size : sizes) {
				ans = Math.max(ans, size);
			}
			return ans;
		}

		private int findHead(int element) {
			Stack<Integer> path = new Stack<>();
			while (element != parents[element]) {
				path.push(element);
				element = parents[element];
			}
			while (!path.isEmpty()) {
				parents[path.pop()] = element;
			}
			return element;
		}

		// a 和 b 分别是两个数的位置，不是值
		public void union(int a, int b) {
			int aF = findHead(a);
			int bF = findHead(b);
			if (aF != bF) {
				int big = sizes[aF] >= sizes[bF] ? aF : bF;
				int small = big == aF ? bF : aF;
				parents[small] = big;
				sizes[big] = sizes[aF] + sizes[bF];
				sizes[small] = 0;
			}
		}
	}
}
