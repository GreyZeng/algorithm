package leetcode;

public class LeetCode_0005_LongestPalindromicSubstring {

	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		if (s.length() == 1) {
			return s;
		}
		char[] origin = s.toCharArray();
		char[] str = manacherString(s);
		int R = -1; // 满足条件的位置的下一个位置
		int C = -1;
		int max = Integer.MIN_VALUE;
		StringBuilder longestPalindrome = new StringBuilder();
		int maxPosition = 0;
		int[] pArr = new int[str.length]; // 回文半径
		for (int i = 0; i < str.length; i++) {
			// pArr[C - (i - C)] 是pArr[i]的对称点的回文半径
			// #1#2#1#1#2#1
			// 这一步是存pArr[i]中至少可以不用验证的区域
			pArr[i] = i < R ? Math.min(pArr[C - (i - C)], R - i) : 1;

			// 这个while循环可以应对：i在R外，暴力扩的情况，也可以应对i对称点的回文半径压到L需要继续扩的情况
			// 除了这两种情况，其他情况进入这个while就会break出来。
			while (i + pArr[i] < str.length  // 右边不越界
					&&
					i - pArr[i] > -1 // 左边不越界
			) {
				if (str[i + pArr[i]] == str[i - pArr[i]]) {

					pArr[i]++;
				} else {
					break;
				}
			}

			// 更新R和C
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		for (int i = 0; i < str.length; i++) {
			if (pArr[i] == max) {
				maxPosition = i;
			}
		}
		if ((maxPosition & 1) == 1) {
			// 实: maxPosition / 2
			// 以maxPosition / 2为中心，扩充一个区域为 max - 1 的字符串
			// len : max - 1
			for (int i = (maxPosition / 2) - ((max - 1) / 2); i <= (maxPosition / 2) + ((max - 1) / 2); i++) {
				longestPalindrome.append(origin[i]);
			}
		} else {
			// 虚
			// len: max - 1
			for (int i = maxPosition - max + 1 + 1; i < maxPosition + max - 1; i += 2) {
				longestPalindrome.append(str[i]);
			}
		}

		// #1#2#1#2#1#
		return longestPalindrome.toString();
	}
	public static char[] manacherString(String str) {
		char[] newArray = new char[str.length() * 2 + 1];
		char[] origin = str.toCharArray();
		for (int i = 0; i < newArray.length; i++) {
			if (i % 2 == 0) {
				newArray[i] = '#';
			} else {
				newArray[i] = origin[i / 2];
			}
		}
		return newArray;
	}

}
