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

import java.util.Scanner;

public class NowCoder_EditDistance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word1 = in.nextLine();
        String word2 = in.nextLine();
        int ic = in.nextInt();
        int dc = in.nextInt();
        int rc = in.nextInt();
        System.out.println(edit(word1, word2, ic, dc, rc));
        in.close();
    }

    public static int edit(String word1, String word2, int ic, int dc, int rc) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        // words1 0~i 搞定 words2 0~j需要的最小编辑代价是多少？
        int[][] dp = new int[str1.length - 1][str2.length - 1];
        for (int i = 0; i < str1.length; i++) {
            dp[i][0] = i * dc;
        }
        for (int i = 0; i < str2.length; i++) {
            dp[0][i] = i * ic;
        }

        // TODO 普遍位置


        return dp[str1.length - 1][str2.length - 1];
    }


}
