package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
public class LeetCode_0787_CheapestFlightsWithinKStops {
  // Bellman Ford
  public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
    int[] cost = new int[n];
    Arrays.fill(cost, Integer.MAX_VALUE);
    cost[src] = 0;
    for (int i = 0; i <= k; i++) {
      int[] next = Arrays.copyOf(cost, n);
      for (int[] flight : flights) {
        if (cost[flight[0]] != Integer.MAX_VALUE) {
          next[flight[1]] = Math.min(next[flight[1]], cost[flight[0]] + flight[2]);
        }
      }
      cost = next;
    }
    return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
  }

  // 类似宽度优先遍历
  public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
    ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] line : flights) {
      graph.get(line[0]).add(new int[] {line[1], line[2]});
    }
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[src] = 0;
    HashMap<Integer, Integer> curMap = new HashMap<>();
    curMap.put(src, 0);
    for (int i = 0; i <= k; i++) {
      HashMap<Integer, Integer> nextMap = new HashMap<>();
      for (Map.Entry<Integer, Integer> entry : curMap.entrySet()) {
        int from = entry.getKey();
        int preCost = entry.getValue();
        for (int[] line : graph.get(from)) {
          int to = line[0];
          int curCost = line[1];
          distance[to] = Math.min(distance[to], preCost + curCost);
          nextMap.put(to, Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE), preCost + curCost));
        }
      }
      curMap = nextMap;
    }
    return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
  }
}
