package nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//链接：https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
//来源：牛客网
//
//输入第一行，两个整数n,m;
//接下来m行，每行三个整数a,b,c，表示有路连接编号为a和b的人家，修路要花费的代价为c。
//数据保证能将每户人家都连接起来。
//注意重边的情况。n≤10000, m≤100,000n\le 10000,\ m\le100,000n≤10000, m≤100,000，边权0<c≤100000< c\le100000<c≤10000。
// 输出最小的花费代价使得这n户人家连接起来。
// https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
public class NowCoder_MST1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] mn = in.nextLine().split(" ");
        int n = Integer.parseInt(mn[0]);
        int m = Integer.parseInt(mn[1]);
        int[][] graph = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] abc = in.nextLine().split(" ");
            // from
            graph[i][0] = Integer.parseInt(abc[0]);
            // to
            graph[i][1] = Integer.parseInt(abc[1]);
            // weight
            graph[i][2] = Integer.parseInt(abc[2]);
        }
        System.out.println(k(graph));
        in.close();
    }


    // k算法生成最小生成树
    public static int k(int[][] graph) {
        UnionFind uf = new UnionFind(graph);
        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
        int ans = 0;
        for (int[] edge : graph) {
            if (!uf.isSameSet(edge[0], edge[1])) {
                uf.union(edge[0], edge[1]);
                ans += edge[3];
            }
        }
        return ans;
    }
    // TODO
    public static class UnionFind {

        public UnionFind(int[][] graph) {

        }

        public boolean isSameSet(int a, int b) {
            return false;
        }

        public void union(int a, int b) {
            return;
        }
    }
}