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
// P算法
public class NowCoder_MST2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[m][3];
        for (int i = 0; i < m; i++) {
            // from
            graph[i][0] = in.nextInt();
            // to
            graph[i][1] = in.nextInt();
            // weight
            graph[i][2] = in.nextInt();
        }
        System.out.println(p(graph, n));
        in.close();
    }

    // P算法生成最小生成树
    public static int p(int[][] graph, int n) {
        return -1;
    }

}