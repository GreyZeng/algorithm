
package snippet;

import java.util.*;
// 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
// 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
// 绳子的边缘点碰到X轴上的点，也算盖住
// O(N*logN)
// 最优解
// 长度和范围有单调性 -> 滑动窗口，左右指针
// 在牛客上，[类似题目](https://www.nowcoder.com/questionTerminal/2b2567c9b95743f19c167bb1ec644b43)
// 和这题有点差别，牛客上的题目是不算边缘压中的情况的
public class Code_0012_CoverMax {
    // 暴力方法：
    // 贪心，绳子边缘没必要不压中某个点
    // 以每个位置作为结尾来找，假设某个位置是103，长度是5，
    // 其实就是找[0，102]范围内>=98的最左的点
    public static int maxCover1(int[][] lines) {
        int min = lines[0][0];
        int max = lines[0][1];
        for (int i = 1; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());
        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
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

    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }

    }

    // for test
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

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    // 双指针似乎不行, 比如这种情况
    // 1 2 ........101 102
    // 左指针在左边1位置，右指针在右边102位置，这两个位置距离其相邻位置的差距都是1，这个时候，不知道该怎么判断要缩动左指针还是右指针
    // public static int coverMax(int[] arr, int n, int k) {
    // int l = 0;
    // int r = 0;
    // int max = 0;
    // while (l < n) {
    // while (r < n && arr[r] - arr[l] <= k) {
    // r++;
    // }
    // max = Math.max(max, r - l);
    // l++;
    // }
    // return max;
    // }

    // public static void main(String[] args) {
    // int n = 5;
    // int k = 3;
    // int[] arr = {1, 2, 3, 4, 5};
    // System.out.println(coverMax(arr, n, k));
    // }
}
