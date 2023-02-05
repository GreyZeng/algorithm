package 练习题.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

// ()[]{} 三类括号
// 栈
// 左压 右出 且匹配
// 栈空即可，只要有不满足就false
// 如果只有一类括号，则不需要用栈，直接用一个变量控制即可
// 左括号count++,右括号count--，任意时刻，如果count<0,直接不满足
// https://leetcode-cn.com/problems/valid-parentheses
// ref : https://www.lintcode.com/problem/valid-parentheses/description
// 笔记：https://www.cnblogs.com/greyzeng/p/16353363.html
public class LeetCode_0020_ValidParentheses {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] str = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str) {
            if (isLeft(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!isValid(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isLeft(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    public boolean isValid(char left, char right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }
}
