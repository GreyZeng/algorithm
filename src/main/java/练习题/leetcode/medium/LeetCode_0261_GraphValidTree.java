package 练习题.leetcode.medium;

// https://leetcode.cn/problems/graph-valid-tree/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0261_GraphValidTree {
  public static boolean validTree(int n, int[][] edges) {
    if (n == 0) {
      return true;
    }
    if (n - 1 != edges.length) {
      return false;
    }
    UnionFind uf = new UnionFind(n);
    for (int[] edge : edges) {
      uf.union(edge[0], edge[1]);
    }
    return uf.setSize() == 1;
  }

  // 如何判断环？ 如果一个node节点中两个点的代表点一样，说明出现了环，直接返回false
  public static class UnionFind {
    private int[] parents;
    private int[] size;
    private int[] help;
    private int sets;

    public UnionFind(int n) {
      parents = new int[n];
      size = new int[n];
      help = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
        size[i] = 1;
      }
      sets = n;
    }

    public int find(int i) {
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

    public void union(int i, int j) {
      int f1 = find(i);
      int f2 = find(j);
      if (f1 != f2) {
        int s1 = size[f1];
        int s2 = size[f2];
        if (s1 < s2) {
          parents[f1] = parents[f2];
          size[f2] += s1;
        } else {
          parents[f2] = parents[f1];
          size[f1] += s2;
        }
        sets--;
      }
    }

    public int setSize() {
      return sets;
    }
  }
}
