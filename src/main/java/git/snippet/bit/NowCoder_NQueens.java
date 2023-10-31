package bit;

// 笔记：https://www.cnblogs.com/greyzeng/p/16909274.html
// N皇后问题
// 在N*N的棋盘上要摆N个皇后， 要求任何两个皇后不同行、不同列， 也不在同一条斜线上 给定一个整数n，返回n皇后的摆法有多少种。
// n=1，返回1 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0 n=8，返回92
// tip:
// 两个点：[x,y]和[甲,乙]
// 是否有冲突，只需要判断两个条件
// (y == 乙) || (|甲-x|==|乙-y|)
// https://www.nowcoder.com/questionTerminal/c76408782512486d91eea181107293b6
public class NowCoder_NQueens {
  public static int num1(int n) {
    if (n < 1 || n == 2 || n == 3) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    int[] records = new int[n];
    return process1(0, records, n);
  }

  public static int process1(int i, int[] records, int n) {
    if (i == n) {
      return 1;
    }
    int ways = 0;
    for (int j = 0; j < n; j++) {
      if (isValid(records, i, j)) {
        records[i] = j;
        ways += process1(i + 1, records, n);
      }
    }
    return ways;
  }

  public static boolean isValid(int[] records, int i, int j) {
    for (int s = 0; s < i; s++) {
      if (records[s] == j || Math.abs(records[s] - j) == Math.abs(i - s)) {
        return false;
      }
    }
    return true;
  }

  // 请不要超过32皇后问题
  public static int num2(int n) {
    if (n < 1 || n > 32) {
      return 0;
    }
    // 如果你是13皇后问题，limit 最右13个1，其他都是0
    int limit = n == 32 ? -1 : (1 << n) - 1;
    return process2(limit, 0, 0, 0);
  }

  // 7皇后问题
  // limit : 0....0 1 1 1 1 1 1 1
  // 之前皇后的列影响：colLim
  // 之前皇后的左下对角线影响：leftDiaLim
  // 之前皇后的右下对角线影响：rightDiaLim
  public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
    if (colLim == limit) {
      return 1;
    }
    // pos中所有是1的位置，是你可以去尝试皇后的位置
    int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
    int mostRightOne;
    int res = 0;
    while (pos != 0) {
      mostRightOne = pos & (~pos + 1);
      pos = pos - mostRightOne;
      res +=
          process2(
              limit,
              colLim | mostRightOne,
              (leftDiaLim | mostRightOne) << 1,
              (rightDiaLim | mostRightOne) >>> 1);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println("start");
    int times = 16;
    for (int n = 0; n < times; n++) {
      int ans1 = num1(n);
      int ans2 = num2(n);
      if (ans1 != ans2) {
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("end");
  }
}
