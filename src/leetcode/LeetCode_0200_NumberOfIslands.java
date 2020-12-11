package leetcode;

//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//
//
//        Example 1:
//
//        Input: grid = [
//        ["1","1","1","1","0"],
//        ["1","1","0","1","0"],
//        ["1","1","0","0","0"],
//        ["0","0","0","0","0"]
//        ]
//        Output: 1
//        Example 2:
//
//        Input: grid = [
//        ["1","1","0","0","0"],
//        ["1","1","0","0","0"],
//        ["0","0","1","0","0"],
//        ["0","0","0","1","1"]
//        ]
//        Output: 3
// 方法1 感染函数 O(N*M)[只能过LeetCode上这题，但是过不了牛客上NC109的这题：会出现栈溢出的错误，可以采用并查集的方式]
// 方法2 并查集 ，LeetCode和牛客上对应的题目都可以通过，不会出现栈溢出的情况
public class LeetCode_0200_NumberOfIslands {

    // 并查集解法
    public static int numIslands(char[][] board) {

        int row = board.length;
        int col = board[0].length;
        UnionFind uf = new UnionFind(row, col, board);
        for (int j = 1; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(0, j - 1, 0, j);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] == '1') {
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

        public UnionFind(int row, int col, char[][] board) {
            this.row = row;
            this.col = col;
            int n = row * col + 1;
            // n的代表点就是records[n],因为二维数组的下标可以转换成一维数组下标（从1开始），所以可以将二维数组某个点的代表点用records[n]表示
            // 其中n = oneArrIndex(i,j)
            records = new int[n];
            size = new int[n];

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
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

    public static int numIslands2(char[][] m) {
        if (null == m || 0 == m.length || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int M = m.length;
        int N = m[0].length;
        int s = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] == '1') {
                    s++;
                    infect(m, i, j, M, N);
                }
            }
        }

        return s;
    }

    // note，这里把M，N传进去和不传进去在leetcode中性能有差别，最好传进去，后续就不需要继续判断了
    private static void infect(char[][] m, int i, int j, int M, int N) {
        if (i < 0 || i >= M || j < 0 || j >= N || m[i][j] != '1') {
            return;
        }
        m[i][j] = '.';
        infect(m, i + 1, j, M, N);
        infect(m, i, j + 1, M, N);
        infect(m, i - 1, j, M, N);
        infect(m, i, j - 1, M, N);
    }

}
