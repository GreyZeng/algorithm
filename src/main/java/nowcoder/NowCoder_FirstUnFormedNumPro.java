package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

// 最小不可组成和-进阶，如果数组中肯定存在1这个值
// https://www.nowcoder.com/questionTerminal/a689a05f75ff4caaa129b1f971aeb71e
public class NowCoder_FirstUnFormedNumPro {
    public static long unformedSum(long[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        long range = 1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nextInt = in.nextInt();
        long[] arr = new long[nextInt];
        for (int i = 0; i < nextInt; i++) {
            arr[i] = in.nextLong();
        }
        System.out.println(unformedSum(arr));

        in.close();
    }
}
