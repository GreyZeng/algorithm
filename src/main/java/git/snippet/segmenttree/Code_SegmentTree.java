package git.snippet.segmenttree;

// 线段树：https://www.cnblogs.com/greyzeng/p/15328120.html
public class Code_SegmentTree {

  public static class SegmentTree {
    // 原序列的信息从0开始，但在arr里是从1开始的
    private int[] arr;
    // 维护区间和
    private int[] sum;
    // lazy[]为累加和懒惰标记
    private int[] lazy;
    // change[]为更新的值
    private int[] change;
    // update[]为更新慵懒标记
    private boolean[] update;

    public SegmentTree(int[] origin) {
      final int n = origin.length + 1;
      arr = new int[n];
      // 0位置不用 从1位置开始使用
      System.arraycopy(origin, 0, arr, 1, origin.length);
      final int range = n << 2;
      sum = new int[range];
      lazy = new int[range];
      change = new int[range];
      update = new boolean[range];
    }

    // 在初始化阶段调用 O(N)
    public void build(int l, int r, int i) {
      if (l == r) {
        sum[i] = arr[l];
        return;
      }
      int mid = (l + r) >> 1;
      build(l, mid, i << 1);
      build(mid + 1, r, i << 1 | 1);
      pushUp(i);
    }

    private void pushUp(int rt) {
      sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
    // 分发策略是什么
    // ln表示左子树元素结点个数，rn表示右子树结点个数
    private void pushDown(int rt, int ln, int rn) {
      if (update[rt]) {
        update[rt << 1] = true;
        update[rt << 1 | 1] = true;
        change[rt << 1] = change[rt];
        change[rt << 1 | 1] = change[rt];
        lazy[rt << 1] = 0;
        lazy[rt << 1 | 1] = 0;
        sum[rt << 1] = change[rt] * ln;
        sum[rt << 1 | 1] = change[rt] * rn;
        update[rt] = false;
      }
      if (lazy[rt] != 0) {
        lazy[rt << 1] += lazy[rt];
        sum[rt << 1] += lazy[rt] * ln;
        lazy[rt << 1 | 1] += lazy[rt];
        sum[rt << 1 | 1] += lazy[rt] * rn;
        lazy[rt] = 0;
      }
    }

    // L~R 所有的值变成C
    public void update(int L, int R, int C, int l, int r, int rt) {
      if (L <= l && r <= R) {
        update[rt] = true;
        change[rt] = C;
        sum[rt] = C * (r - l + 1);
        lazy[rt] = 0;
        return;
      }
      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);
      if (L <= mid) {
        update(L, R, C, l, mid, rt << 1);
      }
      if (R > mid) {
        update(L, R, C, mid + 1, r, rt << 1 | 1);
      }
      pushUp(rt);
    }

    // L...R是任务区间，在这个区间范围内都加C
    public void add(int L, int R, int C, int l, int r, int rt) {
      if (L <= l && r <= R) {
        sum[rt] += C * (r - l + 1);
        lazy[rt] += C;
        return;
      }
      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);
      if (L <= mid) {
        add(L, R, C, l, mid, rt << 1);
      }
      if (R > mid) {
        add(L, R, C, mid + 1, r, rt << 1 | 1);
      }
      pushUp(rt);
    }

    // 1~6 累加和是多少？ 1~8 rt
    public long query(int L, int R, int l, int r, int rt) {
      if (L <= l && r <= R) {
        return sum[rt];
      }
      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);
      long ans = 0;
      if (L <= mid) {
        ans += query(L, R, l, mid, rt << 1);
      }
      if (R > mid) {
        ans += query(L, R, mid + 1, r, rt << 1 | 1);
      }
      return ans;
    }
  }

  public static class Right {
    public int[] arr;

    public Right(int[] origin) {
      arr = new int[origin.length + 1];
      System.arraycopy(origin, 0, arr, 1, origin.length);
    }

    public void update(int L, int R, int C) {
      for (int i = L; i <= R; i++) {
        arr[i] = C;
      }
    }

    public void add(int L, int R, int C) {
      for (int i = L; i <= R; i++) {
        arr[i] += C;
      }
    }

    public long query(int L, int R) {
      long ans = 0;
      for (int i = L; i <= R; i++) {
        ans += arr[i];
      }
      return ans;
    }
  }

  public static int[] genarateRandomArray(int len, int max) {
    int size = (int) (Math.random() * len) + 1;
    int[] origin = new int[size];
    for (int i = 0; i < size; i++) {
      origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
    }
    return origin;
  }

  public static boolean test() {
    int len = 100;
    int max = 1000;
    int testTimes = 50000;
    int addOrUpdateTimes = 1000;
    int queryTimes = 500;
    for (int i = 0; i < testTimes; i++) {
      int[] origin = genarateRandomArray(len, max);
      SegmentTree seg = new SegmentTree(origin);
      int S = 1;
      int N = origin.length;
      int root = 1;
      seg.build(S, N, root);
      Right rig = new Right(origin);
      for (int j = 0; j < addOrUpdateTimes; j++) {
        int num1 = (int) (Math.random() * N) + 1;
        int num2 = (int) (Math.random() * N) + 1;
        int L = Math.min(num1, num2);
        int R = Math.max(num1, num2);
        int C = (int) (Math.random() * max) - (int) (Math.random() * max);
        if (Math.random() < 0.5) {
          seg.add(L, R, C, S, N, root);
          rig.add(L, R, C);
        } else {
          seg.update(L, R, C, S, N, root);
          rig.update(L, R, C);
        }
      }
      for (int k = 0; k < queryTimes; k++) {
        int num1 = (int) (Math.random() * N) + 1;
        int num2 = (int) (Math.random() * N) + 1;
        int L = Math.min(num1, num2);
        int R = Math.max(num1, num2);
        long ans1 = seg.query(L, R, S, N, root);
        long ans2 = rig.query(L, R);
        if (ans1 != ans2) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] origin = {2, 1, 1, 2, 3, 4, 5};
    SegmentTree seg = new SegmentTree(origin);
    int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
    int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
    int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
    int L = 2; // 操作区间的开始位置 -> 可变
    int R = 5; // 操作区间的结束位置 -> 可变
    int C = 4; // 要加的数字或者要更新的数字 -> 可变
    // 区间生成，必须在[S,N]整个范围上build
    seg.build(S, N, root);
    // 区间修改，可以改变L、R和C的值，其他值不可改变
    seg.add(L, R, C, S, N, root);
    // 区间更新，可以改变L、R和C的值，其他值不可改变
    seg.update(L, R, C, S, N, root);
    // 区间查询，可以改变L和R的值，其他值不可改变
    long sum = seg.query(L, R, S, N, root);
    System.out.println(sum);

    System.out.println("对数器测试开始...");
    System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));
  }
}
