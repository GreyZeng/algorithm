package leetcode.medium;

// OJ：https://leetcode.cn/problems/surrounded-regions/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0130_SurroundedRegions {
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                free(i, 0, board);
            }
            if (board[i][n - 1] == 'O') {
                free(i, n - 1, board);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                free(0, i, board);
            }
            if (board[m - 1][i] == 'O') {
                free(m - 1, i, board);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (board[i][j] != '#' ? 'X' : 'O');
            }
        }
    }

    public static void free(int i, int j, char[][] board) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        free(i + 1, j, board);
        free(i - 1, j, board);
        free(i, j + 1, board);
        free(i, j - 1, board);
    }

    // 以下为并查集解法
    public static void solve2(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        // 所有周边为O的点的代表节点都设置为dump
        int dump = 0;
        UF uf = new UF(m, n);
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(dump, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                uf.union(dump, i, n - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                uf.union(dump, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                uf.union(dump, m - 1, i);
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    int index = uf.index(i, j);
                    if (board[i - 1][j] == 'O') {
                        uf.union(index, i - 1, j);
                    }
                    if (board[i][j - 1] == 'O') {
                        uf.union(index, i, j - 1);
                    }
                    if (board[i + 1][j] == 'O') {
                        uf.union(index, i + 1, j);
                    }
                    if (board[i][j + 1] == 'O') {
                        uf.union(index, i, j + 1);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !uf.isSameSet(dump, i, j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static class UF {
        int[] help;
        int[] size;
        int[] parent;
        int row;
        int col;

        public UF(int m, int n) {
            row = m;
            col = n;
            int len = m * n + 1;
            help = new int[len];
            size = new int[len];
            parent = new int[len];
            for (int i = 1; i < len; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int index(int i, int j) {
            return i * col + j + 1;
        }

        private int find(int i) {
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

        public void union(int p1, int i, int j) {
            int p2 = index(i, j);
            int f1 = find(p1);
            int f2 = find(p2);
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
            }
        }

        public boolean isSameSet(int p, int i, int j) {
            return find(p) == find(index(i, j));
        }
    }
}
