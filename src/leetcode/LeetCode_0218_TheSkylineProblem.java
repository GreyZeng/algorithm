package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

// 有序表的使用
public class LeetCode_0218_TheSkylineProblem {
	public static class Node {
		public int x;
		public int h;

		public Node(int x, int h) {
			this.x = x;
			this.h = h;
		}
	}

	public static class MyComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.x != o2.x) {
				return o1.x - o2.x;
			}
			if (o1.h != o2.h) {
				return o1.h > 0 ? -1 : 1;
			}

			return 0;
		}

	}

	public static List<List<Integer>> getSkyline(int[][] buildings) {
		List<Node> items = buildNodes(buildings);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		TreeMap<Integer, Integer> result = new TreeMap<>();
		for (Node n : items) {
			if (n.h > 0) {
				if (!map.containsKey(n.h)) {
					map.put(n.h, 1);
				} else {
					map.put(n.h, map.get(n.h) + 1);
				}
			} else {
				if (map.get(-n.h) == 1) {
					map.remove(-n.h);
				} else {
					map.put(-n.h, map.get(-n.h) - 1);
				}
			}
			if (map.isEmpty()) {
				result.put(n.x, 0);
			} else {
				result.put(n.x, map.lastKey());
			}
		}
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : result.entrySet()) {
			int curX = entry.getKey();
			int curMaxHeight = entry.getValue();
			if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
				ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
			}
		}
		return ans;
	}

	private static List<Node> buildNodes(int[][] buildings) {
		List<Node> list = new ArrayList<>();
		for (int[] building : buildings) {
			list.add(new Node(building[0], building[2]));
			list.add(new Node(building[1], -building[2]));
		}
		list.sort(new MyComparator());
		return list;
	}
}
