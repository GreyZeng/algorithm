package heap;

import java.util.*;

// 线段的最大重合问题
// https://www.nowcoder.com/questionTerminal/f8fa4b67dd054892966d280790c42ba3
// 连接点算重合部分
// 暴力解
// 堆
// 笔记：https://www.cnblogs.com/greyzeng/p/15058662.html
// 线段树解法
public class NowCoder_LineCoverMax {
    // 暴力解
    // 1. 首先得到线段的最大值和最小值
    // 2. 最大值和最小值按单位1等分，看每条线覆盖了多少，抓一下全局max
    // 复杂度O(n*(max-min))
    public static int maxCover(int[][] lines) {
        int min = lines[0][0];
        int max = lines[0][1];
        for (int[] line : lines) {
            min = Math.min(min, line[0]);
            max = Math.max(max, line[1]);
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

    // 堆解法
    // O(N*logN)
    public static int maxCover1(int[][] lines) {
        // O(N*logN)
        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int max = 0;
        for (int[] line : lines) {
            // 这里要注意
            // 如果[1,2] ,[2, 3] 中2 算一个重合点的话，heap.peek().end < line.start
            // 如果不算的话，heap.peek().end <= line.start
            while (!heap.isEmpty() && heap.peek()[1] < line[0]) {
                heap.poll();
            }
            heap.add(line);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // 线段树解法
    // 复杂度是：O(N*logN)
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

    // 离散化
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

    // [1...3],[2..6],[4..9]，问：哪个区间描的最多，可以用线段树(注意离散化，注意在范围内+1以后，执行的不是querySum而是queryMax)
    // 注意：不管什么线段，开始位置排序，线段开始位置越早，越先处理
    public static class SegmentTree {
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

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0) {
                max[rt << 1] += lazy[rt];
                max[(rt << 1) | 1] += lazy[rt];
                lazy[rt << 1] += lazy[rt];
                lazy[(rt << 1) | 1] += lazy[rt];
                lazy[rt] = 0;
            }
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
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

    public static void main(String[] args) {
//        try (Scanner in = new Scanner(System.in)) {
//            int N = in.nextInt();
//            int[][] lines = new int[N][2];
//            for (int i = 0; i < N; i++) {
//                lines[i][0] = in.nextInt();
//                lines[i][1] = in.nextInt();
//            }
//            // System.out.println(maxCover(lines));
//            // System.out.println(maxCover1(lines));
//            System.out.println(maxCover2(lines));
//            in.close();
//        }
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover(lines);
            int ans2 = maxCover1(lines);
            int ans3 = maxCover2(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
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
}
