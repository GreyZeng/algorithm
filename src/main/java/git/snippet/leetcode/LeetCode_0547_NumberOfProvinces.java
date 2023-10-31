package git.snippet.leetcode;

// https://leetcode.com/problems/number-of-provinces/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0547_NumberOfProvinces {
  public static int findCircleNum(int[][] m) {
    int n = m.length;
    UF uf = new UF(n);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (m[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }
    return uf.setSize();
  }

  public static class UF {
    int[] parent;
    int[] help;
    int[] size;
    int sets;

    public UF(int n) {
      size = new int[n];
      parent = new int[n];
      help = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
      sets = n;
    }

    public void union(int i, int j) {
      if (i == j) {
        return;
      }
      int p1 = find(i);
      int p2 = find(j);
      if (p2 != p1) {
        int size1 = size[p1];
        int size2 = size[p2];
        if (size1 > size2) {
          parent[p2] = p1;
          size[p1] += size2;
        } else {
          parent[p1] = p2;
          size[p2] += size1;
        }
        sets--;
      }
    }

    public int find(int i) {
      int hi = 0;
      while (i != parent[i]) {
        help[hi++] = i;
        i = parent[i];
      }
      for (int index = 0; index < hi; index++) {
        parent[help[index]] = i;
      }
      return i;
    }

    public int setSize() {
      return sets;
    }
  }
}
