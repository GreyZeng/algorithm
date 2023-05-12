package snippet;

// 笔记：https://www.cnblogs.com/greyzeng/p/16887649.html
// 怪兽存活概率问题
// 给定3个参数，N，M，K 怪兽有N滴血，等着英雄来砍自己 英雄每一次打击，都会让怪兽流失
// [0...M]的血量 到底流失多少？每一次在[0...M]上等概率的获得一个值 求K次打击之后，英雄把怪兽砍死的概率
//
// tips:
// 怪兽在经历K次打击后所有可能的掉血情况是(M+1)^K次方
// 可优化枚举行为
//
// 血量<=0 -> (M+1)^time 次数
public class Code_KillMonster {
    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        // monster在经历K次打击后所有可能的掉血情况是
        long all = (long) Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return (double) kill / (double) all;
    }

    // 怪兽还剩 hp 点血，每次的伤害在[0……M]范围上，还有 times 次可以砍，返回砍死的情况数。
    public static long process(int times, int M, int hp) {
        // 情况一：已经没有被砍的次数了，这个时候，血量如果正好是小于等于0的值， 说明怪兽已经被砍死一次
        // 否则怪兽不可被砍死
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        // 情况二：怪兽已经死了，但是还可以砍
        // 此时，所有的砍法都满足条件，所以情况就是(long) Math.pow(M + 1, times)
        if (hp <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
