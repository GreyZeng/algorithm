package nowcoder;

import java.util.ArrayList;
import java.util.HashMap;

// 类似题目：
// 数组中所有数都异或起来的结果，叫做异或和
// 给定一个数组arr，可以任意切分成若干个不相交的子数组
// 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
// 返回这个最多数量
// f(i,List), i位置决定是否和前面一坨合并 ，初始调用 f(1,List)--->O(2^n)
// 最优解 O(N), dp[i] arr[0…i]异或和最多的零有多少个
// 考虑结尾，最优划分下，最后一个部分，
// 要不就是0，最后一个部分，不存在一个分割点k，左边也是0，右边也是0
// 要不就不是0，dp[i] 和 dp[i-1] 没区别
// 0…i的异或和是xor
// 找到xor上次出现的最晚的位置是k
// dp[k] + 1
// Max(dp[i-1],dp[k] + 1)
// NowCoder_MostZeroSplitEor.java
// 数组划分的部分进行异或和生成的0最多
// [321 | 0 | 4 | 321 | 0 | 0 | 312 | 0 | 213]
// tips:
// 假设答案法（子数组的最大累加和也用到了这个方法），
// 假设[0..i]异或和为sum
// sum上次出现的位置j，
// dp[i] = max{dp[i-1] , dp[j] + 1}
// 测评链接：https://www.nowcoder.com/practice/77e9828bbe3c4d4a9e0d49cc7537bb6d
public class NowCoder_MostZeroSplitEor {
  // 暴力方法
  public static int comparator(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    int[] eor = new int[N];
    eor[0] = arr[0];
    for (int i = 1; i < N; i++) {
      eor[i] = eor[i - 1] ^ arr[i];
    }
    return process(eor, 1, new ArrayList<>());
  }

  // index去决定：前一坨部分，结不结束！
  // 如果结束！就把index放入到parts里去
  // 如果不结束，就不放
  public static int process(int[] eor, int index, ArrayList<Integer> parts) {
    int ans = 0;
    if (index == eor.length) {
      parts.add(eor.length);
      ans = eorZeroParts(eor, parts);
      parts.remove(parts.size() - 1);
    } else {
      int p1 = process(eor, index + 1, parts);
      parts.add(index);
      int p2 = process(eor, index + 1, parts);
      parts.remove(parts.size() - 1);
      ans = Math.max(p1, p2);
    }
    return ans;
  }

  public static int eorZeroParts(int[] eor, ArrayList<Integer> parts) {
    int L = 0;
    int ans = 0;
    for (Integer end : parts) {
      if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
        ans++;
      }
      L = end;
    }
    return ans;
  }

  // 时间复杂度O(N)的方法
  public static int mostXor(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int n = arr.length;
    // dp[i] 表示：0...i号可以划分出最多异或和的部分是多少
    int[] dp = new int[n];
    // 枚举最后一个部分
    // 如果最后一个部分不为0，则dp[i] = dp[i-1]
    // 如果最后一个部分为0，假设最后一个部分是[m...n], xor[m...n] = 0, 那么对于任何的k属于[m..n], 不会存在xor[m..k]=0,
    // xor[k+1...n]=0
    // 假设xor[0...n] = x, 那么可以找到一个让最后部分为0的最晚位置w，使得xor[0...w] = x，xor[w...n] = 0,
    // dp[i] = dp[w] + 1
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int xor = 0;
    for (int i = 0; i < arr.length; i++) {
      xor ^= arr[i];
      if (map.containsKey(xor)) {
        int key = map.get(xor);
        dp[i] = key == -1 ? 1 : dp[key] + 1;
      }
      if (i - 1 >= 0) {
        dp[i] = Math.max(dp[i - 1], dp[i]);
      }
      // 更新xor出现的最晚位置
      map.put(xor, i);
    }
    return dp[n - 1];
  }

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random());
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int j : arr) {
      System.out.print(j + " ");
    }
    System.out.println();
  }

  // for test
  public static void main(String[] args) {
    // Scanner in = new Scanner(System.in);
    // int n = in.nextInt();
    // int[] arr = new int[n];
    // for (int i = 0; i < n; i++) {
    // arr[i] = in.nextInt();
    // }
    // System.out.println(mostXor(arr));
    // in.close();
    int testTime = 150000;
    int maxSize = 12;
    int maxValue = 5;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      int res = mostXor(arr);
      int comp = comparator(arr);
      if (res != comp) {
        succeed = false;
        printArray(arr);
        System.out.println(res);
        System.out.println(comp);
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
