package 练习题.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// 生成长度为size的达标数组，什么叫达标？
// 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
// 给定一个正数size，返回长度为size的达标数组
// 返回构造出的arr
// tips:
// 长度为1，满足
// 假设 a + b != 2*c
// -> 2 * a - 1 + 2 * b - 1 != 2 * c - 1
// 2 * a + 2 * b != 2 * c
//
// [a,b,c]
// ->[2 * a - 1, 2 * b - 1, 2 * c - 1, 2 * a, 2 * b, 2 * c]
//
// [1,5,3]
// ->[1,9,5,2,10,6]
//
// 长度为M，只需要一个(M + 1)/2长度的种子
//
// 复杂度估计
// T(N) = T(N/2) + O(N)
//
// 空间复杂度可以做到O(1)
// LeetCode 这题有点差别，限制数字一定要是[1...N]
// https://leetcode-cn.com/problems/beautiful-array
public class LeetCode_0932_BeautifulArray {

  public static int[] beautifulArray(int N) {
    Map<Integer, int[]> memo = new HashMap<>(N);
    return f(N, memo);
  }

  public static int[] f(int N, Map<Integer, int[]> memo) {
    if (memo.containsKey(N)) {
      return memo.get(N);
    }

    int[] ans = new int[N];
    if (N == 1) {
      ans[0] = 1;
    } else {
      int t = 0;
      for (int x : f((N + 1) / 2, memo)) {
        ans[t++] = 2 * x - 1;
      }
      for (int x : f(N / 2, memo)) {
        ans[t++] = 2 * x;
      }
    }
    memo.put(N, ans);
    return ans;
  }


  // 检验函数
  public static boolean isValid(int[] arr) {
    int N = arr.length;
    for (int i = 0; i < N; i++) {
      for (int k = i + 1; k < N; k++) {
        for (int j = k + 1; j < N; j++) {
          if (arr[i] + arr[j] == 2 * arr[k]) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("test begin");
    for (int N = 1; N < 1000; N++) {
      int[] arr = beautifulArray(N);
      if (!isValid(arr)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test end");

    System.out.println(isValid(beautifulArray(1042)));
    System.out.println(isValid(beautifulArray(2981)));


  }

}
