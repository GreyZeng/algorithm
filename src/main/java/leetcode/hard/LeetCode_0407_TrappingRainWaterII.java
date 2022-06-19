package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

//Constraints:
//
//        1 <= m, n <= 110
//        0 <= heightMap[i][j] <= 20000
public class LeetCode_0407_TrappingRainWaterII {
    public static class Node {
        public int value;
        public int i;
        public int j;

        public Node(int v, int i, int j) {
            value = v;
            this.i = i;
            this.j = j;
        }
    }

    public static class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }


    public static int trapRainWater(int[][] heightMap) {
        // 0. 准备一个全局max
        int max = 0;
        // 2. 加入过的标志一下
        // 3. 从小根堆弹出一个数字（这个数字必定为周围一圈最薄弱的点）
        // 4. 然后把这个数字的上下左右都加入小根堆，标志为加入过的状态
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] isEntered = new boolean[m][n];
        PriorityQueue<Node> heap = new PriorityQueue<>(new MyComparator());

        // 1. 把周围一圈加入小根堆,加入的点标注为true
        for (int i = 0; i < m; i++) {
            heap.offer(new Node(heightMap[i][0], i, 0));
            heap.offer(new Node(heightMap[i][n - 1], i, n - 1));
            isEntered[i][0] = true;
            isEntered[i][n - 1] = true;
        }
        for (int i = 1; i < n; i++) {
            heap.offer(new Node(heightMap[0][i], 0, i));
            heap.offer(new Node(heightMap[m - 1][i], m - 1, i));
            isEntered[0][i] = true;
            isEntered[m - 1][i] = true;
        }
        int water = 0;
        while (!heap.isEmpty()) {
            Node weakest = heap.poll();
            max = Math.max(weakest.value, max);
            int i = weakest.i;
            int j = weakest.j;
            if (i + 1 < m && !isEntered[i + 1][j]) {
                if (max > heightMap[i + 1][j]) {
                    water += (max - heightMap[i + 1][j]);
                }
                heap.add(new Node(heightMap[i + 1][j], i + 1, j));
                isEntered[i + 1][j] = true;
            }
            if (i - 1 >= 0 && !isEntered[i - 1][j]) {

                if (max > heightMap[i - 1][j]) {
                    water += (max - heightMap[i - 1][j]);
                }
                heap.add(new Node(heightMap[i - 1][j], i - 1, j));
                isEntered[i - 1][j] = true;
            }
            if (j + 1 < n && !isEntered[i][j + 1]) {
                if (max > heightMap[i][j + 1]) {
                    water += (max - heightMap[i][j + 1]);
                }
                heap.add(new Node(heightMap[i][j + 1], i, j + 1));
                isEntered[i][j + 1] = true;
            }
            if (j - 1 >= 0 && !isEntered[i][j - 1]) {
                if (max > heightMap[i][j - 1]) {
                    water += (max - heightMap[i][j - 1]);
                }
                heap.add(new Node(heightMap[i][j - 1], i, j - 1));
                isEntered[i][j - 1] = true;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        System.out.println(trapRainWater(grid));
    }
}
