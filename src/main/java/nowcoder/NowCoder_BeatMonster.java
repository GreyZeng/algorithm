/*链接：https://www.nowcoder.com/questionTerminal/736e12861f9746ab8ae064d4aae2d5a9
来源：牛客网

开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
如果你当前的能力，小于i号怪兽的能力，你必须付出money[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
返回通过所有的怪兽，需要花的最小钱数。


输入描述:
第一行输入一个整数N，表示你的能力值N<=500
接下来N行，每行输入两个整数，表示怪兽的力量和需要的金钱


输出描述:
输出一个整数，需要花的最小钱数
示例1
输入
2
8 10
6 5
输出
10*/
package nowcoder;

import java.util.Scanner;

public class NowCoder_BeatMonster {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] money = new int[n];
        int[] hp = new int[n];
        for (int i = 0; i < n; i++) {
            hp[i] = in.nextInt();
            money[i] = in.nextInt();
        }
        System.out.println(min2(hp, money, n));
        in.close();
    }

    private static int min(int[] hp, int[] money, int n) {
        return p(hp, money, n, 0, 0);
    }

    // 目前，你的能力是ability，你来到了index号怪兽的面前，如果要通过后续所有的怪兽，
    // 请返回需要花的最少钱数
    // 适合能力值总体比较小的时候
    public static int p(int[] hp, int[] money, int n, int ability, int index) {
        if (index == n) {
            return 0;
        }
        if (ability < hp[index]) {
            // 只能贿赂
            return money[index] + p(hp, money, n, ability + hp[index], index + 1);
        }
        // 可以贿赂也可以直接用能力
        return Math.min(
                money[index] + p(hp, money, n, ability + hp[index], index + 1),
                p(hp, money, n, ability, index + 1)
        );
    }

    // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
    // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
    // 适合钱数不是很多的时候
    public static int min2(int[] hp, int[] money, int n) {
        int sum = 0;
        for (int m : money) {
            sum += m;
        }
        int[][] dp = new int[n][sum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][money[0]] = hp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= money[i] && dp[i - 1][j - money[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - money[i]] + hp[i];
                }
                if (dp[i - 1][j] >= hp[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[n - 1][i] != -1) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
