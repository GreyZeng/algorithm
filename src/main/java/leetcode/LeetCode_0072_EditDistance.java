/*给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

        你可以对一个单词进行如下三种操作：

        插入一个字符
        删除一个字符
        替换一个字符
         

        示例 1：

        输入：word1 = "horse", word2 = "ros"
        输出：3
        解释：
        horse -> rorse (将 'h' 替换为 'r')
        rorse -> rose (删除 'r')
        rose -> ros (删除 'e')
        示例 2：

        输入：word1 = "intention", word2 = "execution"
        输出：5
        解释：
        intention -> inention (删除 't')
        inention -> enention (将 'i' 替换为 'e')
        enention -> exention (将 'n' 替换为 'x')
        exention -> exection (将 'n' 替换为 'c')
        exection -> execution (插入 'u')
         

        提示：

        0 <= word1.length, word2.length <= 500
        word1 和 word2 由小写英文字母组成

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/edit-distance
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
package leetcode;

// ref : NowCoder_EditDistance.java
public class LeetCode_0072_EditDistance {

    public int minDistance(String word1, String word2) {
        return minEditCost(word1, word2, 1, 1, 1);
    }

    public static int minEditCost(String str1, String str2, int ic, int dc, int rc) {
        if (ic + dc < rc) {
            rc = ic + dc;
        }
        char[] w1 = str1.toCharArray();
        char[] w2 = str2.toCharArray();
        // words1 前i个 搞定 words2 前j个需要的最小编辑代价是多少？
        int m = w1.length + 1;
        int n = w2.length + 1;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; i++) {
            dp[i][0] = i * dc;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = i * ic;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (w1[i - 1] != w2[j - 1]) {
                    dp[i][j] = rc + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[m - 1][n - 1];
    }
}
