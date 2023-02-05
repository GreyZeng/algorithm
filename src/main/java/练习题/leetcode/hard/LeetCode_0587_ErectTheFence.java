package 练习题.leetcode.hard;

import java.util.Arrays;

// TODO
// 凸包问题经典题
// 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
// 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
// leetcode题目：https://leetcode.cn/problems/erect-the-fence/
public class LeetCode_0587_ErectTheFence {

    public static int[][] outerTrees(int[][] points) {
        int n = points.length;
        int s = 0;
        int[][] stack = new int[n << 1][];
        // x小的排前面，x一样的，y小的排前面
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }
        // 去重返回
        Arrays.sort(stack, 0, s, (a, b) -> b[0] == a[0] ? b[1] - a[1] : b[0] - a[0]);
        n = 1;
        for (int i = 1; i < s; i++) {
            // 如果i点，x和y，与i-1点，x和y都一样
            // i点与i-1点，在同一个位置，此时，i点不保留
            if (stack[i][0] != stack[i - 1][0] || stack[i][1] != stack[i - 1][1]) {
                stack[n++] = stack[i];
            }
        }
        return Arrays.copyOf(stack, n);
    }

    // 叉乘的实现
    // 假设有a、b、c三个点，并且给出每个点的(x,y)位置
    // 从a到c的向量，在从a到b的向量的哪一侧？
    // 如果a到c的向量，在从a到b的向量右侧，返回正数
    // 如果a到c的向量，在从a到b的向量左侧，返回负数
    // 如果a到c的向量，和从a到b的向量重合，返回0
    // TODO
    // tips: 比如要判断一个点是否在三角形内部
    // 逆时针走下去的话，这个点始终在向量的左侧
    // 凸多边形，判断一个点是否在内部，也是类似的算法
    // 凹多边形，这个结论不成立
    public static int cross(int[] a, int[] b, int[] c) {
        return (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
    }

    public static void main(String[] args) {
        int[] a = {4, 4};
        int[] b = {1, 1};
        int[] c = {1, 5};
        System.out.println(cross(a, b, c));
    }

}
