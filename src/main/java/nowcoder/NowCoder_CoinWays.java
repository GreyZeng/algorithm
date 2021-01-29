/*链接：https://www.nowcoder.com/questionTerminal/93bcd2190da34099b98dfc9a9fb77984
        来源：牛客网

        现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币， 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值?


        输入描述:
        第一行输入三个整数n1,n2,m        n1,n2<=1000 m<=100000
        第二行输入n1个整数表示普通币的面值
        第三行输入n2个整数表示纪念币的面值
        不同的硬币面值可能相同


        输出描述:
        使用编号不同但面值相同的硬币算不同的拼法
        输出用多少种方法拼出m的面值，由于答案过大，对1e9 + 7取模
        示例1
        输入
        5 5 100
        87 76 15 79 53
        1 94 59 30 5
        输出
        2
        说明
        1+94+5
        79+15+5+1*/
package nowcoder;

import java.util.Scanner;

public class NowCoder_CoinWays {
    static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int target = in.nextInt();
        int[] many = new int[n1];
        int[] one = new int[n2];
        for (int i = 0; i < n1; i++) {
            many[i] = Integer.parseInt(in.next());
        }
        for (int i = 0; i < n2; i++) {
            one[i] = Integer.parseInt(in.next());
        }
        System.out.println(moneyWays(many, one, target));
        in.close();
    }

    public static long moneyWays(int[] many, int[] one, int money) {
        if (money < 0) {
            return 0;
        }
        if ((many == null || many.length == 0) && (one == null || one.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        long[][] dpMany = many(many, money);
        long[][] dpOne = one(one, money);
        if (dpMany == null) {
            return dpOne[dpOne.length - 1][money];
        }
        if (dpOne == null) {
            return dpMany[dpMany.length - 1][money];
        }
        long res = 0;
        for (int i = 0; i <= money; i++) {
            res += dpMany[dpMany.length - 1][i] * dpOne[dpOne.length - 1][money - i];
            res %= MOD;
        }
        return res;
    }

    public static long[][] many(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        long[][] dp = new long[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= money; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
                dp[i][j] %= MOD;
            }
        }
        return dp;
    }

    public static long[][] one(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        long[][] dp = new long[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
                dp[i][j] %= MOD;
            }
        }
        return dp;
    }

}
