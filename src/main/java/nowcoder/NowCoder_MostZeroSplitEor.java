/*题目描述
        给定一个整型数组arr，其中可能有正有负有零。你可以随意把整个数组切成若干个不相容的子数组，求异或和为0的子数组最多可能有多少个？
        整数异或和定义：把数组中所有的数异或起来得到的值。
        输入描述:
        输出包括两行，第一行一个整数，代表数组长度n(1≤n≤10^6)。第二行有n个整数，代表数组arr(−1e9≤arri≤1e9)。
        输出描述:
        输出一个整数，表示数组切割最多的子数组的个数。
        示例1
        输入
        复制
        10
        3 2 1 9 0 7 0 2 1 3
        输出
        复制
        4
        说明
        最优划分：{3,2,1},{9},{0},{7},{0},{2,1,3} 其中{3,2,1},{0},{0},{2,1,3}的异或和为0
        备注:
        时间复杂度O(n)，空间复杂度O(n)。*/
package nowcoder;

import java.util.HashMap;
import java.util.Scanner;
//类似题目：
//        NowCoder_MostZeroSplitEor.java
//        数组划分的部分进行异或和生成的0最多
//        [321 | 0 | 4 | 321 | 0 | 0 | 312 | 0 | 213]
//        tips:
//        假设答案法（子数组的最大累加和也用到了这个方法），
//        假设[0..i]异或和为sum
//        sum上次出现的位置j，
//        dp[i] = max{dp[i-1] , dp[j] + 1}
// https://www.nowcoder.com/practice/77e9828bbe3c4d4a9e0d49cc7537bb6d
public class NowCoder_MostZeroSplitEor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(mostZeroSplitEor(arr, n));
        in.close();
    }

    public static int mostZeroSplitEor(int[] arr, int n) {
        if (arr == null || n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        int sum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum ^= arr[i];
            if (map.containsKey(sum)) {
                int pre = map.get(sum);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(sum, i);
        }
        return dp[n - 1];
    }
}
