/*Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

        The update(i, val) function modifies nums by updating the element at index i to val.

        Example:

        Given nums = [1, 3, 5]

        sumRange(0, 2) -> 9
        update(1, 2)
        sumRange(0, 2) -> 8


        Constraints:

        The array is only modifiable by the update function.
        You may assume the number of calls to update and sumRange function is distributed evenly.
        0 <= i <= j <= nums.length - 1*/
package leetcode;


public class LeetCode_0307_RangeSumQuery {

    // 线段树解法，注意下标从1开始
    class NumArray {
        int N;
        int[] arr;
        int[] sum;
        boolean[] updated;
        int[] change;

        public NumArray(int[] nums) {
            N = nums.length + 1;
            arr = new int[N];
            for (int i = 1; i < N; i++) {
                arr[i] = nums[i - 1];
            }
            int v = N << 2;
            sum = new int[v];
            change = new int[v];
            updated = new boolean[v];
            build(1, N - 1, 1);
        }

        private void build(int l, int r, int rt) {
            if (r < l) {
                return;
            }
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, (rt << 1) | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) | 1];
        }

        public void update(int i, int val) {
            update(i + 1, i + 1, val, 1, N - 1, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                updated[rt] = true;
                change[rt] = C;
                sum[rt] = (r - l + 1) * C;
                return;
            }
            int mid = (r + l) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (updated[rt]) {
                change[rt << 1] = change[rt];
                change[(rt << 1) | 1] = change[rt];
                sum[rt << 1] = ln * change[rt];
                sum[(rt << 1) | 1] = rn * change[rt];
                updated[rt << 1] = true;
                updated[(rt << 1) | 1] = true;
                updated[rt] = false;
            }
        }

        public int sumRange(int i, int j) {
            return query(i + 1, j + 1, 1, N - 1, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, (rt << 1) | 1);
            }
            return ans;
        }
    }


}
