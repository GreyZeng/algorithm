/*链接：https://www.nowcoder.com/questionTerminal/fccb5d14b44b4b99b34839bdf20588e9
        来源：牛客网

        给定一个无序数组arr，求出需要排序的最短子数组的长度，对子数组排序后能使得整个数组有序，即为需要排序的数组。例如：arr=[1,5,3,4,2,6,7]返回4，因为只有[5,3,4,2]需要排序。

        输入描述:
        输入包含两行，第一行包括一个整数n(1≤n≤10^5)，代表数组arr长度，第二行n个整数，代表数组arr(−10^9≤arri≤10^9)。


        输出描述:
        输出一个整数，代表需要排序的最短子数组的长度。
        示例1
        输入
        7
        1 5 3 4 2 6 7
        输出
        4

        备注:
        时间复杂度O(n),额外空间复杂度O(1)。*/
package nowcoder;

import java.util.Scanner;

public class NowCoder_MinSubArrayNeedSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n] ;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(minSubArrayNeedSort(arr,n));
        in.close();
    }
    // TODO
    public static int minSubArrayNeedSort(int[] arr, int n) {
        return -1;
    }
}
