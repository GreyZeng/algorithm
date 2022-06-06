package leetcode.hard;

// https://leetcode.cn/problems/bricks-falling-when-hit/
// 笔记：https://www.cnblogs.com/greyzeng/p/16343068.html
public class LeetCode_0803_BricksFallingWhenHit {

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2;
            }
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }

    // 并查集
    public static class UnionFind {
        private int row;
        private int col;
        // 连在天花板上的集合的元素个数
        private int cellingAll;
        // 原始矩阵
        private int[][] grid;
        // 是否连在天花板上
        private boolean[] cellingSet;
        private int[] parents;
        private int[] size;
        private int[] help;

        public UnionFind(int[][] matrix) {
            grid = matrix;
            row = grid.length;
            col = grid[0].length;
            int all = row * col;
            cellingAll = 0;
            cellingSet = new boolean[all];
            parents = new int[all];
            size = new int[all];
            help = new int[all];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * col + j;
                        parents[index] = index;
                        size[index] = 1;
                        if (i == 0) {
                            cellingSet[index] = true;
                            cellingAll++;
                        }
                    }
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        union(i, j, i - 1, j);
                        union(i, j, i + 1, j);
                        union(i, j, i, j - 1);
                        union(i, j, i, j + 1);
                    }
                }
            }
        }

        private int index(int i, int j) {
            return i * col + j;
        }

        private int find(int i) {
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

        private void union(int r1, int c1, int r2, int c2) {
            if (valid(r1, c1) && valid(r2, c2)) {
                int father1 = find(index(r1, c1));
                int father2 = find(index(r2, c2));
                if (father1 != father2) {
                    int size1 = size[father1];
                    int size2 = size[father2];
                    boolean status1 = cellingSet[father1];
                    boolean status2 = cellingSet[father2];
                    if (size1 <= size2) {
                        parents[father1] = father2;
                        size[father2] = size1 + size2;
                        if (status1 ^ status2) {
                            cellingSet[father2] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    } else {
                        parents[father2] = father1;
                        size[father1] = size1 + size2;
                        if (status1 ^ status2) {
                            cellingSet[father1] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    }
                }
            }
        }

        private boolean valid(int row, int col) {
            return row >= 0 && row < this.row && col >= 0 && col < this.col;
        }

        public int finger(int i, int j) {
            grid[i][j] = 1;
            int cur = i * col + j;
            if (i == 0) {
                cellingSet[cur] = true;
                cellingAll++;
            }
            parents[cur] = cur;
            size[cur] = 1;
            int pre = cellingAll;
            union(i, j, i - 1, j);
            union(i, j, i + 1, j);
            union(i, j, i, j - 1);
            union(i, j, i, j + 1);
            int now = cellingAll;
            if (i == 0) {
                return now - pre;
            } else {
                return now == pre ? 0 : now - pre - 1;
            }
        }
    }
}
