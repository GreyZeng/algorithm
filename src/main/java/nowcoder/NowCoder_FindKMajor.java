// 链接：https://www.nowcoder.com/questionTerminal/4d448650c0324df08c40c614226026ad
// 来源：牛客网

// 给定一个整型数组arr，再给定一个整数k，打印所有出现次数大于n/k的数，如果没有这样的数,请打印”-1“。

// 输入描述:
// 输入包含两行，第一行输入包含两个整数n和k(1≤k≤n≤10^5)
//,第二行包含n个整数，代表数组arr(1≤arri≤10^9)。


// 输出描述:
// 输出所有出现次数大于n/k的数，如果没有这样的数,请输出”-1“。
// 示例1
// 输入
// 7 7
// 1 2 3 1 2 3 4
// 输出
// 1 2 3
// 示例2
// 输入
// 4 1
// 1 1 2 3
// 输出
// -1

// 备注:
// 时间复杂度O(n),额外空间复杂度O(k)。
package nowcoder;

import java.util.Scanner;

// TODO
public class NowCoder_FindKMajor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(major(arr, k));
        in.close();
    }

    public static int major(int[] arr, int k) {
        return -1;
    }
}
