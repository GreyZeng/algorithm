package leetcode;
//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
//
//        已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
//
//        每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
//
//        请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
//
//         
//        示例 1：
//
//        输入：k = 1, n = 2
//        输出：2
//        解释：
//        鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
//        否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
//        如果它没碎，那么肯定能得出 f = 2 。
//        因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
//        示例 2：
//
//        输入：k = 2, n = 6
//        输出：3
//        示例 3：
//
//        输入：k = 3, n = 14
//        输出：4
//         
//
//        提示：
//
//        1 <= k <= 100
//        1 <= n <= 104
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/super-egg-drop
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//tips:
//        如果 k > log2N 二分处理 int f(i,j) 剩余i层楼，还剩下j颗 最少需要多少次数 考虑最差情况
//
//        四边形不等式优化 非最优解
//
//        最优解 dp[i][j] : i是棋子数，j是扔的次数，假设有i个棋子，可以扔j次的情况下可以搞定多少层楼 dp[i][j] = dp[i-1][j-1] + dp[i][j-1] + 1 列的确定：用空间压缩的方法 O(K*S)
//        S是客观上要解决的次数 K表示的棋子
//
//        可以继续优化（有可能转换成斐波那契问题）
// leetcode测试链接：https://leetcode.com/problems/super-egg-drop
// 方法1和方法2会超时
// 方法3勉强通过
// 方法4打败100%
// 方法5打败100%，方法5是在方法4的基础上做了进一步的常数优化
public class LeetCode_0887_SuperEggDrop {

    public static int superEggDrop1(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        return process1(nLevel, kChess);
    }

    // rest还剩多少层楼需要去验证
    // k还有多少颗棋子能够使用
    // 一定要验证出最高的不会碎的楼层！但是每次都是坏运气。
    // 返回至少需要扔几次？
    public static int process1(int rest, int k) {
        if (rest == 0) {
            return 0;
        }
        if (k == 1) {
            return rest;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i != rest + 1; i++) { // 第一次扔的时候，仍在了i层
            min = Math.min(min, Math.max(process1(i - 1, k - 1), process1(rest - i, k)));
        }
        return min + 1;
    }

    public static int superEggDrop2(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) {
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    public static int superEggDrop3(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        int[][] best = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp[0].length; i++) {
            dp[1][i] = 1;
            best[1][i] = 1;
        }
        for (int i = 2; i < nLevel + 1; i++) {
            for (int j = kChess; j > 1; j--) {
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                int down = best[i - 1][j];
                int up = j == kChess ? i : best[i][j + 1];
                for (int first = down; first <= up; first++) {
                    int cur = Math.max(dp[first - 1][j - 1], dp[i - first][j]);
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = first;
                    }
                }
                dp[i][j] = ans + 1;
                best[i][j] = bestChoose;
            }
        }
        return dp[nLevel][kChess];
    }

    public static int superEggDrop4(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static int superEggDrop5(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int bsTimes = log2N(nLevel) + 1;
        if (kChess >= bsTimes) {
            return bsTimes;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int maxN = 500;
        int maxK = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans2 = superEggDrop2(K, N);
            int ans3 = superEggDrop3(K, N);
            int ans4 = superEggDrop4(K, N);
            int ans5 = superEggDrop5(K, N);
            if (ans2 != ans3 || ans4 != ans5 || ans2 != ans4) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}
