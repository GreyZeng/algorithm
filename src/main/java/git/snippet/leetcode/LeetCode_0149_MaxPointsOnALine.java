package git.snippet.leetcode;

import java.util.HashMap;
import java.util.Map;

// 给定两个数组arrx和arry，长度都为N。代表二维平面上有N个点，第i个点的x 坐标和y坐标分别为arrx[i]和arry[i]，返回求一条直线最多能穿过多少个点?
// tips:
// 必须穿过某个点的直线
// 假设某个点是a
// 1. a 和 x 重合
// 2. a 和 x 共y
// 3. a 和 x 共x
// 4. a 和 x 有斜率 如何表示斜率？ 最大公约数以后用字符串拼接
// https://leetcode-cn.com/problems/max-points-on-a-line/
// 笔记：https://www.cnblogs.com/greyzeng/p/16464473.html
public class LeetCode_0149_MaxPointsOnALine {

  public static int maxPoints(int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }
    if (points.length == 1) {
      return 1;
    }
    int max = 1;
    // map形如(3,(4,10)) 表示：斜率为3/4的点有10个
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      map.clear();
      int sameY = 1;
      int sameX = 1;
      int sameSlope = 1;
      int x1 = points[i][0];
      int y1 = points[i][1];
      for (int j = i + 1; j < points.length; j++) {
        int x2 = points[j][0];
        int y2 = points[j][1];
        if (x2 == x1) {
          sameX++;
        } else if (y2 == y1) {
          sameY++;
        } else {
          int rangeY = y2 - y1;
          int rangeX = x2 - x1;
          int z = gcd(rangeX, rangeY);
          rangeY = rangeY / z;
          rangeX = rangeX / z;
          if (map.containsKey(rangeY)) {
            Map<Integer, Integer> m = map.get(rangeY);
            if (m.containsKey(rangeX)) {
              m.put(rangeX, m.get(rangeX) + 1);
              map.put(rangeY, m);
            } else {
              m.put(rangeX, 2);
              map.put(rangeY, m);
            }
          } else {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(rangeX, 2);
            map.put(rangeY, m);
          }
          sameSlope = Math.max(map.get(rangeY).get(rangeX), sameSlope);
        }
        max = Math.max(max, Math.max(Math.max(sameX, sameY), sameSlope));
      }
    }
    return max;
  }

  private static int gcd(int m, int n) {
    return n == 0 ? m : gcd(n, m % n);
  }
}
