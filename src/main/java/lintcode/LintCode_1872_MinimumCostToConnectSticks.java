package lintcode;

import java.util.List;
import java.util.PriorityQueue;

// https://www.lintcode.com/problem/minimum-cost-to-connect-sticks/description
public class LintCode_1872_MinimumCostToConnectSticks {
	public static int MinimumCost(List<Integer> sticks) {
		if (null == sticks) {
			return 0;
		}
		if (sticks.isEmpty()) {
			return 0;
		}
		if (sticks.size() <= 2) {
			return sticks.get(0) + sticks.get(1);
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (Integer s : sticks) {
			queue.offer(s);
		}
		
		int cost = 0;
		while (queue.size() != 1) {
			int a = queue.poll();
			int b = queue.poll();
			cost += (a + b);
			queue.offer(a + b);
		}
		return cost;
		
		
	}
}
