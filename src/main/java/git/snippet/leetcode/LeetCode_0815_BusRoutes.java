package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

// TODO
// 来自三七互娱(Leetcode原题)
// 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
// 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
// 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
// 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
// Leetcode原题 : https://leetcode.com/problems/bus-routes/
// Leetcode原题 : https://leetcode.com/problems/bus-routes/
public class LeetCode_0815_BusRoutes {

  // 0 : [1,3,7,0]
  // 1 : [7,9,6,2]
  // ....
  // 返回：返回换乘几次+1 -> 返回一共坐了多少条线的公交。
  public static int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) {
      return 0;
    }
    int n = routes.length;
    // key : 车站
    // value : list -> 该车站拥有哪些线路！
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < routes[i].length; j++) {
        if (!map.containsKey(routes[i][j])) {
          map.put(routes[i][j], new ArrayList<>());
        }
        map.get(routes[i][j]).add(i);
      }
    }
    ArrayList<Integer> queue = new ArrayList<>();
    boolean[] set = new boolean[n];
    for (int route : map.get(source)) {
      queue.add(route);
      set[route] = true;
    }
    int len = 1;
    while (!queue.isEmpty()) {
      ArrayList<Integer> nextLevel = new ArrayList<>();
      for (int route : queue) {
        int[] bus = routes[route];
        for (int station : bus) {
          if (station == target) {
            return len;
          }
          for (int nextRoute : map.get(station)) {
            if (!set[nextRoute]) {
              nextLevel.add(nextRoute);
              set[nextRoute] = true;
            }
          }
        }
      }
      queue = nextLevel;
      len++;
    }
    return -1;
  }
}
