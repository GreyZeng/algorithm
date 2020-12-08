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


import java.util.*;

// 暴力解
// 堆
// 线段数
public class NowCoder_LineCoverMax {

    // 堆解法
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

    // 线段树解法
    public static int maxCover2(int[][] lines) {
        HashMap<Integer, Integer> map = index(lines);
        int N = map.size();

        SegmentTree tree = new SegmentTree(N);
        long max = 0;
        for (int[] line : lines) {
            int L = map.get(line[0]);
            int R = map.get(line[1]);
            tree.add(L, R, 1, 1, N, 1);
            long l = tree.queryMax(L, R, 1, N, 1);
            max = Math.max(l, max);
        }
        return (int) max;
    }

    public static HashMap<Integer, Integer> index(int[][] lines) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] line : lines) {
            set.add(line[0]);
            set.add(line[1]);
        }
        HashMap<Integer, Integer> map = new HashMap<>(set.size());
        int count = 0;
        for (Integer i : set) {
            map.put(i, ++count);
        }
        return map;
    }

    public static class SegmentTree {
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        private int MAXN;
        private int[] arr;
        private int[] max;
        private int[] lazy;

        public SegmentTree(int N) {
            MAXN = N + 1;
            arr = new int[MAXN];
            int v = MAXN << 2;
            lazy = new int[v];
            max = new int[v];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[(rt << 1) | 1]);
        }

        // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                max[rt << 1] += lazy[rt];
                max[(rt << 1) | 1] += lazy[rt];
                lazy[rt << 1] += lazy[rt];
                lazy[(rt << 1) | 1] += lazy[rt];
                lazy[rt] = 0;
            }
        }

        // L..R -> 任务范围 ,所有的值累加上C
        // l,r -> 表达的范围
        // rt  去哪找l，r范围上的信息
        // 这里不仅要处理lazy数组，不要忘记了处理sum数组
        public void add(
                int L, int R, int C,
                int l, int r,
                int rt) {
            if (L <= l && R >= r) {
                lazy[rt] += C;
                max[rt] += C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        public long queryMax(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long left = Integer.MIN_VALUE;
            long right = Integer.MIN_VALUE;
            if (L <= mid) {
                left = queryMax(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = queryMax(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return Math.max(left, right);
        }
    }


    // 暴力解
    // 1. 首先得到线段的最大值和最小值
    // 2. 最大值和最小值按单位1等分，看每条线覆盖了多少，抓一下全局max
    public static int maxCover3(int[][] lines) {
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



    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] lines = new int[N][2];
        for (int i = 0; i < N; i++) {
            lines[i][0] = in.nextInt();
            lines[i][1] = in.nextInt();
        }
        System.out.println(maxCover3(lines));
        in.close();
    }*/

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
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);

            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            int ans = maxCover(lines);
            if (ans != ans3 || ans2 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end");
    }
}

