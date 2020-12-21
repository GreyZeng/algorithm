package lintcode;

import java.util.List;
import java.util.PriorityQueue;

// https://www.lintcode.com/problem/minimum-cost-to-connect-sticks/description
public class LintCode_1872_MinimumCostToConnectSticks {
	public static int MinimumCost(List<Integer> sticks) {
		if (sticks == null || sticks.size() <=1) {
			return 0;
		}
		if (sticks.size() == 2) {
			return sticks.get(1) + sticks.get(0);
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (Integer i : sticks) {
			queue.add(i);
		}
		int cost = 0 ;
		while (queue.size() > 1) {
			int i = queue.poll();
			int j = queue.poll();
			cost += (i + j);
			queue.add(i+j);
		}
		
		return cost;
	} 
}
