package git.snippet.leetcode;

// https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
public class LeetCode_0033_SearchInRotatedSortedArray {

  // L..M..R 位置的数只要不相等，就可以二分，如果三个都相等，就无法二分
  public static int search(int[] arr, int num) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    if (arr.length == 1) {
      return arr[0] == num ? 0 : -1;
    }
    int N = arr.length - 1;
    int L = 0;
    int R = N;
    int M = (L + R) / 2;
    while (L < R) {
      if (arr[L] == arr[R] && arr[L] == arr[M]) {
        if (arr[L] == num) {
          return L;
        }
        L++;
      } else {
        if (arr[L] < arr[M]) {
          if (arr[L] == num) {
            return L;
          }
          if (num < arr[M] && num > arr[L]) {
            R = M;
            M = (L + R) / 2;
          } else {
            L = M;
            M = (L + R) / 2;
          }
        } else if (arr[L] == arr[M]) {
          if (arr[L] == num) {
            return L;
          } else {
            L++;
            M++;
          }
        } else {
          // arr[L] > arr[M]

          if (num > arr[M] && num <= arr[R]) {
            L = M;
            M = (L + R) / 2;
          } else {
            R = M;
            M = (L + R) / 2;
          }
        }
      }
    }
    return L == R && arr[L] == num ? L : -1;
  }

  public static void main(String[] args) {
    int[] arr = {5, 1, 3};
    int target = 5;
    System.out.println(search(arr, target));
  }
}
