package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_0017_LetterCombinationsOfAPhoneNumber {

	public static final char[][] map = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
			{ 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

	public static List<String> letterCombinations(String digits) {
		if (null == digits) {
			return null;
		}
		if (0 == digits.length()) {
			return new ArrayList<>();
		}
		char[] strs = digits.toCharArray();
		char[] path = new char[strs.length];
		List<String> ans = new ArrayList<>();
		process(strs, 0, path, ans);
		return ans;
	}

	// str = ['2','3'] 3 3
	// str[....index-1]，按出的结果是什么都在path里
	// str[index...] 按完之后，有哪些组合，放入到ans里
	public static void process(char[] str, int index, char[] path, List<String> ans) {
		if (index == str.length) {
			ans.add(new String(path));
			return;
		}
		char[] w = map[str[index] - '0'];
		for(char c : w) {
			path[index]=c;
			process(str, index+1, path,ans);
		}
	}

}
