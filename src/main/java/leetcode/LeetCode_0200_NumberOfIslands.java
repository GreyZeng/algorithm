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
public class LeetCode_0200_NumberOfIslands {

    // 方法1 感染函数 O(N*M)
    // 方法2 并查集
    public static int numIslands(char[][] m) {
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


    // TODO 使用并查集
    public static int numIslands2(char[][] m) {
       return -1;
    }

}
