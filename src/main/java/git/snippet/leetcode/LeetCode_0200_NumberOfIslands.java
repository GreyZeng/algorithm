package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is
// surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You
// may assume all four edges of the grid are all surrounded by water.
//
//
//
// Example 1:
//
// Input: grid = [
// ["1","1","1","1","0"],
// ["1","1","0","1","0"],
// ["1","1","0","0","0"],
// ["0","0","0","0","0"]
// ]
// Output: 1
// Example 2:
//
// Input: grid = [
// ["1","1","0","0","0"],
// ["1","1","0","0","0"],
// ["0","0","1","0","0"],
// ["0","0","0","1","1"]
// ]
// Output: 3
// 方法1 感染函数 O(N*M)[只能过LeetCode上这题，但是过不了牛客上NC109的这题：会出现栈溢出的错误，可以采用并查集的方式]
// 方法2 并查集 ，LeetCode和牛客上对应的题目都可以通过，不会出现栈溢出的情况
// lintcode 433
// https://leetcode-cn.com/problems/number-of-islands/
// 笔记：https://www.cnblogs.com/greyzeng/p/14118298.html
public class LeetCode_0200_NumberOfIslands {
    public static int numIslands3(char[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    ans++;
                    infect(board, i, j);
                }
            }
        }
        return ans;
    }

    // 感染函数做法
    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = '2';
        infect(board, i + 1, j);
        infect(board, i, j + 1);
        infect(board, i - 1, j);
        infect(board, i, j - 1);
    }

    public static int numIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind1<Dot> uf = new UnionFind1<>(dotList);
        for (int j = 1; j < col; j++) {
            // (0,j) (0,0)跳过了 (0,1) (0,2) (0,3)
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                uf.union(dots[0][j - 1], dots[0][j]);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                uf.union(dots[i - 1][0], dots[i][0]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(dots[i][j - 1], dots[i][j]);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(dots[i - 1][j], dots[i][j]);
                    }
                }
            }
        }
        return uf.sets();
    }

    // 并查集做法
    public static int numIslands2(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return 0;
        }
        int m = board.length;
        int n = board[0].length;
        UF uf = new UF(board);
        // 第一列
        for (int i = 1; i < m; i++) {
            //
            if (board[i][0] == '1' && board[i - 1][0] == '1') {
                uf.union(i - 1, 0, i, 0);
            }
        }
        // 第一行
        for (int i = 1; i < n; i++) {
            if (board[0][i] == '1' && board[0][i - 1] == '1') {
                uf.union(0, i - 1, 0, i);
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == '1') {
                    if (board[i - 1][j] == '1') {
                        uf.union(i, j, i - 1, j);
                    }
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return uf.setSize();
    }

    // 为了测试
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

    // 为了测试
    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;

        row = 1000;
        col = 1000;
        board1 = generateRandomMatrix(row, col);
        board2 = copy(board1);
        board3 = copy(board1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + numIslands1(board2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        System.out.println();

        row = 10000;
        col = 10000;
        board1 = generateRandomMatrix(row, col);
        board3 = copy(board1);
        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
    }

    public static class Dot {
    }

    public static class Node<V> {

        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    public static class UF {
        public int[] parent;
        // 替代队列，便于做扁平化优化
        public int[] help;
        // 记录大小，便于做小挂大优化
        public int[] size;
        public int sets;
        public int col;
        public int row;
        public int len;

        public UF(char[][] board) {
            row = board.length;
            col = board[0].length;
            len = row * col;
            parent = new int[len];
            help = new int[len];
            size = new int[len];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        sets++;
                        size[index] = 1;
                        parent[index] = index;
                    }
                }
            }
        }

        public int index(int i, int j) {
            return j + i * col;
        }

        public void union(int i, int j, int m, int n) {
            int index1 = index(i, j);
            int index2 = index(m, n);
            int p1 = find(index1);
            int p2 = find(index2);
            if (p1 != p2) {
                int size1 = size[p1];
                int size2 = size[p2];
                if (size1 > size2) {
                    parent[p2] = p1;
                    size[p1] += size2;
                } else {
                    // size1 <= size2
                    parent[p1] = p2;
                    size[p2] += size1;
                }
                sets--;
            }
        }

        // 找到代表节点，记得扁平化操作
        public int find(int i) {
            int p = 0;
            while (i != parent[i]) {
                // 只要i不等于其代表节点
                help[p++] = i;
                i = parent[i];
            }
            for (int index = 0; index < p; index++) {
                parent[help[index]] = i;
            }
            return i;
        }

        public int setSize() {
            return sets;
        }
    }
}
