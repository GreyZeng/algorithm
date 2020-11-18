//描述
//给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
//
//你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中。
//
// 
//样例
//样例 1:
//
//输入: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
//输出: true.
//样例 2:
//
//输入: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
//输出: false.
package lintcode;

// https://www.lintcode.com/problem/graph-valid-tree/description
public class LintCode_0148_GraphValidTree {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        int n = 5;
        System.out.println(validTree(n, edges));
        n = 5;
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(validTree(n, edges2));
    }

    // 如何判断环？ 如果一个node节点中两个点的代表点一样，说明出现了环，直接返回false
    // TODO
    public static boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.isSameSet(edge[0], edge[1])) {
                return false;
            } else {
                uf.union(n, edge[0]);
                uf.union(n, edge[1]);
            }
        }
        return true;
    }

    public static class UnionFind {
        private int[] records;
        private int[] size;

        public UnionFind(int n) {
            records = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                records[i] = i;
                size[i] = 1;
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        private int find(int n) {
            int t = n;
            while (t != records[t]) {
                t = records[t];
            }
            int m = n;
            while (m != t) {
                records[m] = t;
                m = records[m];
            }
            return t;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                int s1 = size[f1];
                int s2 = size[f2];
                int all = s1 + s2;
                if (s1 < s2) {
                    records[f1] = f2;
                    size[f1] = all;
                } else {
                    records[f2] = f1;
                    size[f2] = all;
                }
            }
        }
    }
}
