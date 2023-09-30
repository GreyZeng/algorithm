package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// TODO
// 给定一个每一行有序、每一列也有序，整体可能无序的二维数组
// 在给定一个正数k
// 返回二维数组中，最小的第k个数
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class LeetCode_0378_KthSmallestElementInSortedMatrix {
  // 二分的方法
  // 收集两个信息：
  // 1. 小于等于target的数有几个
  // 2. 小于等于target离target最近的数是哪个
  // O((M+N)*log(Max-Min))
  public static int kthSmallest(int[][] matrix, int k) {
    int N = matrix.length;
    int M = matrix[0].length;
    int left = matrix[0][0];
    int right = matrix[N - 1][M - 1];
    int ans = 0;
    while (left <= right) {
      int mid = left + ((right - left) >> 1);
      // <=mid 有几个 <= mid 在矩阵中真实出现的数，谁最接近mid
      Info info = noMoreNum(matrix, mid);
      if (info.num < k) {
        left = mid + 1;
      } else {
        ans = info.near;
        right = mid - 1;
      }
    }
    return ans;
  }

  public static class Info {
    public int near;
    public int num;

    public Info(int n1, int n2) {
      near = n1;
      num = n2;
    }
  }

  public static Info noMoreNum(int[][] matrix, int value) {
    int near = Integer.MIN_VALUE;
    int num = 0;
    int N = matrix.length;
    int M = matrix[0].length;
    int row = 0;
    int col = M - 1;
    while (row < N && col >= 0) {
      if (matrix[row][col] <= value) {
        near = Math.max(near, matrix[row][col]);
        num += col + 1;
        row++;
      } else {
        col--;
      }
    }
    return new Info(near, num);
  }

  public static class Node {
    public int r;
    public int c;
    public int v;

    public Node(int r, int c, int v) {
      this.r = r;
      this.c = c;
      this.v = v;
    }
  }

  // 小根堆 按照值来组织
  public static int kthSmallest2(int[][] matrix, int k) {
    int M = matrix.length;
    int N = matrix[0].length;
    boolean[][] set = new boolean[M][N];
    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.v));
    queue.offer(new Node(0, 0, matrix[0][0]));
    set[0][0] = true;
    int count = 0;
    Node n = null;
    while (!queue.isEmpty()) {
      n = queue.poll();
      if (++count == k) {
        return n.v;
      }
      if (n.c + 1 < N && !set[n.r][n.c + 1]) {
        set[n.r][n.c + 1] = true;
        queue.offer(new Node(n.r, n.c + 1, matrix[n.r][n.c + 1]));
      }
      if (n.r + 1 < M && !set[n.r + 1][n.c]) {
        set[n.r + 1][n.c] = true;
        queue.offer(new Node(n.r + 1, n.c, matrix[n.r + 1][n.c]));
      }
    }
    return n.v;
  }
}
