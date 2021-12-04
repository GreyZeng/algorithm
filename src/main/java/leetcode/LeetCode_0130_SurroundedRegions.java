package leetcode;

//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//		A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//		Example:
//
//		X X X X
//		X O O X
//		X X O X
//		X O X X
//		After running your function, the board should be:
//
//		X X X X
//		X X X X
//		X X X X
//		X O X X
//		Explanation:
//
//		Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
//		Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
//		Two cells are connected if they are adjacent cells connected horizontally or vertically.
public class LeetCode_0130_SurroundedRegions {

    // 递归方法 感染函数 Leetcode测试1ms
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int M = board.length;
        int N = board[0].length;
        // 四周的O都可以被解救
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                free(board, i, 0);
            }
            if (board[i][N - 1] == 'O') {
                free(board, i, N - 1);
            }
        }
        for (int i = 0; i < N; i++) {
            if (board[0][i] == 'O') {
                free(board, 0, i);
            }
            if (board[M - 1][i] == 'O') {
                free(board, M - 1, i);
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '#') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void free(char[][] board, int i, int j) {
        if (!inValid(board, i, j)) {
            return;
        }
        board[i][j] = '#';
        free(board, i, j - 1);
        free(board, i, j + 1);
        free(board, i + 1, j);
        free(board, i - 1, j);
    }

    public static boolean inValid(char[][] board, int i, int j) {
        return i >= 0 && j >= 0 && i <= board.length - 1 && j <= board[0].length - 1 && board[i][j] == 'O';
    }

    // 以下为并查集解法 LeetCode 21ms
    public static void solve2(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int M = board.length;
        int N = board[0].length;
        UnionFind unionFind = new UnionFind(M * N + 1);
        int dump = 0;

        // 以下两个for循环把四周的O节点的代表点设置为dump
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                unionFind.union(dump, oneArrIndex(M, N, i, 0));
            }
            if (board[i][N - 1] == 'O') {
                unionFind.union(dump, oneArrIndex(M, N, i, N - 1));
            }
        }
        for (int i = 0; i < N; i++) {
            if (board[0][i] == 'O') {
                unionFind.union(dump, oneArrIndex(M, N, 0, i));
            }
            if (board[M - 1][i] == 'O') {
                unionFind.union(dump, oneArrIndex(M, N, M - 1, i));
            }
        }
        for (int i = 1; i < M - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (board[i][j] == 'O') {
                    int t = oneArrIndex(M, N, i, j);
                    if (board[i][j + 1] == 'O') {
                        unionFind.union(t, oneArrIndex(M, N, i, j + 1));
                    }
                    if (board[i][j - 1] == 'O') {
                        unionFind.union(t, oneArrIndex(M, N, i, j - 1));
                    }
                    if (board[i + 1][j] == 'O') {
                        unionFind.union(t, oneArrIndex(M, N, i + 1, j));
                    }
                    if (board[i - 1][j] == 'O') {
                        unionFind.union(t, oneArrIndex(M, N, i - 1, j));
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'O' && !unionFind.isSameSet(dump, oneArrIndex(M, N, i, j))) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 将二维坐标转换成一维坐标
    public static int oneArrIndex(int M, int N, int i, int j) {
        return i * N + j + 1;
    }

    public static class UnionFind {

        private int[] records;
        private int[] size;
        public UnionFind(int n) {
            // n的代表点就是records[n],因为二维数组的下标可以转换成一维数组下标（从1开始），所以可以将二维数组某个点的代表点用records[n]表示
            // 其中n = oneArrIndex(i,j)
            records = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                records[i] = i;
                size[i] = 1;
            }
        }

        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }

        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b); 
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

    public static void main(String[] args) {
        int MAX_ROW = 10000;
        int MAX_COL = 10000;
        int times = 100000;
        long s;
        long e;
        for (int i = 0; i < times; i++) {
            char[][] arr = generateCharArr(MAX_ROW, MAX_COL);
            char[][] arr1 = copyArr(arr);
            char[][] arr2 = copyArr(arr);
            s = System.currentTimeMillis();
            solve(arr1);
            e = System.currentTimeMillis();
            System.out.println("递归方法时间：" + (e - s));
            s = System.currentTimeMillis();
            solve2(arr2);
            e = System.currentTimeMillis();
            System.out.println("并查集方法时间：" + (e - s));
        }
    }

    public static char[][] generateCharArr(int r, int c) {
        char[][] res = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (Math.random() > 0.5) {
                    res[i][j] = 'X';
                } else {
                    res[i][j] = 'O';
                }
            }
        }
        return res;
    }

    
    public static char[][] copyArr(char[][] c) {
        char[][] res = new char[c.length][c[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                if (Math.random() > 0.5) {
                    res[i][j] = 'X';
                } else {
                    res[i][j] = 'O';
                }
            }
        }
        return res;
    }

}
