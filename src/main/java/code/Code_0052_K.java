package code;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.*;

import code.graph.*;


public class Code_0052_K { 
	
	public class UnionFind {
		private HashMap<Node, Node> parentMap;
		private HashMap<Node, Integer> sizeMap;

		public UnionFind() {
			parentMap = new HashMap<>();
			sizeMap = new HashMap<>();
		}

		public void addSets(Collection<Node> values) {
			for (Node node : values) {
				parentMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		public boolean isSameSet(Node from, Node to) {
			return findFather(from) == findFather(to);
		}

		public void union(Node a, Node b) {
			Node f1 = findFather(a);
			Node f2 = findFather(b);
			if (f1 != f2) {
				int size1 = sizeMap.get(f1);
				int size2 = sizeMap.get(f2);
				Node small = size1 > size2 ? f2 : f1;
				Node large = small == f1 ? f2 : f1;
				parentMap.put(small, large);
				sizeMap.put(large, size1 + size2);
			}

		}

		public Node findFather(Node n) {
			Stack<Node> path = new Stack<>();
			while(n != parentMap.get(n)) {
				path.add(n);
				n = parentMap.get(n);
			}
			while(!path.isEmpty()) {
				parentMap.put(path.pop(), n);
			}
			return n;
		}

	}

	public static class MyComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}

	public Set<Edge> k(Graph graph) {

		// 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
		PriorityQueue<Edge> queue = new PriorityQueue<>(new MyComparator());
		for (Edge edge : graph.edges) {
			queue.offer(edge);
		}
		HashSet<Edge> ans = new HashSet<>();
		UnionFind uf = new UnionFind();
		uf.addSets(graph.nodes.values());
		// 2）当前的边要么进入最小生成树的集合，要么丢弃
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (!uf.isSameSet(edge.from, edge.to)) {
				// 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
				ans.add(edge);
				uf.union(edge.from, edge.to);
			}
			// 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
			// 5）考察完所有边之后，最小生成树的集合也得到了
		}
		return ans;
	}
}
