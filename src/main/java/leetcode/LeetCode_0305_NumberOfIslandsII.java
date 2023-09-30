package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.cn/problems/number-of-islands-ii/
// LintCode 434
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0305_NumberOfIslandsII {

  public static List<Integer> numIslands21(int m, int n, int[][] positions) {
    UF uf = new UF(m, n);
    List<Integer> ans = new ArrayList<>();
    for (int[] position : positions) {
      ans.add(uf.connect(position[0], position[1]));
    }
    return ans;
  }

  public static class UF {
    int[] help;
    int[] parent;
    int[] size;
    int sets;
    int row;
    int col;

    public UF(int m, int n) {
      row = m;
      col = n;
      int len = m * n;
      help = new int[len];
      size = new int[len];
      parent = new int[len];
    }

    private int index(int i, int j) {
      return i * col + j;
    }

    private void union(int i1, int j1, int i2, int j2) {
      if (i1 < 0 || i2 < 0 || i1 >= row || i2 >= row || j1 < 0 || j2 < 0 || j1 >= col
          || j2 >= col) {
        return;
      }

      int f1 = index(i1, j1);
      int f2 = index(i2, j2);
      if (size[f1] == 0 || size[f2] == 0) {
        // 重要：如果两个都不是岛屿，则不用合并
        return;
      }
      f1 = find(f1);
      f2 = find(f2);
      if (f1 != f2) {
        int s1 = size[f1];
        int s2 = size[f2];
        if (s1 >= s2) {
          size[f1] += s2;
          parent[f2] = f1;
        } else {
          size[f2] += s1;
          parent[f1] = f2;
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

    public int connect(int i, int j) {
      int index = index(i, j);
      if (size[index] == 0) {
        sets++;
        size[index] = 1;
        parent[index] = index;
        // 去四个方向union
        union(i - 1, j, i, j);
        union(i, j - 1, i, j);
        union(i + 1, j, i, j);
        union(i, j + 1, i, j);
      }
      // index上本来就有岛屿，所以不需要处理
      return sets;
    }
  }

  public static List<Integer> numIslands22(int m, int n, int[][] positions) {
    UnionFind2 uf = new UnionFind2();
    List<Integer> ans = new ArrayList<>();
    for (int[] position : positions) {
      ans.add(uf.connect(position[0], position[1]));
    }
    return ans;
  }

  public static class UnionFind2 {
    private final HashMap<String, String> parent;
    private final HashMap<String, Integer> size;
    private final ArrayList<String> help;
    private int sets;

    public UnionFind2() {
      parent = new HashMap<>();
      size = new HashMap<>();
      help = new ArrayList<>();
      sets = 0;
    }

    private String find(String cur) {
      while (!cur.equals(parent.get(cur))) {
        help.add(cur);
        cur = parent.get(cur);
      }
      for (String str : help) {
        parent.put(str, cur);
      }
      help.clear();
      return cur;
    }

    private void union(String s1, String s2) {
      if (parent.containsKey(s1) && parent.containsKey(s2)) {
        String f1 = find(s1);
        String f2 = find(s2);
        if (!f1.equals(f2)) {
          int size1 = size.get(f1);
          int size2 = size.get(f2);
          String big = size1 >= size2 ? f1 : f2;
          String small = big == f1 ? f2 : f1;
          parent.put(small, big);
          size.put(big, size1 + size2);
          sets--;
        }
      }
    }

    public int connect(int r, int c) {
      String key = r + "_" + c;
      if (!parent.containsKey(key)) {
        parent.put(key, key);
        size.put(key, 1);
        sets++;
        String up = r - 1 + "_" + c;
        String down = r + 1 + "_" + c;
        String left = r + "_" + (c - 1);
        String right = r + "_" + (c + 1);
        union(up, key);
        union(down, key);
        union(left, key);
        union(right, key);
      }
      return sets;
    }
  }
}
