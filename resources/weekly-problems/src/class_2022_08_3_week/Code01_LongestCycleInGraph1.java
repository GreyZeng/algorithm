package class_2022_08_3_week;

import java.util.ArrayList;

// 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
// 图用一个大小为 n 下标从 0 开始的数组 edges 表示，
// 节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
// 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
// 一个环指的是起点和终点是 同一个 节点的路径。
// 用强联通分量
// 测试链接 : https://leetcode.cn/problems/longest-cycle-in-a-graph/
public class Code01_LongestCycleInGraph1 {

	public static int longestCycle(int[] edges) {
		int n = edges.length;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; i++) {
			if (edges[i] != -1) {
				graph.get(i).add(edges[i]);
			}
		}
		StronglyConnectedComponents connectedComponents = new StronglyConnectedComponents(graph);
		int m = connectedComponents.getSccn() + 1;
		int[] cnt = new int[m];
		int[] scc = connectedComponents.getScc();
		for (int i = 0; i < n; i++) {
			cnt[scc[i]]++;
		}
		int ans = -1;
		for (int count : cnt) {
			ans = Math.max(ans, count > 1 ? count : -1);
		}
		return ans;
	}

	public static class StronglyConnectedComponents {
		public ArrayList<ArrayList<Integer>> nexts;
		public int n;
		public int stackSize;
		public int cnt;
		public int sccn;
		public int[] stack;
		public int[] dfn;
		public int[] low;
		public int[] scc;

		public StronglyConnectedComponents(ArrayList<ArrayList<Integer>> edges) {
			nexts = edges;
			init();
			scc();
		}

		private void init() {
			n = nexts.size();
			stackSize = 0;
			cnt = 0;
			sccn = 0;
			stack = new int[n];
			dfn = new int[n];
			low = new int[n];
			scc = new int[n];
		}

		private void scc() {
			for (int i = 0; i < n; i++) {
				if (dfn[i] == 0) {
					tarjan(i);
				}
			}
		}

		private void tarjan(int p) {
			low[p] = dfn[p] = ++cnt;
			stack[stackSize++] = p;
			for (int q : nexts.get(p)) {
				if (dfn[q] == 0) {
					tarjan(q);
				}
				if (scc[q] == 0) {
					low[p] = Math.min(low[p], low[q]);
				}
			}
			if (low[p] == dfn[p]) {
				sccn++;
				int top = 0;
				do {
					top = stack[--stackSize];
					scc[top] = sccn;
				} while (top != p);
			}
		}

		public int[] getScc() {
			return scc;
		}

		public int getSccn() {
			return sccn;
		}

	}

}
