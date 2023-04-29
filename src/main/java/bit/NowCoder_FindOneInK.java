/*
 * 题目描述 给定一个整型数组arr和一个大于1的整数k。已知arr中只有1个数出现了一次，其他的数出现k次，请返回出现了1次的数。 输入描述:
 * 输入包含两行，第一行包含两个整数n和k，1<=n<=10^5，1 < k <= 100，n代表数组arr的长度，第二行n个整数，代表数组arr，数组arr中每个数都是32位整数。 输出描述:
 * 输出一个整数，代表唯一出现1次的数。 示例1 输入 7 3 5 4 1 1 5 1 5 输出 4 备注: 时间复杂度O(n),额外空间复杂度O(1)。
 */
package bit;

import java.util.Scanner;

// https://www.nowcoder.com/practice/26e46f1f5e0d48c4b9ba13fe3e8d0ec6
public class NowCoder_FindOneInK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(km(arr, 1, k));
        in.close();
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
