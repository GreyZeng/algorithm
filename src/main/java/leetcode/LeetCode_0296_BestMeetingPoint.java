package leetcode;

// TODO
// 二维矩阵中只有0和1，每个1可以上、下、左、右的移动
// 想让所有的1汇聚在一个点上开会，请返回所有1移动的最小距离和
// leetcode题目 : https://leetcode.cn/problems/best-meeting-point/
public class LeetCode_0296_BestMeetingPoint {

  public static int minTotalDistance(int[][] grid) {
    int N = grid.length;
    int M = grid[0].length;
    int[] iOnes = new int[N];
    int[] jOnes = new int[M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) {
          iOnes[i]++;
          jOnes[j]++;
        }
      }
    }
    int total = 0;
    int i = 0;
    int j = N - 1;
    int iRest = 0;
    int jRest = 0;
    while (i < j) {
      if (iOnes[i] + iRest <= iOnes[j] + jRest) {
        total += iOnes[i] + iRest;
        iRest += iOnes[i++];
      } else {
        total += iOnes[j] + jRest;
        jRest += iOnes[j--];
      }
    }
    i = 0;
    j = M - 1;
    iRest = 0;
    jRest = 0;
    while (i < j) {
      if (jOnes[i] + iRest <= jOnes[j] + jRest) {
        total += jOnes[i] + iRest;
        iRest += jOnes[i++];
      } else {
        total += jOnes[j] + jRest;
        jRest += jOnes[j--];
      }
    }
    return total;
  }
}
