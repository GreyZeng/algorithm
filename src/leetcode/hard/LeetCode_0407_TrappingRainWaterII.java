package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;
//给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
//        Leetcode题目：https://leetcode.com/problems/trapping-rain-water-ii/
//Constraints:
//
// 1 <= m, n <= 110
// 0 <= heightMap[i][j] <= 20000
// https://www.lintcode.com/problem/364/
// 笔记：https://www.cnblogs.com/greyzeng/p/16418808.html
public class LeetCode_0407_TrappingRainWaterII {
    public static int trapRainWater(int[][] heightMap) {
        if (null == heightMap || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        int max = 0;
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.v));
        boolean[][] isEntered = new boolean[m][n];
        // 以下两个for循环，是把四周都加入小根堆
        for (int i = 0; i < m; i++) {
            heap.offer(new Node(heightMap[i][0], i, 0));
            heap.offer(new Node(heightMap[i][n - 1], i, n - 1));
            isEntered[i][0] = true;
            isEntered[i][n - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            heap.offer(new Node(heightMap[0][i], 0, i));
            heap.offer(new Node(heightMap[m - 1][i], m - 1, i));
            isEntered[0][i] = true;
            isEntered[m - 1][i] = true;
        }
        int water = 0;
        while (!heap.isEmpty()) {
            // 最薄弱的位置
            Node weakest = heap.poll();
            max = Math.max(weakest.v, max);
            int i = weakest.i;
            int j = weakest.j;
            if (i + 1 < m && !isEntered[i + 1][j]) {
                water += Math.max(0, max - heightMap[i + 1][j]);
                isEntered[i + 1][j] = true;
                heap.offer(new Node(heightMap[i + 1][j], i + 1, j));
            }
            if (i - 1 >= 0 && !isEntered[i - 1][j]) {
                water += Math.max(0, max - heightMap[i - 1][j]);
                isEntered[i - 1][j] = true;
                heap.offer(new Node(heightMap[i - 1][j], i - 1, j));
            }
            if (j + 1 < n && !isEntered[i][j + 1]) {
                water += Math.max(0, max - heightMap[i][j + 1]);
                isEntered[i][j + 1] = true;
                heap.offer(new Node(heightMap[i][j + 1], i, j + 1));
            }
            if (j - 1 >= 0 && !isEntered[i][j - 1]) {
                water += Math.max(0, max - heightMap[i][j - 1]);
                isEntered[i][j - 1] = true;
                heap.offer(new Node(heightMap[i][j - 1], i, j - 1));
            }
        }
        return water;
    }

    public static class Node {
        public int v;
        public int i;
        public int j;

        public Node(int v, int i, int j) {
            this.i = i;
            this.j = j;
            this.v = v;
        }
    }
}
