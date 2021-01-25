/*链接：https://www.nowcoder.com/questionTerminal/ceeeb34083934e79ad56019cd2cef4c4
        来源：牛客网

给定一个有序的正数数组arr和一个正数range，如果可以自由选择arr中的数字，想累加得 到 1~range 范围上所有的数，返回arr最少还缺几个数。
【举例】
arr = {1,2,3,7}，range = 15
想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1
arr = {1,5,7}，range = 15
想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2

        输入描述:
        第一行一个整数N, K。表示数组长度以及range
        接下来一行N个整数表示数组内的元素*/
package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Young
 * @version 1.0
 * @date 2021/1/25 0:06
 */
public class NowCoder_MissingNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int aim = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(missing(arr, aim));
        in.close();
    }

    // 如果要实现1~range所有目标，但整个目标还没有达到1~aim，你永远缺range+1，一定是最省且最经济的，补上range+1后，能达到的数是1~2*range+1
    // 先将数组排序，依次考察如何最经济使用i位置的数
    public static int missing(int[] arr, int aim) {
        int miss = 0;
        long range = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > range + 1) {
                range += (range + 1);
                miss++;
                if (range >= aim) {
                    return miss;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return miss;
            }

        }

        while (aim >= range + 1) {
            range += range + 1;
            miss++;
        }
        return miss;
    }
}
