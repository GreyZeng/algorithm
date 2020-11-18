package lintcode;

import java.util.*;

// https://www.lintcode.com/problem/topological-sorting/description
public class LintCode_0127_TopologicalSorting {

	class DirectedGraphNode {
		int label;
		ArrayList<DirectedGraphNode> neighbors;

		DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<>();
		}
	}

	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Integer> map = new HashMap<>();
		for (DirectedGraphNode node : graph) {
			if (!map.containsKey(node)) {
				map.put(node, 0);
			}
			for (DirectedGraphNode neighbor : node.neighbors) {
				if (map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}
		Queue<DirectedGraphNode> starts = new LinkedList<>();
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 0) {
				starts.add(entry.getKey());
			}
		}
		while (!starts.isEmpty()) {
			DirectedGraphNode node = starts.poll();
			ans.add(node);
			if (node.neighbors != null && !node.neighbors.isEmpty()) {
				for (DirectedGraphNode neighbor : node.neighbors) {
					if (map.get(neighbor) == 1) {
						starts.offer(neighbor);
					} else {
						map.put(neighbor, map.get(neighbor) - 1);
					}
				}
			}
		}
		return ans;
	}

	// TODO DFS方式
	public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
		return null;
	}

	public class Graph {
		public HashMap<Integer, Node> nodes;
		public HashSet<Edge> edges;

		public Graph() {
			nodes = new HashMap<>();
			edges = new HashSet<>();
		}
	}

	public class Edge {
		public int weight;
		public Node from;
		public Node to;

		public Edge(int weight, Node from, Node to) {
			this.weight = weight;
			this.from = from;
			this.to = to;
		}
	}

	public class Node {
		public int value;
		public int in;
		public int out;
		public ArrayList<Node> nexts;
		public ArrayList<Edge> edges;

		public Node(int value) {
			this.value = value;
			in = 0;
			out = 0;
			nexts = new ArrayList<>();
			edges = new ArrayList<>();
		}
	}

}
