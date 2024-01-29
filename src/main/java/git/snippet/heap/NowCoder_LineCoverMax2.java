package git.snippet.heap;

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
public class NowCoder_LineCoverMax2 {
    // 暴力解法，无法过牛客测评，可以通过对数器来验证
    public static int maxCover(int[][] lines) {
        if (null == lines || lines.length == 0) {
            return 0;
        }
        int min = lines[0][0];
        int max = lines[0][1];
        for (int i = 1; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int maxCover = 0;
        for (int i = min; i <= max; i++) {
            int cover = 0;
            for (int[] line : lines) {
                if (line[0] <= i + 0.1 && line[1] >= i + 0.1) {
                    cover++;
                }
            }
            maxCover = Math.max(maxCover, cover);
        }
        return maxCover;
    }

    // 堆解法
    public static int maxCover2(int[][] lines) {
        if (null == lines || lines.length == 0) {
            return 0;
        }
        Arrays.sort(lines, Comparator.comparingInt(line -> line[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(line -> line[1]));
        int max = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek()[1] <= line[0]) {
                heap.poll();
            }
            heap.offer(line);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        //        Scanner in = new Scanner(System.in);
        //        int N = in.nextInt();
        //        int[][] lines = new int[N][2];
        //        for (int i = 0; i < N; i++) {
        //            lines[i][0] = in.nextInt();
        //            lines[i][1] = in.nextInt();
        //        }
        //        System.out.println(maxCover2(lines));
        //        in.close();
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover(lines);
            int ans3 = maxCover2(lines);
            if (ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }
}
