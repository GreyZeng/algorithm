package heap;

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
  public static int maxCover2(int[][] arr) {
    Line[] lines = toLine(arr);
    Arrays.sort(lines, Comparator.comparingInt(o -> o.start));
    PriorityQueue<Line> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
    int max = 1;
    for (Line line : lines) {
      while (!heap.isEmpty() && heap.peek().end <= line.start) {
        heap.poll();
      }
      heap.offer(line);
      max = Math.max(heap.size(), max);
    }
    return max;
  }

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
}
