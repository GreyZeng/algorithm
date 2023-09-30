package snippet;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/** 给定一个非负数组arr，和一个正数m 返回arr的所有子序列中累加和%m之后的最大值。 */
public class Code_0045_SubSequenceMaxModM {
  // 暴力解法
  public static int max1(int[] arr, int m) {
    Set<Integer> set = new HashSet<>();
    process(arr, 0, 0, set);
    int max = arr[0] % m;
    for (int sum : set) {
      max = Math.max(max, sum % m);
    }
    return max;
  }

  public static void process(int[] arr, int i, int sum, Set<Integer> set) {
    if (i == arr.length) {
      set.add(sum);
    } else {
      process(arr, i + 1, sum + arr[i], set);
      process(arr, i + 1, sum, set);
    }
  }

  // dp[i][j] ---> 0...i自由选择，能否正确得到j
  // 最后一行，dp[i][j] = true的记录，j%m的最大值即为答案
  // 二维表 O(N*sum)
  // 这种情况下 sum不能太大
  public static int max2(int[] arr, int m) {
    int sum = arr[0];
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      sum += arr[i];
    }
    boolean[][] dp = new boolean[n][sum + 1];
    // 0...0，可以组成arr[0] 或者 0
    dp[0][arr[0]] = true;
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        // 至少可以不选i位置的值累加
        dp[i][j] = dp[i - 1][j];
        if (j >= arr[i]) {
          dp[i][j] |= dp[i - 1][j - arr[i]];
        }
      }
    }
    int max = 0;
    for (int j = 0; j < dp[0].length; j++) {
      if (dp[n - 1][j]) {
        max = Math.max(max, j % m);
      }
    }
    return max;
  }

  // sum如果很大，那么max2方法不行，可以考虑如下方法
  // 二维表（n*m）：列是数组下标，行0...m-1
  // 0到i上随意组合，在i%m后能否组成j
  public static int max3(int[] arr, int m) {
    int n = arr.length;
    boolean[][] dp = new boolean[n][m];
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = true;
    }
    dp[0][arr[0] % m] = true;
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        // 至少可以不选i位置的值累加
        dp[i][j] = dp[i - 1][j];
        int cur = arr[i] % m;
        if (j < cur) {
          dp[i][j] |= dp[i - 1][m + j - cur];
        } else {
          dp[i][j] |= dp[i - 1][j - cur];
        }
      }
    }
    for (int i = m - 1; i >= 1; i--) {
      if (dp[n - 1][i]) {
        return i;
      }
    }
    return 0;
  }

  // 如果arr的累加和很大，m也很大
  // 但是arr的长度相对不大
  // 方法3（分治）：
  // 二维表(n*m（真实的余数）):如果n很小，可以分解两半，暴力求每一半的所有可能子数组的累加和，放入一个List中，List加工出每个值模m后的List‘
  // 左边部分的数量 + 右边部分的数量 + 左边和右边整合的List
  public static int max4(int[] arr, int m) {
    if (arr.length == 1) {
      return arr[0] % m;
    }
    int mid = (arr.length - 1) / 2;
    TreeSet<Integer> sortSet1 = new TreeSet<>();
    process4(arr, 0, 0, mid, m, sortSet1);
    TreeSet<Integer> sortSet2 = new TreeSet<>();
    process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
    int ans = 0;
    for (Integer leftMod : sortSet1) {
      // 左边的余数是leftMode，期待右边有一个余数 两个余数之和最接近 m -1即为答案
      ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
    }
    return ans;
  }

  // 从index出发，最后有边界是end+1，arr[index...end]
  public static void process4(
      int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
    if (index == end + 1) {
      sortSet.add(sum % m);
    } else {
      process4(arr, index + 1, sum, end, m, sortSet);
      process4(arr, index + 1, sum + arr[index], end, m, sortSet);
    }
  }

  public static int[] generateRandomArray(int len, int value) {
    int[] ans = new int[(int) (Math.random() * len) + 1];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = (int) (Math.random() * value);
    }
    return ans;
  }

  public static void main(String[] args) {
    int len = 10;
    int value = 100;
    int m = 76;
    int testTime = 500000;
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(len, value);
      int ans1 = max1(arr, m);
      int ans2 = max2(arr, m);
      int ans3 = max3(arr, m);
      int ans4 = max4(arr, m);
      if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish!");
  }
}
