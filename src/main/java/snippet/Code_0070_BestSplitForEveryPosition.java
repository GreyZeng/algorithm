package snippet;

// 把Code_0065_BestSplitForAll中提到的，min{左部分累加和，右部分累加和}，定义为S(N-1)，也就是说：

// `S(N-1)`表示在`arr[0…N-1]`范围上，做最优划分所得到的`min{左部分累加和，右部分累加和}的最大值`,
// 现在要求返回一个长度为N的s数组，
// S[i]表示在`arr[0…i]`范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值, 得到整个S数组的过程，做到时间复杂度O(N)

// tips:
// 1. sum和范围有单调性
// 2. 范式遵从 差(好(S左，S右))
// 都有O(N)的优化
// 3. 前缀和加速（前缀和数组多加一位，不需要判断边界）
public class Code_0070_BestSplitForEveryPosition {
  // 暴力枚举
  public static int[] bestSplit1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }
    int n = arr.length;
    if (n == 1) {
      return new int[] {0};
    }
    int[] S = new int[n];
    S[0] = 0;

    for (int i = 1; i < n; i++) {
      // 枚举左右两个部分
      // split表示分割点
      for (int split = 0; split < i; split++) {
        int leftSum = 0;
        int rightSum = 0;
        // 左边：0...split
        for (int l = 0; l <= split; l++) {
          leftSum += arr[l];
        }
        // 右边：split+1,i
        for (int r = split + 1; r <= i; r++) {
          rightSum += arr[r];
        }
        S[i] = Math.max(S[i], Math.min(leftSum, rightSum));
      }
    }
    return S;
  }

  // 暴力解基础上通过前缀和加速
  public static int[] bestSplit2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }
    int n = arr.length;
    if (n == 1) {
      return new int[] {0};
    }
    // 前缀和数组，任意i...j位置上的值通过sum[j + 1] - sum[i]获取
    int[] sum = new int[n + 1];
    sum[0] = 0;
    for (int i = 1; i < n + 1; i++) {
      sum[i] = sum[i - 1] + arr[i - 1];
    }
    int[] S = new int[n];
    S[0] = 0;
    for (int i = 1; i < n; i++) {
      // 枚举左右两个部分
      // split表示分割点
      for (int split = 0; split < i; split++) {
        // 使用前缀和加速，减少了一阶
        int leftSum = sum(sum, 0, split);
        int rightSum = sum(sum, split + 1, i);
        S[i] = Math.max(S[i], Math.min(leftSum, rightSum));
      }
    }
    return S;
  }

  // 最优解，四边形不等式优化
  public static int[] bestSplit3(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new int[0];
    }
    int n = arr.length;
    if (n == 1) {
      return new int[] {0};
    }
    // 前缀和数组，任意i...j位置上的值通过sum[j + 1] - sum[i]获取
    int[] sum = new int[n + 1];
    sum[0] = 0;
    for (int i = 1; i < n + 1; i++) {
      sum[i] = sum[i - 1] + arr[i - 1];
    }
    int[] S = new int[n];
    S[0] = 0;
    // 记录前一个位置最优划分点在哪里
    int best = 0;
    for (int i = 1; i < n; i++) {
      // 四边形不等式优化
      S[i] = Math.min(sum(sum, 0, best), sum(sum, best + 1, i));
      for (int split = best; split < i; split++) {
        int leftSum = sum(sum, 0, split);
        int rightSum = sum(sum, split + 1, i);
        int result = Math.min(leftSum, rightSum);
        // 一定要>=, 因为如果严格大于，会错过0的情况
        if (result >= S[i]) {
          S[i] = result;
          best = split;
        } else {
          break;
        }
      }
    }

    return S;
  }

  // 求原来的数组arr中，arr[L...R]的累加和
  public static int sum(int[] sum, int L, int R) {
    return sum[R + 1] - sum[L];
  }

  public static int[] randomArray(int len, int max) {
    int[] ans = new int[len];
    for (int i = 0; i < len; i++) {
      ans[i] = (int) (Math.random() * max);
    }
    return ans;
  }

  public static boolean isSameArray(int[] arr1, int[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }
    int N = arr1.length;
    for (int i = 0; i < N; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int N = 20;
    int max = 30;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * N);
      int[] arr = randomArray(len, max);
      int[] ans1 = bestSplit1(arr);
      int[] ans2 = bestSplit2(arr);
      int[] ans3 = bestSplit3(arr);
      if (!isSameArray(ans1, ans2) || !isSameArray(ans1, ans3)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("测试结束");
  }
}
