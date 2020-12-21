//链接：https://www.nowcoder.com/questionTerminal/d0ef3e33e63a49dd99c90aeef306b0fc
//        来源：牛客网
//
//        给一个数组arr，其中只有一个数出现了奇数次，其它数出现了偶数次，打印这个数。
//
//        输入描述:
//        输出包含两行，第一行包含一个整数n（1≤n≤10^5），代表数组arr长度，第二行有n个数，代表数组arrarr_i 为32位整数arr
//
//
//        输出描述:
//        输出一个整数，代表出现次数为奇数次的那个数。
//        示例1
//        输入
//        5
//        3 1 3 1 2
//        输出
//        2
//        示例2
//        输入
//        3
//        6 6 3
//        输出
//        3
//
//        备注:
//        时间复杂度O(n),额外空间复杂度O(1)。
package nowcoder;

import java.util.Scanner;

public class NowCoder_EvenOddTimes {
    /**
     * 题目二
     * <p>
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     */
    public static int getEvenNum(int[] arr) {
        int t = arr[0];
        for (int i = 1; i < arr.length; i++) {
            t ^= arr[i];
        }
        return t;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(getEvenNum(arr));
    }
}
