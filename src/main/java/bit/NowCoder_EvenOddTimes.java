package bit;

import java.util.Scanner;

// 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
// 测评链接：https://www.nowcoder.com/questionTerminal/d0ef3e33e63a49dd99c90aeef306b0fc
// 备注:
// 时间复杂度O(n),额外空间复杂度O(1)。
public class NowCoder_EvenOddTimes {

    public static int getEvenNum(int[] arr) {
        int t = arr[0];
        for (int i = 1; i < arr.length; i++) {
            t ^= arr[i];
        }
        return t;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int count = in.nextInt();
            int[] arr = new int[count];
            for (int i = 0; i < count; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(getEvenNum(arr));
        }
    }
}
