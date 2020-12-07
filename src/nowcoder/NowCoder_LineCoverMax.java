//链接：https://www.nowcoder.com/questionTerminal/f8fa4b67dd054892966d280790c42ba3
//        来源：牛客网
//
//        每一个线段都有start和end两个数据项，表示这条线段在X轴上从start位置开始到end位置结束。给定一批线段，求所有重合区域中最多重合了几个线段。
//
//
//        输入描述:
//        第一行一个数N，表示有N条线段
//        接下来N行每行2个数，表示线段起始和终止位置
//
//
//        输出描述:
//        输出一个数，表示同一个位置最多重合多少条线段
//        示例1
//        输入
//        3
//        1 2
//        2 3
//        1 3
//        输出
//        3
package nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// 暴力解
// 堆
// 线段数
public class NowCoder_LineCoverMax {
    
    public static int maxCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());
        PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
        int max = 0;
        for (Line line : lines) {
            // 这里要注意 如果[1,2] ,[2, 3] 中2 算一个重合点的话，heap.peek().end < line.start，如果不算的话，heap.peek().end <= line.start
            while (!heap.isEmpty() && heap.peek().end < line.start) {
                heap.poll();
            }
            heap.add(line);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }

    }

    // 暴力解
    // 1. 首先得到线段的最大值和最小值
    // 2. 最大值和最小值按单位1等分，看每条线覆盖了多少，抓一下全局max
    public static int maxCover2(int[][] lines) {
        int min = lines[0][0];
        int max = lines[0][1];
        for (int[] line : lines) {
            min = Math.min(min, Math.min(line[0], line[1]));
            max = Math.max(max, Math.max(line[0], line[1]));
        }
        int cover = 0;
        int maxCover = 0;
        for (int i = min; i <= max; i++) {
            for (int[] line : lines) {
                // 这里要注意 如果[1,2] ,[2, 3] 中2 算一个重合点的话，
                // 则条件为：line[0] <= i && line[1] >= i
                // 如果不算的话，line[0] <= i+0.5 && line[1] >= i + 0.5
                if (line[0] <= i && line[1] >= i) {
                    cover++;
                }
            }
            maxCover = Math.max(cover, maxCover);
            cover = 0;
        }
        return maxCover;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            lines[i][0] = in.nextInt();
            lines[i][1] = in.nextInt();
        }
        System.out.println(maxCover(lines));
        in.close();
    }
}

