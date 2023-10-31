package git.snippet.leetcode;

// 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
// 一块砖直接连接到网格的顶部，或者至少有一块相邻（4个方向之一）砖块稳定不会掉落时
// 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失
// 然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）
// 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目
// 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
// Leetcode题目：https://leetcode.com/problems/bricks-falling-when-hit/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0803_BricksFallingWhenHit {

  public static int[] hitBricks(int[][] grid, int[][] hits) {
    for (int[] hit : hits) {
      if (grid[hit[0]][hit[1]] == 1) {
        grid[hit[0]][hit[1]] = 2;
      }
    }
    UnionFind unionFind = new UnionFind(grid);
    int[] ans = new int[hits.length];
    for (int i = hits.length - 1; i >= 0; i--) {
      if (grid[hits[i][0]][hits[i][1]] == 2) {
        ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
      }
    }
    return ans;
  }

  // 并查集
  public static class UnionFind {
    private int row;
    private int col;
    // 连在天花板上的集合的元素个数
    private int cellingAll;
    // 原始矩阵
    private int[][] grid;
    // 是否连在天花板上
    private boolean[] cellingSet;
    private int[] parents;
    private int[] size;
    private int[] help;

    public UnionFind(int[][] matrix) {
      grid = matrix;
      row = grid.length;
      col = grid[0].length;
      int all = row * col;
      cellingAll = 0;
      cellingSet = new boolean[all];
      parents = new int[all];
      size = new int[all];
      help = new int[all];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (grid[i][j] == 1) {
            int index = i * col + j;
            parents[index] = index;
            size[index] = 1;
            if (i == 0) {
              cellingSet[index] = true;
              cellingAll++;
            }
          }
        }
      }
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (grid[i][j] == 1) {
            union(i, j, i - 1, j);
            union(i, j, i + 1, j);
            union(i, j, i, j - 1);
            union(i, j, i, j + 1);
          }
        }
      }
    }

    private int index(int i, int j) {
      return i * col + j;
    }

    private int find(int i) {
      int hi = 0;
      while (i != parents[i]) {
        help[hi++] = i;
        i = parents[i];
      }
      for (int j = 0; j < hi; j++) {
        parents[help[j]] = i;
      }
      return i;
    }

    private void union(int r1, int c1, int r2, int c2) {
      if (valid(r1, c1) && valid(r2, c2)) {
        int father1 = find(index(r1, c1));
        int father2 = find(index(r2, c2));
        if (father1 != father2) {
          int size1 = size[father1];
          int size2 = size[father2];
          boolean status1 = cellingSet[father1];
          boolean status2 = cellingSet[father2];
          if (size1 <= size2) {
            parents[father1] = father2;
            size[father2] = size1 + size2;
            if (status1 ^ status2) {
              cellingSet[father2] = true;
              cellingAll += status1 ? size2 : size1;
            }
          } else {
            parents[father2] = father1;
            size[father1] = size1 + size2;
            if (status1 ^ status2) {
              cellingSet[father1] = true;
              cellingAll += status1 ? size2 : size1;
            }
          }
        }
      }
    }

    private boolean valid(int row, int col) {
      return row >= 0 && row < this.row && col >= 0 && col < this.col;
    }

    public int finger(int i, int j) {
      grid[i][j] = 1;
      int cur = i * col + j;
      if (i == 0) {
        cellingSet[cur] = true;
        cellingAll++;
      }
      parents[cur] = cur;
      size[cur] = 1;
      int pre = cellingAll;
      union(i, j, i - 1, j);
      union(i, j, i + 1, j);
      union(i, j, i, j - 1);
      union(i, j, i, j + 1);
      int now = cellingAll;
      if (i == 0) {
        return now - pre;
      } else {
        return now == pre ? 0 : now - pre - 1;
      }
    }
  }
}
