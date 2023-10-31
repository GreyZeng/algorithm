package git.snippet.leetcode;

// 编辑距离问题
// 不带权重
// https://leetcode-cn.com/problems/edit-distance/
// https://www.lintcode.com/problem/119/
// 带权重
// https://www.nowcoder.com/questionTerminal/05fed41805ae4394ab6607d0d745c8e4
// 进阶：Code_0018_MinDeleteCost
public class LeetCode_0072_EditDistance {

  public static int minDistance(String word1, String word2) {
    return minEditCost(word1.toCharArray(), word2.toCharArray(), 1, 1, 1);
  }

  public static int minEditCost(char[] str1, char[] str2, int ic, int dc, int rc) {
    // 插入和删除的代价如果小于替换代价，所有的替换行为都可以改成插入和删除的代价
    rc = Math.min(ic + dc, rc);
    // dp[i][j] : str1前i个字符,通过最短的编辑代价，实现str2的前j个字符
    int[][] dp = new int[str1.length + 1][str2.length + 1];
    // 第0行
    for (int i = 0; i < str2.length + 1; i++) {
      // 第0行
      dp[0][i] = i * ic;
    }
    // 第0列
    for (int i = 0; i < str1.length + 1; i++) {
      dp[i][0] = i * dc;
    }
    // 普遍位置
    for (int i = 1; i < str1.length + 1; i++) {
      for (int j = 1; j < str2.length + 1; j++) {
        int p1 = dp[i - 1][j] + dc;
        int p2 = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);
        int p3 = dp[i][j - 1] + ic;
        dp[i][j] = Math.min(p1, Math.min(p2, p3));
      }
    }
    return dp[str1.length][str2.length];
  }
}
