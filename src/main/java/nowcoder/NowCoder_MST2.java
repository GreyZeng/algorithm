package nowcoder;

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
public class NowCoder_MST2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] mn = in.nextLine().split(" ");
        int n = Integer.valueOf(mn[0]);
        int m = Integer.valueOf(mn[1]);
        int[][] graph = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] abc = in.nextLine().split(" ");
            // from
            graph[i][0] = Integer.valueOf(abc[0]);
            // to
            graph[i][1] = Integer.valueOf(abc[1]);
            // weight
            graph[i][2] = Integer.valueOf(abc[2]);
        }
        System.out.println(minCost(graph));
        in.close();
    }

    public static int minCost(int[][] graph) {
        int[][] mst = p(graph);
        int ans = 0;
        for (int i = 0; i < mst.length; i++) {
            ans += mst[i][2];
        }
        return ans;
    }

    // TODO p算法生成最小生成树
    public static int[][] p(int[][] graph) {
        return null;
    }

}