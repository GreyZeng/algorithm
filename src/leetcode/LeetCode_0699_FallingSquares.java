/*On an infinite number line (x-axis), we drop given squares in the order they are given.

        The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

        The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

        The squares are infinitely sticky on their bottom edge,
        and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


        Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

        Example 1:

        Input: [[1, 2], [2, 3], [6, 1]]
        Output: [2, 5, 5]
        Explanation:
        After the first drop of positions[0] = [1, 2]: _aa _aa ------- The maximum height of any square is 2.

        After the second drop of positions[1] = [2, 3]: __aaa __aaa __aaa _aa__ _aa__ -------------- The maximum height of any square is 5.
        The larger square stays on top of the smaller square despite where its center of gravity is, because squares are infinitely sticky on their bottom edge.

        After the third drop of positions[1] = [6, 1]: __aaa __aaa __aaa _aa _aa___a -------------- The maximum height of any square is still 5. Thus, we return an answer of [2, 5, 5].




        Example 2:

        Input: [[100, 100], [200, 100]]
        Output: [100, 100]
        Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.


        Note:

        1 <= positions.length <= 1000.
        1 <= positions[i][0] <= 10^8.
        1 <= positions[i][1] <= 10^6.*/
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class LeetCode_0699_FallingSquares {
    public static class SegmentTree {
        public int MAXN;
        public int[] arr;
        public int[] change;
        public int[] sum;
        public int[] max;
        public boolean[] update;
        public int[] lazy;


        public SegmentTree(int N) {
            int[] origin = new int[N];
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            int v = MAXN << 2;
            change = new int[v];
            sum = new int[v];
            max = new int[v];
            update = new boolean[v];
            lazy = new int[v];
            build(1, MAXN - 1, 1);
        }

        private void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                max[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, (rt << 1) | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
            max[rt] = Math.max(max[rt << 1], max[(rt << 1) | 1]);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                lazy[rt] = 0;
                sum[rt] = C * (r - l + 1);
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        public int querySum(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += querySum(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += querySum(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return ans;
        }

        public int queryMax(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int left = Integer.MIN_VALUE;
            int right = Integer.MIN_VALUE;
            if (L <= mid) {
                left = queryMax(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = queryMax(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return Math.max(right, left);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                lazy[rt] += C;
                sum[rt] += (r - l + 1) * C;
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

        public void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                lazy[rt << 1] = 0;
                lazy[(rt << 1) | 1] = 0;
                max[rt << 1] = change[rt];
                max[(rt << 1) | 1] = change[rt];
                sum[rt << 1] = (ln * change[rt]);
                sum[(rt << 1) | 1] = (rn * change[rt]);
                change[rt << 1] = change[rt];
                change[(rt << 1) | 1] = change[rt];
                update[rt << 1] = true;
                update[(rt << 1) | 1] = true;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[(rt << 1) | 1] += lazy[rt];
                sum[rt << 1] += (ln * lazy[rt]);
                sum[(rt << 1) | 1] += (rn * lazy[rt]);
                max[rt << 1] += lazy[rt];
                max[(rt << 1) | 1] += lazy[rt];
                lazy[rt] = 0;
            }
        }
    }

    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] arr : positions) {
            set.add(arr[0]);
            set.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer i : set) {
            map.put(i, ++count);
        }
        return map;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree tree = new SegmentTree(N);
        int L = 0;
        int R = 0;
        int max = 0;
        int height = 0;
        List<Integer> ans = new ArrayList<>();
        for (int[] arr : positions) {
            L = map.get(arr[0]);
            R = map.get(arr[0] + arr[1] - 1);
            height = tree.queryMax(L, R, 1, N, 1) + arr[1];
            max = Math.max(max, height);
            ans.add(max);
            tree.update(L, R, height, 1, N, 1);
        }
        return ans;

    }
}
