package candy;

import java.util.Scanner;

// 链接：https://www.nowcoder.com/questionTerminal/de342201576e44548685b6c10734716e
// 环形分糖果问题 @see Code_CircleCandy
public class NowCoder_CandyII {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
    }
    System.out.println(candy(arr));
    in.close();
  }

  // 进阶：相等分数糖果一定要相等
  public static int candy(int[] arr) {
    int n = arr.length;
    int[] left = new int[n];
    int[] right = new int[n];
    left[0] = 1;
    for (int i = 1; i < n; i++) {
      if (arr[i] > arr[i - 1]) {
        left[i] = left[i - 1] + 1;
      } else if (arr[i] == arr[i - 1]) {
        left[i] = left[i - 1];
      } else {
        left[i] = 1;
      }
    }
    right[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) {
        right[i] = right[i + 1] + 1;
      } else if (arr[i] == arr[i + 1]) {
        right[i] = right[i + 1];
      } else {
        right[i] = 1;
      }
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += Math.max(left[i], right[i]);
    }
    return sum;
  }
}
