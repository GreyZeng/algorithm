package lintcode;

import java.util.LinkedList;

public class LintCode_0423_ValidParentheses {
	/**
	 * @param s: A string
	 * @return: whether the string is a valid parentheses
	 */
	public static boolean isValidParentheses(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		char[] strs = s.toCharArray();
		LinkedList<Character> stack = new LinkedList<Character>();
		int len = strs.length;
		for (int i = 0; i < len; i++) {
			if (isLeft(strs[i])) {
				stack.push(strs[i]);
			} else {
				if (stack.isEmpty()) {
					return false;
				} else if (!isMatch(stack.poll(), strs[i])) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	public static boolean isLeft(char c) {
		return '(' == c || '{' == c || '[' == c;
	}

	public static boolean isMatch(char left, char right) {
		return (left == '[' && right == ']') || (left == '(' && right == ')') || (left == '{' && right == '}');
	}
	
	public static void main(String[] args) {
		String s= "[(){[]}()]";
		System.out.println(isValidParentheses(s));
	}
}
