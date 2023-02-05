package 练习题.nowcoder;

// 笔记：https://www.cnblogs.com/greyzeng/p/16777406.html
// https://www.nowcoder.com/questionTerminal/736e12861f9746ab8ae064d4aae2d5a9
// int[] hp，hp[i]：i号怪兽的能力
// int[] money，money[i]：i号怪兽要求的钱
// 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
// 如果你当前的能力，小于i号怪兽的能力，你必须付出money[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
// 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
// 返回通过所有的怪兽，需要花的最小钱数。
// tips:
// 根据数据量猜解法
// 方法1： 如果怪兽能力值不大的情况，二维表 能力 + index 达到的最小钱数
// 方法2： 如果怪兽能力值比较大的情况，二维表 钱数 + index 达到的最大能力 严格花某个钱
public class NowCoder_BeatMonster {
    // i到最后通过所有怪兽的最少钱数是多少？
    // 适合怪兽能力不大的情况
    public static long func1(int[] hp, int[] money) {
        return p(hp, money, 0, 0);
    }

    public static long p(int[] hp, int[] money, int i, int j) {
        if (i == hp.length) {
            return 0;
        }
        // 选择贿赂
        long p = money[i] + p(hp, money, i + 1, j + hp[i]);
        // 不选贿赂，有条件
        if (j >= hp[i]) {
            return Math.min(p, p(hp, money, i + 1, j));
        }
        return p;
    }

    public static long func1Dp(int[] hp, int[] money) {
        int sum = 0;
        for (int a : hp) {
            sum += a;
        }
        long[][] dp = new long[hp.length + 1][sum + 1];
        for (int i = hp.length - 1; i >= 0; i--) {
            for (int j = sum - hp[i]; j >= 0; j--) {
                dp[i][j] = money[i] + dp[i + 1][j + hp[i]];
                if (j >= hp[i]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static long func2(int[] hp, int[] money) {
        int sum = 0;
        for (int a : money) {
            sum += a;
        }
        int N = hp.length;
        for (int i = 0; i < sum; i++) {
            if (p2(hp, money, N - 1, i) != -1) {
                return i;
            }
        }
        return sum;
    }

    // 从0....index 怪兽，花的钱，必须严格==m
    // 如果通过不了，返回-1
    // 如果可以通过，返回能通过情况下的最大能力值
    public static long p2(int[] hp, int[] money, int index, int m) {
        if (index == -1) {
            return m == 0 ? 0L : -1L;
        }
        // 贿赂当前怪兽
        long p1 = p2(hp, money, index - 1, m);
        long ans = -1;
        if (p1 != -1 && p1 >= hp[index]) {
            ans = p1;
        }
        long p2 = p2(hp, money, index - 1, m - money[index]);
        if (p2 != -1) {
            ans = Math.max(ans, p2 + hp[index]);
        }
        return ans;
    }


    public static long func4(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }

    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 12;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = func1(d, p);
            long ans4 = func1Dp(d, p);
            long ans2 = func2(d, p);
            long ans3 = func4(d, p);
            if (ans1 != ans2 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("oops!");
            }
        }

    }

}
