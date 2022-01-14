package leetcode.medium;

// https://leetcode.com/problems/number-of-provinces/
public class LeetCode_0547_NumberOfProvinces {
    // m是正方形矩阵
    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // {0} {1} {2} {N-1}
        UnionFind unionFind = new UnionFind(N);
        // 只需要遍历对角线上半区即可
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) { // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        // 辅助结构
        private int[] help;
        // 一共有多少个集合
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            index--;
            for (; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                sets--;
                int s1 = size[i];
                int s2 = size[j];
                if (s1 > s2) {
                    size[f1] = s1 + s2;
                    size[f2] = 0;
                    parent[f2] = f1;
                } else {
                    size[f2] = 0;
                    size[f1] = s1 + s2;
                    parent[f1] = f2;
                }
            }
        }

        public int sets() {
            return sets;
        }
    }
}
