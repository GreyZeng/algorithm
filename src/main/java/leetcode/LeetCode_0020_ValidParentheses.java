package leetcode;

import java.util.Stack;

// ()[]{} 三类括号
// 栈
// 左压 右出 且匹配
// 栈空即可，只要有不满足就false
// 如果只有一类括号，则不需要用栈，直接用一个变量控制即可
// ref : https://www.lintcode.com/problem/valid-parentheses/description
public class LeetCode_0020_ValidParentheses {

	public static boolean isValid(String s) {
		if (null == s || s.length() == 0) {
			return true;
		}
		char[] strs = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] == '(' || strs[i] == '[' || strs[i] == '{') {
				stack.push(strs[i]);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					char m = stack.pop();
					if ((strs[i] == ')' && m != '(') || (strs[i] == ']' && m != '[') || (strs[i] == '}' && m != '{')) {
						return false;
					}
				}
			}
		}
		return stack.isEmpty();
	}

}
