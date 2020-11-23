package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code_0053_PrintAllSubSequence {

	public static List<String> prinAllSubSequence(String s) {
		if (s == null || s.length() < 1) {
			return null;
		}
		char[] strs = s.toCharArray();
		List<String> ans = new ArrayList<>();

		process(strs, 0, ans, "");
		return ans;
	}

	// 0...index - 1都已经做好了选择,现在从index开始做选择
	// 所有收集的答案存在ans里面
	public static void process(char[] strs, int index, List<String> ans, String path) {
		if (index == strs.length) {
			ans.add(path);
			return;
		}
		// 要index位置的字符
		process(strs, index + 1, ans, path + strs[index]);
		// 不要index位置的字符
		process(strs, index + 1, ans, path);
	}

	public static List<String> printAllSubSequenceNoRepeat(String s) {
		if (s == null || s.length() < 1) {
			return null;
		}
		char[] strs = s.toCharArray();
		HashSet<String> set = new HashSet<>();
		process2(strs, 0, set, "");
		List<String> ans = new ArrayList<>();
		for (String string : set) {
			ans.add(string);
		}
		return ans;
	}

	public static void process2(char[] strs, int index, HashSet<String> ans, String path) {
		if (index == strs.length) {
			ans.add(path);
			return;
		}
		// 要index位置的字符
		process2(strs, index + 1, ans, path + strs[index]);
		// 不要index位置的字符
		process2(strs, index + 1, ans, path);
	}

	public static void main(String[] args) {
		String str = "aabc";
		List<String> ans2 = prinAllSubSequence(str);
		for (String ans : ans2) {
			System.out.println(ans);
		}

		System.out.println("=====================");
		List<String> ans3 = printAllSubSequenceNoRepeat(str);
		for (String a : ans3) {
			System.out.println(a);
		}

	}

}
