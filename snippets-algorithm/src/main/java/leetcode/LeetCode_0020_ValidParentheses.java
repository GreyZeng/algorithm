package leetcode;

import java.util.Stack;

// 栈
// 左压 右出 且匹配
// 栈空即可，只要有不满足就false

public class LeetCode_0020_ValidParentheses {

    public static boolean isValid(String s) {
        if (s == null) {
            return true;
        }
        char[] strs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : strs) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char m = stack.pop();

                if ((c == ')' && m == '(')
                        || (c == '}' && m == '{')
                        || (c == ']' && m == '[')) {
                } else {
                    return false;
                }

            }
        }
        return stack.isEmpty();
    }

}
