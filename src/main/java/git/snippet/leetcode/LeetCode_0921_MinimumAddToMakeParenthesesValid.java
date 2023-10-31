/*
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or
 * ')', and in any positions ) so that the resulting parentheses string is valid.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, or It can be written as AB (A concatenated with B), where A and B are
 * valid strings, or It can be written as (A), where A is a valid string. Given a parentheses
 * string, return the minimum number of parentheses we must add to make the resulting string valid.
 *
 *
 *
 * Example 1:
 *
 * Input: "())" Output: 1 Example 2:
 *
 * Input: "(((" Output: 3 Example 3:
 *
 * Input: "()" Output: 0 Example 4:
 *
 * Input: "()))((" Output: 4
 *
 *
 * Note:
 *
 * S.length <= 1000 S only consists of '(' and ')' characters.
 */
package git.snippet.leetcode;

// tips:
// 用一个count和need变量
// 如果count==-1，need++，count恢复成0
// 到最后count==0，返回need，到最后count不等于0，返回need+count
// https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/
// ref : https://www.lintcode.com/problem/minimum-add-to-make-parentheses-valid/description
public class LeetCode_0921_MinimumAddToMakeParenthesesValid {

  public static int minAddToMakeValid(String s) {
    int count = 0;
    int need = 0;
    char[] strs = s.toCharArray();
    for (char str : strs) {
      if (str == ')') {
        count--;
        if (count == -1) {
          // 说明左括号无法cover住右括号了，需要补上一个
          count = 0;
          need++;
        }
      } else {
        count++;
      }
    }
    // 多余的左括号
    // 多余的右括号
    return count + need;
  }

  public static void main(String[] args) {
    String s = "()))((";
    System.out.println(minAddToMakeValid(s));
  }
}
