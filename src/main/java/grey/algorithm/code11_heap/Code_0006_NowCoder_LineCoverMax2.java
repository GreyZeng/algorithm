package grey.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 笔记：https://www.cnblogs.com/greyzeng/p/15058662.html
// https://www.nowcoder.com/questionTerminal/1ae8d0b6bb4e4bcdbf64ec491f63fc37
// 线段的最大重合区域问题
// 连接点不算重合部分
// 暴力解法
// 堆解法
// 线段树解法 TODO
public class Code_0032_NowCoder_LineCoverMax2 {
    private static int MAXN = 10001;
    private static int[][] lines = new int[MAXN][2];
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        n = (int) in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            lines[i][0] = (int) in.nval;
            in.nextToken();
            lines[i][1] = (int) in.nval;
        }
        out.println(maxCover());
        out.flush();
        out.close();
        br.close();
    }

    public static int maxCover() {
        // 按开始位置排序
        Arrays.sort(lines, 0, n, Comparator.comparingInt(line -> line[0]));
        // 以结束位置建立小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(line -> line[1]));
        int max = 0;
        for (int i = 0; i < n; i++) {
            // 如果交接点算重合区域，那么 heap.peek()[1] < lines[i][0]
            while (!heap.isEmpty() && heap.peek()[1] <= lines[i][0]) {
                heap.poll();
            }
            heap.offer(lines[i]);
            max = Math.max(max, heap.size());
        }
        return max;
    }
}
