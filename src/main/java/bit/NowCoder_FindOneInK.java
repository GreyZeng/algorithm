package bit;

import java.util.Scanner;

// 一个数组中有一种数出现K次，其他数都出现了M次，M > 1, K < M, 找到出现了K次的数，要求，额外空间复杂度O(1)，时间复杂度O(N)
// https://www.cnblogs.com/greyzeng/p/15385402.html
// https://www.nowcoder.com/practice/26e46f1f5e0d48c4b9ba13fe3e8d0ec6
public class NowCoder_FindOneInK {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(km(arr, 1, k));
        }
    }


    public static int km(int[] arr, int k, int m) {
        int[] bit = new int[32];
        // 将arr中的每个元素转换成二进制填充到bit数组中，每个位置上的数字累加
        for (int n : arr) {
            for (int i = 0; i < 32; i++) {
                bit[i] += ((n >>> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bit[i] % m != 0) {
                // 出现了k次的数在i位置是1
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
