## 题目描述

[题目链接](https://leetcode.com/problems/number-of-islands/)

### 思路1 感染函数


遍历二维数组，设定一个全局变量s，表示岛屿数量，初始化为0，遇到字符'1'就加将岛屿的数量+1（即s++），

同时，把这个字符'1'及其周围的所有字符'1'都“感染”成其他的值，比如变为'.' 这个字符，最后s的值即岛屿的数量。

代码和注释见：


```java
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
```


### 思路2 并查集


暂时不用理解并查集这个结构的细节，我们只需要知道，并查集可以提供如下两个方法，且复杂度均为O(1)


```java
// 查询样本x和样本y是否属于一个集合
public boolean isSameSet(V x,V y)
// 把x和y各自所在集合的所有样本合并成一个集合
public void union(V x,V y) 
// 返回一共的集合数量（在这题下含义就是岛屿的数量）
public int getSets()
```


我们可以先把第0行和第0列中'1'的字符拿到并建立集合，

然后从第一行第一列开始遍历剩余位置，如果当前位置是'1'则看其周围有没有已经建好的'1'集合，

有则union操作，

无则单独新建一个集合。

代码如下（把并查集当作黑盒）：


```java
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
```


接下来看具体的并查集的实现，并查集除了常规的扁平化优化操作以外，还可以做一个小的优化，

可以将二维数组变成一维数组，这样并查集中的size数组不需要设置为二维数组，具体转换方式是

任意MxN的二维数组，(i,j)位置，可以转换成一维数组的i * N + j + 1位置


```java
public static int oneArrIndex(int M, int N, int i, int j) {
   return i * N + j + 1;
}
```


完整并查集代码见：


```java
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
```


## 更多


[算法和数据结构笔记](https://github.com/GreyZeng/algorithm)


## 参考资料


- [程序员代码面试指南（第2版）](https://book.douban.com/subject/30422021/)
- [算法和数据结构基础班-左程云](https://ke.qq.com/course/2145184)
- [极客时间-数据结构与算法之美-王争](https://time.geekbang.org/column/intro/126)
- [算法(第四版)](https://book.douban.com/subject/19952400/)
