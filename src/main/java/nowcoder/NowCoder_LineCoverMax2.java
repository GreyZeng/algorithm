package nowcoder;

import java.util.*;

/*链接：https://www.nowcoder.com/questionTerminal/1ae8d0b6bb4e4bcdbf64ec491f63fc37
        来源：牛客网

        每一个线段都有start和end两个数据项，表示这条线段在X轴上从start位置开始到end位置结束。
        给定一批线段，求所有重合区域中最多重合了几个线段，首尾相接的线段不算重合。
        例如：线段[1,2]和线段[2.3]不重合。
        线段[1,3]和线段[2,3]重合



        输入描述:
        第一行一个数N，表示有N条线段
        接下来N行每行2个数，表示线段起始和终止位置


        输出描述:
        输出一个数，表示同一个位置最多重合多少条线段
        示例1
        输入
        3
        1 2
        2 3
        1 3
        输出
        2

        备注:
        N\leq 10^4N≤10
        4

        1\leq start,end \leq 10^51≤start,end≤10
        5*/
public class NowCoder_LineCoverMax2 {

    private static Line[] toLine(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        return lines;
    }

    public static class Line {

        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
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

    // 暴力解法
    private static int maxCover(int[][] arr) {
        Line[] lines = toLine(arr);
        int min = lines[0].start;
        int max = lines[0].end;
        for (int i = 1; i < lines.length; i++) {
            max = Math.max(max, lines[i].end);
            min = Math.min(min, lines[i].start);
        }
        int coverMax = 0;
        int cover = 0;
        for (int i = min; i <= max; i++) {
            for (Line line : lines) {
                if (line.start <= i + 0.5 && line.end >= i + 0.5) {
                    cover++;
                }
            }
            coverMax = Math.max(cover, coverMax);
            cover = 0;
        }
        return coverMax;
    }
    // 堆解法
    
}
