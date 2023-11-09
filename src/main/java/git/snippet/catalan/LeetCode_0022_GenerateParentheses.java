/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 */
package git.snippet.catalan;

import java.util.ArrayList;
import java.util.List;

// 如果只需要返回数量，则只需要卡特兰数即可
public class LeetCode_0022_GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        if (0 == n) {
            return new ArrayList<>();
        }
        char[] path = new char[n << 1];
        List<String> list = new ArrayList<>();
        process(path, 0, 0, n, list);
        return list;
    }

    // 剪枝方式
    // path: 所有做过的决定 path 0...index - 1 已经做完决定，当前轮到index位置上做决定
    // leftMinusRight 之前做的决定中，左括号减去右括号的数量
    // leftRest 还剩下几个左括号
    // 结果放list中
    private static void process(
            char[] path, int index, int leftMinusRight, int leftRest, List<String> list) {
        if (index == path.length) {
            // 之所以可以这样做，是因为剪枝做的完善
            list.add(new String(path));
            return;
        }
        if (leftMinusRight > 0) {
            // 左括号减去右括号如果大于0，一定可以做一个右括号的决定
            path[index] = ')';
            process(path, index + 1, leftMinusRight - 1, leftRest, list);
        }
        if (leftRest > 0) {
            // 左括号有剩余，一定可以做一个左括号的决定
            path[index] = '(';
            process(path, index + 1, leftMinusRight + 1, leftRest - 1, list);
        }
    }
}
