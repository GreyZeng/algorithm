package leetcode;

// TODO
// 力扣想让一个最优秀的员工在 N 个城市间旅行来收集算法问题。
// 但只工作不玩耍，聪明的孩子也会变傻，所以您可以在某些特定的城市和星期休假。
// 您的工作就是安排旅行使得最大化你可以休假的天数，但是您需要遵守一些规则和限制。
// 规则和限制：
// 您只能在 N 个城市之间旅行，用 0 到 N-1 的索引表示。一开始，您在索引为0的城市，并且那天是星期一。
// 这些城市通过航班相连。这些航班用 N*N 矩阵 flights（不一定是对称的）表示，flights[i][j] 代表城市i到城市j的航空状态。如果没有城市i到城市j的航班，
// flights[i][j] = 0；否则，flights[i][j] = 1。同时，对于所有的 i，flights[i][i] = 0。
// 您总共有 K 周（每周7天）的时间旅行。您每天最多只能乘坐一次航班，并且只能在每周的星期一上午乘坐航班。由于飞行时间很短，我们不考虑飞行时间的影响。
// 对于每个城市，不同的星期您休假天数是不同的，给定一个 N*K 矩阵 days 代表这种限制，days[i][j] 代表您在第j个星期在城市i能休假的最长天数。
// 给定 flights 矩阵和 days 矩阵，您需要输出 K 周内可以休假的最长天数。
// leetcode题目：https://leetcode.cn/problems/maximum-vacation-days/
// tips: 宽度优先遍历
public class LeetCode_0568_MaximumVacationDays {

    public static int maxVacationDays(int[][] fly, int[][] day) {
        int n = fly.length;
        int k = day[0].length;
        // pas[i] = {a, b, c}
        // 从a、b、c能飞到i
        int[][] pass = new int[n][];
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                if (fly[j][i] != 0) {
                    s++;
                }
            }
            pass[i] = new int[s];
            for (int j = n - 1; j >= 0; j--) {
                if (fly[j][i] != 0) {
                    pass[i][--s] = j;
                }
            }
        }
        // dp[i][j] -> 第i周必须在j这座城，0~i-1周（随意），最大休假天数
        int[][] dp = new int[k][n];
        // 飞的时机，是周一早上飞，认为对时间没有影响，直接到某个城，然后过一周
        dp[0][0] = day[0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j] = fly[0][j] != 0 ? day[j][0] : -1;
        }
        for (int i = 1; i < k; i++) { // 第i周
            for (int j = 0; j < n; j++) { // 在j号城过！
                // 第i周，要怎么到j号城
                // 下面max的初始值，我第i-1周，就在j号城，选择不动地方，进入第i周
                int max = dp[i - 1][j];
                for (int p : pass[j]) { // 枚举什么？能到j号城的城市p
                    max = Math.max(max, dp[i - 1][p]);
                }
                dp[i][j] = max != -1 ? max + day[j][i] : -1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[k - 1][i]);
        }
        return ans;
    }

}
