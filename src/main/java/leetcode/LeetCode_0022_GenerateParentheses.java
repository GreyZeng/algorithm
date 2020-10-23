/* Given n pairs of parentheses, write a function to generate all combinations
 of well-formed parentheses.

    For example, given n = 3, a solution set is:

            [
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
            ]*/
package leetcode;

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
		process(path, 0, list);
		return list;
	}
	public static List<String> generateParenthesis2(int n) {
		if (0 == n) {
			return new ArrayList<>();
		}
		char[] path = new char[n << 1];
		List<String> list = new ArrayList<>();
		process(path, 0,0,n, list);
		return list;
	}
	// 剪枝方式
	private static void process(char[] path, int index,int leftMRight, int leftRest, List<String> list) {
		if (index == path.length) {
			list.add(new String(path));
			return;
		}
		if (leftMRight > 0) {
			path[index] = ')';
			process(path, index+1, leftMRight-1, leftRest,list);
		}
		if (leftRest > 0) {
			path[index] = '(';
			process(path, index+1, leftMRight+1, leftRest-1, list);
		}
	}
	private static void process(char[] path, int index, List<String> list) {
		if (index == path.length) {
			if (isValid(path)) {
				list.add(String.valueOf(path));
			}
		} else {
			path[index] = '(';
			process(path, index + 1, list);
			path[index] = ')';
			process(path, index + 1, list);

		}
	}

	private static boolean isValid(char[] path) {
		int count = 0;
		for (char c : path) {
			if (c == '(') {
				count++;
			}
			if (c == ')') {
				count--;
			}
			if( count < 0) {
				return false;
			}
		}
		return count == 0;
	}

}
