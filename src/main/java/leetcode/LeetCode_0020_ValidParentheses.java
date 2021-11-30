package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// ()[]{} 三类括号
// 栈
// 左压 右出 且匹配
// 栈空即可，只要有不满足就false
// 如果只有一类括号，则不需要用栈，直接用一个变量控制即可
// ref : https://www.lintcode.com/problem/valid-parentheses/description
public class LeetCode_0020_ValidParentheses {
	public static void main(String[] args) {
		boolean b = isValid("()()()()()(){");
		System.out.println(b);
	}
	public static boolean isValid(String s) {
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
				} else if (!isMatch(stack.poll(), c)) {
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

}
