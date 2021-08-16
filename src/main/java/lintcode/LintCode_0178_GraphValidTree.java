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
public class LintCode_0178_GraphValidTree {

    // 如何判断环？ 如果一个node节点中两个点的代表点一样，说明出现了环，直接返回false
    class UnionFind {
        private int[] records;
        private int[] size;
        private int group;

        public UnionFind(int n) {
            records = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                records[i] = i;
                size[i] = 1;
            }
            group = n;
        }

        public int find(int i) {
            int fx = i;
            while (fx != records[fx]) {
                fx = records[fx];
            }
            // fx就是其代表点
            // 扁平化操作
            while (i != fx) {
                int m = records[i];
                records[i] = fx;
                i = m;
            }
            return fx;
        }

        public void union(int i, int j) {
            int findI = find(i);
            int findJ = find(j);
            if (findI != findJ) {
                int sizeI = size[i];
                int sizeJ = size[j];
                if (sizeI < sizeJ) {
                    records[records[i]] = records[j];
                    size[j] = sizeI + sizeJ;
                } else {
                    records[records[j]] = records[i];
                    size[i] = sizeI + sizeJ;
                }
                group--;
            }
        }

        public int getGroup() {
            return group;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        // write your code here
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

        return uf.getGroup() == 1;
    }
}
