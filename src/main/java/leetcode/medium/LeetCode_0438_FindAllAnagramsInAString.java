/*Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/
package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

// 滑动窗口 + 欠账表
public class LeetCode_0438_FindAllAnagramsInAString {
	public static List<Integer> findAnagrams(String s, String p) {
		ArrayList<Integer> result = new ArrayList<>();
		if (s == null || p == null) {
			return result;
		}
		int left = 0, right = 0, count = p.length();

		int[] map = new int[256];
		char[] sc = s.toCharArray();
		// 初始化map
		for (char c : p.toCharArray()) {
			map[c]++;
		}
		while (right < s.length()) {
			// 1：扩展窗口，窗口中包含一个T中子元素，count--；
			if (map[sc[right++]]-- >= 1) {
				count--;
			}
			// 2：通过count或其他限定值，得到一个可能解。
			if (count == 0) {
				result.add(left);
			}
			// 3：只要窗口中有可能解，那么缩小窗口直到不包含可能解。
			if (right - left == p.length() && map[sc[left++]]++ >= 0) {
				count++;
			}
		}
		return result;
	}

}
