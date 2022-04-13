package leetcode.hard;

import java.util.HashMap;

//给定两个数组arrx和arry，长度都为N。代表二维平面上有N个点，第i个点的x 坐标和y坐标分别为arrx[i]和arry[i]，返回求一条直线最多能穿过多少个点?
//        tips:
//        必须穿过某个点的直线
//        假设某个点是a
//        1. a 和 x 重合
//        2. a 和 x 共y
//        3. a 和 x 共x
//        4. a 和 x 有斜率 如何表示斜率？ 最大公约数以后用字符串拼接
// https://leetcode-cn.com/problems/max-points-on-a-line/
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
