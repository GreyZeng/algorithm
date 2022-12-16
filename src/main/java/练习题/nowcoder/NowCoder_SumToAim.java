// 链接：https://www.nowcoder.com/questionTerminal/f40109c48e55452096ec1de2ddeb8458
// 来源：牛客网
//
// 给定一个有序数组arr，和一个整数aim，请不重复打印arr中所有累加和为aim的二元组。
// 给定一个有序数组arr，和一个整数aim，请不重复打印arr中所有累加和为aim的三元组。
//
//
// 输入描述:
// 第一行两个整数
// N(数组大小)，aim
// 接下来一行N个整数，从小到大排列，表示数组内容
//
//
// 输出描述:
// 按字典序输出所有和为aim的二元组
// 按字典序输出所有和为aim的三元组
// 每个组中数据从小到大排列
// 数据完全相同的不输出
// 示例1
// 输入
// 9 6
// 1 1 1 2 2 2 3 3 3
// 输出
// 3 3
// 1 2 3
// 2 2 2
package 练习题.nowcoder;

import java.util.LinkedList;
import java.util.List;

// TODO
public class NowCoder_SumToAim {
  public static List<List<Integer>> twoSumAim(int[] arr, int k) {
    if (arr == null || arr.length < 2) {
      return new LinkedList<>();
    }
    int left = 0;
    int right = arr.length - 1;
    List<List<Integer>> ans = new LinkedList<>();
    while (left < right) {
      if (arr[left] + arr[right] < k) {
        left++;
      } else if (arr[left] + arr[right] > k) {
        right--;
      } else { // L + R = aim
        if (left == 0 || arr[left - 1] != arr[left]) {
          // System.out.println(arr[left] + "," + arr[right]);
          List<Integer> list = new LinkedList<>();
          list.add(arr[left]);
          list.add(arr[right]);
          ans.add(list);
        }
        left++;
        right--;
      }
    }
    return ans;
  }



  public static List<List<Integer>> threeSumToAim(int[] arr, int k) {
    if (arr == null || arr.length < 3) {
      return new LinkedList<>();
    }
    List<List<Integer>> ans = new LinkedList<>();
    for (int i = 0; i < arr.length - 2; i++) {
      if (i == 0 || arr[i] != arr[i - 1]) {
        printRest(arr, i, i + 1, arr.length - 1, k - arr[i], ans);
      }
    }
    return ans;
  }

  public static void printRest(int[] arr, int f, int l, int r, int k, List<List<Integer>> ans) {
    while (l < r) {
      if (arr[l] + arr[r] < k) {
        l++;
      } else if (arr[l] + arr[r] > k) {
        r--;
      } else {
        if (l == f + 1 || arr[l - 1] != arr[l]) {
          List<Integer> list = new LinkedList<>();
          list.add(arr[f]);
          list.add(arr[l]);
          list.add(arr[r]);
          ans.add(list);
        }
        l++;
        r--;
      }
    }
  }

  public static void main(String[] args) {
    /*
     * Scanner in = new Scanner(System.in); int n = in.nextInt(); int aim = in.nextInt(); int[] arr
     * = new int[n]; for (int i = 0; i < n; i++) { arr[i] = in.nextInt(); } List<List<Integer>> ans1
     * = twoSumAim(arr, aim); List<List<Integer>> ans2 = threeSumToAim(arr, aim); for (List<Integer>
     * ans : ans1) { for (Integer i : ans) { System.out.print(i + " "); } System.out.println(); }
     * for (List<Integer> ans : ans2) { for (Integer i : ans) { System.out.print(i + " "); }
     * System.out.println(); } in.close();
     */
    int[] arr = {1, 1, 1, 2, 2, 2, 3, 3, 3};
    int aim = 6;
    System.out.println(twoSumAim(arr, aim));
    System.out.println(threeSumToAim(arr, aim));
  }
}
