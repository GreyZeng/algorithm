package 练习题.nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 链接：https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
// 来源：牛客网
// 笔记：https://www.cnblogs.com/greyzeng/p/16814543.html
// 输入第一行，两个整数n,m;
// 接下来m行，每行三个整数a,b,c，表示有路连接编号为a和b的人家，修路要花费的代价为c。
// 数据保证能将每户人家都连接起来。
// 注意重边的情况。n≤10000, m≤100,000n\le 10000,\ m\le100,000n≤10000, m≤100,000，边权0<c≤100000<
// c\le100000<c≤10000。
// 输出最小的花费代价使得这n户人家连接起来。
// K算法
public class NowCoder_MST1 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] graph = new int[m][3];
    for (int i = 0; i < m; i++) {
      // from
      graph[i][0] = in.nextInt();
      // to
      graph[i][1] = in.nextInt();
      // weight
      graph[i][2] = in.nextInt();
    }
    System.out.println(k(graph, n));
    in.close();
  }

  // k算法生成最小生成树
  public static int k(int[][] graph, int n) {
    UnionFind uf = new UnionFind(n);
    Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
    int ans = 0;
    for (int[] edge : graph) {
      if (!uf.same(edge[0], edge[1])) {
        uf.union(edge[0], edge[1]);
        ans += edge[2];
      }
    }
    return ans;
  }

  public static class UnionFind {
    private final int[] parent;
    private final int[] size;
    private final int[] help;

    public UnionFind(int n) {
      parent = new int[n + 1];
      size = new int[n + 1];
      help = new int[n + 1];
      for (int i = 1; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    public boolean same(int a, int b) {
      return find(a) == find(b);
    }

    private int find(int a) {
      int index = 0;
      while (a != parent[a]) {
        help[index++] = a;
        a = parent[a];
      }
      index--;
      while (index > 0) {
        parent[help[index--]] = a;
      }
      return a;
    }

    public void union(int a, int b) {
      int f1 = find(a);
      int f2 = find(b);
      if (f1 != f2) {
        int size1 = size[f1];
        int size2 = size[f2];
        if (size1 > size2) {
          parent[f2] = f1;
          size[f2] = 0;
          size[f1] = size1 + size2;
        } else {
          parent[f1] = f2;
          size[f1] = 0;
          size[f2] = size1 + size2;
        }
      }
    }
  }
}
