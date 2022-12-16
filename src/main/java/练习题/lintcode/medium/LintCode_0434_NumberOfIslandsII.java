package 练习题.lintcode.medium;

import java.util.ArrayList;
import java.util.List;

// 描述
// 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 初始二维矩阵全0. 二元数组A内的k个元素代表k次操作,
// 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿. 问在每次操作之后, 二维矩阵中岛屿的数量.
// 你需要返回一个大小为k的数组.
//
// 设定0表示海洋, 1代表岛屿, 并且上下左右相邻的1为同一个岛屿.
// https://www.lintcode.com/problem/434/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LintCode_0434_NumberOfIslandsII {
  class Point {
    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }

  public static List<Integer> numIslands2(int n, int m, Point[] positions) {
    if (null == positions || positions.length == 0) {
      return new ArrayList<>();
    }
    UnionFind uf = new UnionFind(n, m);
    List<Integer> ans = new ArrayList<>();
    for (Point position : positions) {
      ans.add(uf.connect(position.x, position.y));
    }
    return ans;
  }

  public static class UnionFind {
    private int[] parent;
    private int[] size;
    private int[] help;
    private final int row;
    private final int col;
    private int sets;

    public UnionFind(int m, int n) {
      row = m;
      col = n;
      sets = 0;
      int len = row * col;
      parent = new int[len];
      size = new int[len];
      help = new int[len];
    }

    private int index(int r, int c) {
      return r * col + c;
    }

    private int find(int i) {
      int hi = 0;
      while (i != parent[i]) {
        help[hi++] = i;
        i = parent[i];
      }
      // 并查集的优化，扁平化
      for (hi--; hi >= 0; hi--) {
        parent[help[hi]] = i;
      }
      return i;
    }

    private void union(int r1, int c1, int r2, int c2) {
      if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0
          || c2 == col) {
        return;
      }
      int i1 = index(r1, c1);
      int i2 = index(r2, c2);
      if (size[i1] == 0 || size[i2] == 0) {
        return;
      }
      int f1 = find(i1);
      int f2 = find(i2);
      if (f1 != f2) {
        // 并查集的优化1，小挂大
        if (size[f1] >= size[f2]) {
          size[f1] += size[f2];
          parent[f2] = f1;
        } else {
          size[f2] += size[f1];
          parent[f1] = f2;
        }
        sets--;
      }
    }

    public int connect(int r, int c) {
      int index = index(r, c);
      if (size[index] == 0) {
        // 新加入节点
        parent[index] = index;
        size[index] = 1;
        sets++;
        // 把新加入节点和四面八方的节点union一下。
        union(r - 1, c, r, c);
        union(r + 1, c, r, c);
        union(r, c - 1, r, c);
        union(r, c + 1, r, c);
      }
      return sets;
    }

  }
}
