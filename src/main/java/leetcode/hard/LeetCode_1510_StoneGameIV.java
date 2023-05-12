package leetcode.hard;

import java.util.Arrays;

// Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
//
// 一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
//
// 如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
//
// 给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
//
//  
//
// 示例 1：
//
// 输入：n = 1
// 输出：true
// 解释：Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。
// 示例 2：
//
// 输入：n = 2
// 输出：false
// 解释：Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -> 1 -> 0）。
// 示例 3：
//
// 输入：n = 4
// 输出：true
// 解释：n 已经是一个平方数，Alice 可以一次全拿掉 4 个石子并赢得胜利（4 -> 0）。
// 示例 4：
//
// 输入：n = 7
// 输出：false
// 解释：当 Bob 采取最优策略时，Alice 无法赢得比赛。
// 如果 Alice 一开始拿走 4 个石子， Bob 会拿走 1 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 3 -> 2 -> 1 -> 0）。
// 如果 Alice 一开始拿走 1 个石子， Bob 会拿走 4 个石子，然后 Alice 只能拿走 1 个石子，Bob 拿走最后一个石子并赢得胜利（7 -> 6 -> 2 -> 1 -> 0）。
// 示例 5：
//
// 输入：n = 17
// 输出：false
// 解释：如果 Bob 采取最优策略，Alice 无法赢得胜利。
//  
//
// 提示：
//
// 1 <= n <= 10^5
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode.cn/problems/stone-game-iv
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
// Tips:牛羊吃草 O(N*根号N）
public class LeetCode_1510_StoneGameIV {

    // 暴力解法 LeetCode超时
    public static boolean winnerSquareGame(int n) {
        return p(n);
    }

    // 暴力解法
    public static boolean p(int n) {
        if (n == 0) {
            // 先手遇到0，就是输了
            return false;
        }
        for (int i = 1; i * i <= n; i++) {
            if (!p(n - i * i)) {
                return true;
            }
        }
        return false;
    }

    // 缓存
    public static boolean winnerSquareGame2(int n) {
        int[] dp = new int[n + 1];
        // -1表示没算过
        // 0 表示算过是false
        // 1 表示算过是true
        Arrays.fill(dp, -1);
        return p2(n, dp);
    }

    public static boolean p2(int n, int[] dp) {
        if (dp[n] != -1) {
            return dp[n] == 1;
        }
        if (n == 0) {
            // 先手遇到0，就是输了
            dp[n] = 0;
            return false;
        }
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        boolean ans = false;
        for (int i = 1; i * i <= n; i++) {
            if (!p2(n - i * i, dp)) {
                ans = true;
                break;
            }
        }
        dp[n] = ans ? 1 : 0;
        return ans;
    }

    // 动态规划
    public static boolean winnerSquareGame3(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            // 这种情况，先手绝对赢
            dp[i * i] = 1;
            if (i * i == n) {
                return dp[n] == 1;
            }
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - j * j] == 0) {
                    dp[i] = 1;
                }
            }
            dp[i] = dp[i] == -1 ? 0 : dp[i];
        }
        return dp[n] == 1;
    }
}
