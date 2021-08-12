package lintcode;

public class LintCode_0433_NumberOfIslands {
    // 使用感染函数
    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int s = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]) {
                    s++;
                    infect(grid, i, j, m, n);
                }
            }
        }
        return s;
    }

    public void infect(boolean[][] grid, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || !grid[i][j]) {
            return;
        }
        grid[i][j] = false;
        infect(grid, i + 1, j, m, n);
        infect(grid, i, j + 1, m, n);
        infect(grid, i - 1, j, m, n);
        infect(grid, i, j - 1, m, n);
    }

    public static int numIslands2(boolean[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int row = board.length;
        int col = board[0].length;
        UnionFind uf = new UnionFind(row, col, board);
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] && board[0][j]) {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0]  && board[i][0] ) {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] ) {
                    if (board[i][j - 1] ) {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] ) {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.getSets();
    }

    public static int oneArrIndex(int M, int N, int i, int j) {
        return i * N + j + 1;
    }

    public static class UnionFind {
        private int col;
        private int row;
        private int[] records;
        private int[] size;
        private int sets;

        public UnionFind(int row, int col, boolean[][] board) {
            this.row = row;
            this.col = col;
            int n = row * col + 1;
            // n的代表点就是records[n],因为二维数组的下标可以转换成一维数组下标（从1开始），所以可以将二维数组某个点的代表点用records[n]表示
            // 其中n = oneArrIndex(i,j)
            records = new int[n];
            size = new int[n];

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c]) {
                        int i = oneArrIndex(row, col, r, c);
                        records[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        public int getSets() {
            return sets;
        }

        public void union(int x, int y, int x2, int y2) {
            int fa = find(oneArrIndex(row, col, x, y));
            int fb = find(oneArrIndex(row, col, x2, y2));
            if (fa != fb) {
                int sizeFb = size[fb];
                int sizeFa = size[fa];
                int all = sizeFa + sizeFb;
                if (sizeFa > sizeFb) {
                    records[fb] = fa;
                    size[fa] = all;
                    //size[fb] = all;
                } else {
                    records[fa] = fb;
                    // size[fa] = all;
                    size[fb] = all;
                }
                sets--;
            }
        }

        private int find(int a) {
            int t = a;
            while (t != records[t]) {
                t = records[t];
            }
            int ans = t;
            // 扁平化操作
            while (a != t) {
                int m = records[a];
                records[m] = t;
                a = m;
            }
            return ans;
        }
    }
}
