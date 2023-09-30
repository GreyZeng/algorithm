package leetcode;

import java.util.HashMap;

// TODO
// 我们有 N 个与坐标轴对齐的矩形, 其中 N > 0, 判断它们是否能精确地覆盖一个矩形区域。
// 每个矩形用左下角的点和右上角的点的坐标来表示。例如， 一个单位正方形可以表示为 [1,1,2,2]。 ( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2) )。
// 示例 1:
// rectangles = [
// [1,1,3,3],
// [3,1,4,2],
// [3,2,4,4],
// [1,3,2,4],
// [2,3,3,4]
// ]
// 返回 true。5个矩形一起可以精确地覆盖一个矩形区域。
// 示例 2:
// rectangles = [
// [1,1,2,3],
// [1,3,2,4],
// [3,1,4,2],
// [3,2,4,4]
// ]
// 返回 false。两个矩形之间有间隔，无法覆盖成一个矩形。
// 示例 3:
// rectangles = [
// [1,1,3,3],
// [3,1,4,2],
// [1,3,2,4],
// [3,2,4,4]
// ]
// 返回 false。图形顶端留有间隔，无法覆盖成一个矩形。
// 示例 4:
// rectangles = [
// [1,1,3,3],
// [3,1,4,2],
// [1,3,2,4],
// [2,2,4,4]
// ]
// 返回 false。因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
// leetcode题目：https://leetcode.com/problems/perfect-rectangle/
// tips: 面积之和
// 结论：四个顶点一定只出现一次。内部所有点，一定出现偶数次

public class LeetCode_0391_PerfectRectangle {

  public static boolean isRectangleCover(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return false;
    }
    int l = Integer.MAX_VALUE;
    int r = Integer.MIN_VALUE;
    int d = Integer.MAX_VALUE;
    int u = Integer.MIN_VALUE;
    HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
    int area = 0;
    for (int[] rect : matrix) {
      add(map, rect[0], rect[1]);
      add(map, rect[0], rect[3]);
      add(map, rect[2], rect[1]);
      add(map, rect[2], rect[3]);
      area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
      l = Math.min(rect[0], l);
      d = Math.min(rect[1], d);
      r = Math.max(rect[2], r);
      u = Math.max(rect[3], u);
    }
    return checkPoints(map, l, d, r, u) && area == (r - l) * (u - d);
  }

  public static void add(HashMap<Integer, HashMap<Integer, Integer>> map, int row, int col) {
    if (!map.containsKey(row)) {
      map.put(row, new HashMap<>());
    }
    map.get(row).put(col, map.get(row).getOrDefault(col, 0) + 1);
  }

  public static boolean checkPoints(
      HashMap<Integer, HashMap<Integer, Integer>> map, int l, int d, int r, int u) {
    if (map.get(l).getOrDefault(d, 0) != 1
        || map.get(l).getOrDefault(u, 0) != 1
        || map.get(r).getOrDefault(d, 0) != 1
        || map.get(r).getOrDefault(u, 0) != 1) {
      return false;
    }
    map.get(l).remove(d);
    map.get(l).remove(u);
    map.get(r).remove(d);
    map.get(r).remove(u);
    for (int key : map.keySet()) {
      for (int value : map.get(key).values()) {
        if ((value & 1) != 0) {
          return false;
        }
      }
    }
    return true;
  }
}
