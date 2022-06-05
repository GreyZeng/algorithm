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
package lintcode.medium;

import leetcode.medium.LeetCode_0261_GraphValidTree;

// https://www.lintcode.com/problem/178/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LintCode_0178_GraphValidTree {

    public static boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return true;
        }
        if (n - 1 != edges.length) {
            return false;
        }
        LeetCode_0261_GraphValidTree.UnionFind uf = new LeetCode_0261_GraphValidTree.UnionFind(n);
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
