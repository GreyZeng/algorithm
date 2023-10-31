package git.snippet.nowcoder;

// https://www.nowcoder.com/practice/f5805cc389394cf69d89b29c0430ff27
// 笔记：https://www.cnblogs.com/greyzeng/p/16424970.html
public class NowCoder_MaxGap {
  public int findMaxGap(int[] A, int n) {
    int max = A[0];
    int len = A.length;
    for (int i = 1; i < len; i++) {
      max = Math.max(A[i], max);
    }
    return max - (Math.min(A[0], A[len - 1]));
  }
}
