package git.snippet.mergesort;

// 逆序对问题
// 笔记：https://www.cnblogs.com/greyzeng/p/16653063.html
// 测评链接：https://www.lintcode.com/problem/532/
public class LintCode_0532_ReversePairs {

  public long reversePairs(int[] A) {
    if (null == A || A.length <= 1) {
      return 0;
    }
    return count(A, 0, A.length - 1);
  }

  private long count(int[] a, int l, int r) {
    if (l == r) {
      return 0L;
    }
    int m = l + ((r - l) >> 1);
    return count(a, l, m) + count(a, m + 1, r) + merge(a, l, m, r);
  }

  private long merge(int[] a, int l, int m, int r) {
    int[] help = new int[r - l + 1];
    int i = 0;
    int ls = l;
    int rs = m + 1;
    long ans = 0L;
    while (ls <= m && rs <= r) {
      if (a[ls] > a[rs]) {
        ans += (r - rs + 1);
        help[i++] = a[ls++];
      } else {
        help[i++] = a[rs++];
      }
    }
    while (ls <= m) {
      help[i++] = a[ls++];
    }
    while (rs <= r) {
      help[i++] = a[rs++];
    }
    for (i = 0; i < help.length; i++) {
      a[l + i] = help[i];
    }
    return ans;
  }
}
