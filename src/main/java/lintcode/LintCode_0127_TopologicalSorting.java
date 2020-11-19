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
	// 使用BFS实现，已通过验证
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Integer> map = buildIndex(graph);
		Queue<DirectedGraphNode> starts = new LinkedList<>();

		for (Map.Entry<DirectedGraphNode, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 0) {
				starts.add(entry.getKey());
			}
		}
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		while (!starts.isEmpty()) {
			DirectedGraphNode node = starts.poll();
			ans.add(node);
			if (node.neighbors != null && !node.neighbors.isEmpty()) {
				for (DirectedGraphNode neighbor : node.neighbors) {
					if (map.get(neighbor) == 1) {
						starts.offer(neighbor);
					}
					map.put(neighbor, map.get(neighbor) - 1);
				}
			}
		}
		return ans;
	}

	// 所有点以及其入度是多少对应关系
	public HashMap<DirectedGraphNode, Integer> buildIndex(ArrayList<DirectedGraphNode> graph) {
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
		return map;
	}

	// TODO 使用DFS来实现
	public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
		return null;
	}

	

}
