package leetcode;

import java.util.HashMap;

//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
//        Example 1:
//
//        Input: [[1,1],[2,2],[3,3]]
//        Output: 3
//        Explanation:
//        ^
//        |
//        |        o
//        |     o
//        |  o
//        +------------->
//        0  1  2  3  4
//        Example 2:
//
//        Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//        Output: 4
//        Explanation:
//        ^
//        |
//        |  o
//        |     o        o
//        |        o
//        |  o        o
//        +------------------->
//        0  1  2  3  4  5  6
//        NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
public class LeetCode_0149_MaxPointsOnALine {

    // 共位置
    // 共享斜率
    // 斜率表 最简分数来表示斜率（拼接成字符串）
    // 点关系
    //   1. 共位置
    //   2. 共斜率
    public static int maxPoints(int[][] points) {
        if (null == points || points.length == 0) {
            return 0;
        }
        if (points.length == 1) {
            return 1;
        }
        // HashMap<String, HashSet<Integer>> map = new HashMap<>(); // 这样的话效率要低一些
        int max = 0;
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int same = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0 && y == 0) {
                    same++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int z = gcd(x, y);
                    int m = x / z;
                    int n = y / z;

                    if (map.containsKey(m)) {
                        HashMap<Integer, Integer> t = map.get(m);
                        if (t.containsKey(n)) {
                            t.put(n, t.get(n) + 1);
                        } else {
                            t.put(n, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> t = new HashMap<>();
                        t.put(n, 1);
                        map.put(m, t);
                    }
                    line = Math.max(map.get(m).get(n), line);
                }

            }
            max = Math.max(Math.max(Math.max(sameX, sameY), line) + same, max);

        }
        return max;
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

}
