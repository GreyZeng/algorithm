package nowcoder;

import java.util.Scanner;

// 进阶分糖果问题
//链接：https://www.nowcoder.com/questionTerminal/de342201576e44548685b6c10734716e
// 原始问题：https://leetcode-cn.com/problems/candy/
// 环形分糖果问题 @see Code_0112_CircleCandy
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

    public static long candy(int[] arr) {
        long[] left = new long[arr.length];
        long[] right = new long[arr.length];
        left[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else if (arr[i] == arr[i - 1]) {
                left[i] = left[i - 1];
            } else {
                left[i] = 1;
            }
        }
        right[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else if (arr[i] == arr[i + 1]) {
                right[i] = right[i - 1];
            } else {
                right[i] = 1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            left[i] = Math.max(left[i], right[i]);
        }
        long sum = 0;
        for (long n : left) {
            sum += n;
        }
        return sum;
    }
}
