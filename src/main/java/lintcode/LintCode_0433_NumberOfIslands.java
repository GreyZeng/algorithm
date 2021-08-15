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
            if (board[i - 1][0] && board[i][0]) {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j]) {
                    if (board[i][j - 1]) {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j]) {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.getSets();
    }

    // M行N列的二维数组映射成一维数组的位置

    public static class UnionFind {
        private int[] records;
        private int[] size;
        private int sets;
        private int col;

        public UnionFind(int row, int col, boolean[][] board) {
            this.col = col;
            int volume = row * col;
            records = new int[volume];
            size = new int[volume];
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j]) {
                        int a = map(i, j);
                        records[a] = a;
                        size[a] = 1;
                        sets++;
                    }
                }

            }
        }

        public int getSets() {
            return sets;
        }

        public int map(int i, int j) {
            return this.col * i + j;
        }

        public void union(int x, int y, int x2, int y2) {
            int a = find(map(x, y));
            int b = find(map(x2, y2));
            if (a == b) {
                return;
            }
            int sizeA = size[a];
            int sizeB = size[b];
            if (sizeA > sizeB) {
                records[a] = b;
                size[a] = sizeA + sizeB;
                sets--;
            } else {
                records[b] = a;
                size[b] = sizeA + sizeB;
                sets--;
            }
        }

        private int find(int a) {
            int rA = a;
            while (rA != records[rA]) {
                rA = records[rA];
            }
            int ans = rA;
            while (a != rA) {
                int m = records[a];
                records[a] = rA;
                a = m;
            }
            return ans;
        }
    }
}
