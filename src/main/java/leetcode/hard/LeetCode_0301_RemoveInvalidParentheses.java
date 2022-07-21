package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
//        返回所有可能的结果。答案可以按任意顺序 返回。
//        示例 1：
//
//        输入：s = "()())()"
//        输出：["(())()","()()()"]
//        示例 2：
//
//        输入：s = "(a)())()"
//        输出：["(a())()","(a)()()"]
//        示例 3：
//
//        输入：s = ")("
//        输出：[""]
//
//        提示：
//
//        1 <= s.length <= 25
//        s 由小写英文字母以及括号 '(' 和 ')' 组成
//        s 中至多含 20 个括号
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
//剪枝条件
//1. 如何判断一个字符串是否有效，count不出现小于0的情况，左括号++，右括号--
//2. f(i,j), 检查位置从i开始，删除位置从j开始
// TODO
public class LeetCode_0301_RemoveInvalidParentheses {
    // https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    // modifyIndex <= checkIndex
    // 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
    // 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
    // 比如：
    // ( ( ) ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // 一开始当然checkIndex = 0，modifyIndex = 0
    // 当查到6的时候，发现不对了，
    // 然后可以去掉2位置、4位置的 )，都可以
    // 如果去掉2位置的 ), 那么下一步就是
    // ( ( ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 2
    // 如果去掉4位置的 ), 那么下一步就是
    // ( ( ) ( ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 4
    // 也就是说，
    // checkIndex和modifyIndex，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
    public static void remove(String s, List<String> ans, int checkIndex, int modifyIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            // i check计数<0的第一个位置
            if (count < 0) {
                for (int j = modifyIndex; j <= i; ++j) {
                    // 比如
                    if (s.charAt(j) == par[1] && (j == modifyIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                    }
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed);
        }
    }
}
