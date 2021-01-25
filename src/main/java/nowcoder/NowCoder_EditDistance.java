/*链接：https://www.nowcoder.com/questionTerminal/05fed41805ae4394ab6607d0d745c8e4
        来源：牛客网

        给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。
        示例1
        输入
        "abc","adc",5,3,2
        输出
        2
        示例2
        输入
        "abc","adc",5,3,100
        输出
        8


备注:
1≤∣str1∣,∣str2∣≤5000
1≤ic,dc,rc≤10000
*/
package nowcoder;


public class NowCoder_EditDistance {

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

    public static void main(String[] args) {
        String words1 = "abc";
        String words2 = "adc";
        System.out.println(minEditCost(words1, words2, 5, 3, 2));
    }


}
