package grey.algorithm.code11_heap;

import java.util.*;

// 线段的最大重合问题
// https://www.nowcoder.com/questionTerminal/f8fa4b67dd054892966d280790c42ba3
// 连接点算重合部分
// 暴力解
// 堆
// 笔记：https://www.cnblogs.com/greyzeng/p/15058662.html
// 线段树解法 TODO
public class Code_0005_NowCoder_LineCoverMax {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int lens = in.nextInt();
            int[][] lines = new int[lens][2];
            for (int i = 0; i < lens; i++) {
                lines[i][0] = in.nextInt();
                lines[i][1] = in.nextInt();
            }
            System.out.println(maxCover(lines));
        }
    }

    // 暴力解
    // 也可以通过牛客测试
    // lines[i][0]: 第i条线的起始点
    // lines[i][0]: 第i条线的终点
    // 时间复杂度O(n*(max - min))
    public static int maxCover(int[][] lines) {
        if(null == lines || lines.length < 1) {
            return 0;
        }
        int min = lines[0][0];
        int max = lines[0][1];
        for (int i = 1; i < lines.length; i++) {
            min = Math.min(lines[i][0],min);
            max = Math.max(lines[i][1],max);
        }
        int maxCover = 0;
        for (int i = min ; i <= max; i++) {
            int cover = 0;
            for (int[] line : lines) {
                if (line[0] <= i && i <= line[1]){
                    cover++;
                }
            }
            maxCover = Math.max(maxCover, cover);
        }
        return maxCover;
    }

    // 堆解法
    // O(N*logN)
    public static int maxCover3(int[][] lines) {
        if (null == lines || lines.length == 0) {
            return 0;
        }
        // 开始位置排序
        Arrays.sort(lines, Comparator.comparingInt(line -> line[0]));
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(line -> line[1]));
        int max = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek()[1] < line[0]) {
                heap.poll();
            }
            heap.offer(line);
            max = Math.max(heap.size(), max);
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
}
