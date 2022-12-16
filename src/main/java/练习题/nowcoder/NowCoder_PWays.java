/*
 * 链接：https://www.nowcoder.com/questionTerminal/1469eb4694de4bb08a72a55ffb387b21 来源：牛客网
 * 
 * 对于一个字符串, 从前开始读和从后开始读是一样的, 我们就称这个字符串是回文串。例如"ABCBA","AA", "A" 是回文串, 而"ABCD", "AAB"不是回文串。牛牛特别喜欢回文串,
 * 他手中有一个字符串s, 牛牛在思考能否从字 符串中移除部分(0个或多个)字符使其变为回文串，并且牛牛认为空串不是回文串。牛牛发现移除的方案可能有 很多种,
 * 希望你来帮他计算一下一共有多少种移除方案可以使s变为回文串。对于两种移除方案, 如果移除的字符位置不一样就是不同的方案。
 * 
 * 
 * 输入描述: 输入一个字符串
 * 
 * 
 * 输出描述: 输出移除方案数，由于答案较大，对998244353取模 示例1 输入 aab 输出 4 说明 移除第1，2个字符 移除第1，3个字符 移除第2，3个字符 移除第3个字符 示例2 输入
 * abcde 输出 5 说明 任意移除4个字符
 */
package 练习题.nowcoder;

import java.util.Scanner;

// tips:
// 范围模型LxRxaLvRxbLxRvcLvRvd
// ```dp[L][R]```
//
// ```dp[L+1][R]=a+c```
// ```dp[L][R-1]=a+b```
// 加工a+b+cd=a+1(考虑空串，所以要加1)
public class NowCoder_PWays {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    System.out.println(ways(s));
    in.close();
  }

  public static int ways(String str) {
    int mod = 998244353;
    char[] s = str.toCharArray();
    int n = s.length;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      // 只有一个字符，只能选择：不移除 这种方式
      dp[i][i] = 1;
    }
    for (int i = 0; i < n - 1; i++) {
      // ab
      // 移除a
      // 移除b
      // 一共2种
      // aa
      // 移除第一个a
      // 移除第二个a
      // 不移除
      dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
    }

    for (int L = n - 3; L >= 0; L--) {
      for (int R = L + 2; R < n; R++) {
        // 普遍位置
        // L 位置和 R位置的值是否一样来确定
        // dp[L][R] 可以不选L，可以不选R
        // dp[L][R] = dp[L+1][R] + dp[L][R-1]
        // 因为这里面是有重复的，相当于加了两遍的dp[L+1][R-1]
        // 所以dp[L][R] 至少应该是 dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1]
        // 如果正好L位置的值等于R位置的值
        // 那么dp[L][R] 囊括了 dp[L-1][R-1]的所有情况，且还可以把dp[L-1][R-1] 全部删成空串，所以dp[L-1][R-1] + 1
        dp[L][R] = (dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1]) % mod;
        if (s[L] == s[R]) {
          dp[L][R] = (dp[L][R] + dp[L + 1][R - 1] + 1) % mod;
        }


        // 数据很大，做特殊处理
        if (dp[L][R] < 0) {
          dp[L][R] += mod;
        }
      }
    }

    return dp[0][n - 1];
  }
}
