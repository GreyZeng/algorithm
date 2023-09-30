package lintcode;

// https://www.lintcode.com/problem/477/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LintCode_0477_SurroundedRegions {
  public static void surroundedRegions(char[][] board) {
    if (null == board || board.length == 0 || board[0].length == 0) {
      return;
    }
    int row = board.length;
    int col = board[0].length;
    // 四周的O可以将联通的O解救出来
    for (int i = 0; i < row; i++) {
      if (board[i][0] == 'O') {
        free(board, i, 0);
      }
      if (board[i][col - 1] == 'O') {
        free(board, i, col - 1);
      }
    }
    // 四周的O可以将联通的O解救出来
    for (int i = 0; i < col; i++) {
      if (board[0][i] == 'O') {
        free(board, 0, i);
      }
      if (board[row - 1][i] == 'O') {
        free(board, row - 1, i);
      }
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j] != '#') {
          board[i][j] = 'X';
        } else {
          board[i][j] = 'O';
        }
      }
    }
  }

  public static void free(char[][] board, int i, int j) {
    int row = board.length;
    int col = board[0].length;
    if (i >= row || i < 0 || j >= col || j < 0 || board[i][j] != 'O') {
      return;
    }
    board[i][j] = '#';
    free(board, i + 1, j);
    free(board, i - 1, j);
    free(board, i, j + 1);
    free(board, i, j - 1);
  }

  public static void surroundedRegions2(char[][] board) {
    if (null == board || board.length == 0 || board[0].length == 0) {
      return;
    }
    int M = board.length;
    int N = board[0].length;
    UnionFind unionFind = new UnionFind(M * N);
    int dump = -1;
    // 以下两个for循环把四周的O节点的代表点设置为dump
    for (int i = 0; i < M; i++) {
      if (board[i][0] == 'O') {
        if (dump != -1) {
          unionFind.union(dump, oneArrIndex(M, N, i, 0));
        } else {
          dump = oneArrIndex(M, N, i, 0);
        }
      }
      if (board[i][N - 1] == 'O') {
        if (dump != -1) {
          unionFind.union(dump, oneArrIndex(M, N, i, N - 1));
        } else {
          dump = oneArrIndex(M, N, i, N - 1);
        }
      }
    }
    for (int i = 0; i < N; i++) {
      if (board[0][i] == 'O') {
        if (dump != -1) {
          unionFind.union(dump, oneArrIndex(M, N, 0, i));
        } else {
          dump = oneArrIndex(M, N, 0, i);
        }
      }
      if (board[M - 1][i] == 'O') {
        if (dump != -1) {
          unionFind.union(dump, oneArrIndex(M, N, M - 1, i));
        } else {
          dump = oneArrIndex(M, N, M - 1, i);
        }
      }
    }
    if (dump == -1) {
      // dump = -1 表示四周都是X，所以整个二维数组的元素都是X
      for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
          board[i][j] = 'X';
        }
      }
      return;
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
    return i * N + j;
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
          // size[fb] = all;
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
        int m = records[t];
        t = m;
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
