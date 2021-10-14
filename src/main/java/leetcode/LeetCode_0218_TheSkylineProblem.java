package leetcode;

import java.util.*;
//【举例】
//		matrix ={{2,5,6}, {1,7,4}, {4,6,7}, {3,6,5}, {10,13,2}, {9,11,3}, {12,14,4},{10,12,5} }
//		返回: {{1,2,4},{2,4,6}, {4,6,7}, {6,7,4}, {9,10,3}, {10,12,5}, {12,14,4}}
// 有序表的使用
//tips:
//		最大高度的变化
//		开始变化的时候，最高高度开始变化，结算前一次的轮廓线
//		建立一组记录：
//		每个大楼的开始和结束点对应的最大高度
//		离散化处理
//
//		封装对象：
//		[3,7,6]  -> 3 + 6 , 7 - 6
//
//		第一个有序表 记录次数
//		第二个有序表 记录轮廓
//		为了防止纸片大楼，在排序的时候，把+放在-之前
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
				return o1.h > o2.h ? -1 : 1;
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
		for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
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
