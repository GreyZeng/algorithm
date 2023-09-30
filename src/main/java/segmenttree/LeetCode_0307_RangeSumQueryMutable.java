package segmenttree;

// 线段树
// 笔记：https://www.cnblogs.com/greyzeng/p/15328120.html
// https://leetcode-cn.com/problems/range-sum-query-mutable/
public class LeetCode_0307_RangeSumQueryMutable {
  // 线段树解法
  class NumArray {
    private int[] arr;
    private int[] change;
    private boolean[] update;
    // private int[] lazy;
    private int[] sum;

    public NumArray(int[] nums) {
      final int n = nums.length + 1;
      arr = new int[n];
      System.arraycopy(nums, 0, arr, 1, nums.length);
      sum = new int[n << 2];
      change = new int[n << 2];
      // lazy = new int[n << 2];
      update = new boolean[n << 2];
      build(1, nums.length, 1);
    }

    private void build(int l, int r, int rt) {
      if (l == r) {
        sum[rt] = arr[r];
      } else {
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
      }
    }

    private void pushUp(int rt) {
      sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    public void update(int index, int val) {
      index += 1;
      update(index, index, val, 1, arr.length - 1, 1);
    }

    public void update(int L, int R, int C, int l, int r, int rt) {
      if (L <= l && r <= R) {
        change[rt] = C;
        update[rt] = true;
        sum[rt] = C * (r - l + 1);
        // lazy[rt] = 0;
      } else {
        int mid = (l + r) >> 1;
        // 下放
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
          update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
          update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
      }
    }

    private void pushDown(int rt, int lNum, int rNum) {
      if (update[rt]) {
        sum[rt << 1] = change[rt] * lNum;
        sum[rt << 1 | 1] = change[rt] * rNum;
        change[rt << 1] = change[rt];
        change[rt << 1 | 1] = change[rt];
        // change[rt] = 0;
        update[rt << 1] = true;
        update[rt << 1 | 1] = true;
        update[rt] = false;
      }
    }

    public int sumRange(int left, int right) {
      left += 1;
      right += 1;
      return sum(left, right, 1, arr.length - 1, 1);
    }

    public int sum(int L, int R, int l, int r, int rt) {
      if (L <= l && r <= R) {
        return sum[rt];
      } else {
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (L <= mid) {
          ans += sum(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
          ans += sum(L, R, mid + 1, r, rt << 1 | 1);
        }
        return (int) ans;
      }
    }
  }

  // index tree解法
  class NumArray2 {
    private int[] tree;
    private int N;
    private int[] arr;

    public NumArray2(int[] nums) {
      arr = new int[nums.length + 1];
      System.arraycopy(nums, 0, arr, 1, nums.length);
      N = nums.length;
      tree = new int[N + 1];
      for (int i = 1; i < tree.length; i++) {
        add(i, arr[i]);
      }
    }

    // leetcode中index是从0开始计算
    public void update(int index, int val) {
      add(index + 1, val - arr[index + 1]);
      arr[index + 1] = val;
    }

    // leetcode中left和right都是从0开始计算
    public int sumRange(int left, int right) {
      left += 1;
      right += 1;
      if (left == 1) {
        return sum(right);
      }
      return sum(right) - sum(left - 1);
    }

    // index tree方法，从1开始计算
    public void add(int index, int d) {
      while (index <= N) {
        tree[index] += d;
        index += (index & (-index));
      }
    }

    // index tree方法，从1开始算
    public int sum(int index) {
      int ret = 0;
      while (index > 0) {
        ret += tree[index];
        index -= (index & (-index));
      }
      return ret;
    }
  }
}
