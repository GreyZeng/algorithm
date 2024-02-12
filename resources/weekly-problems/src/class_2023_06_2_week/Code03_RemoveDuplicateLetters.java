package class_2023_06_2_week;

// 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
// 需保证 返回结果的字典序最小
// 要求不能打乱其他字符的相对位置)
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
// 大厂刷题班讲过，不过那时没有讲出最优解，安排一下重讲
public class Code03_RemoveDuplicateLetters {

	public static String removeDuplicateLetters(String s) {
		// 统计每一种字符，出现的词频
		// 为了判断，某一种字符，后面还存在不存在
		int[] cnts = new int[26];
		// 判断某一种字符，是不是已经在栈里了
		boolean[] enter = new boolean[26];
		for (int i = 0; i < s.length(); i++) {
			cnts[s.charAt(i) - 'a']++;
		}
		// 栈 26长度，足够
		char[] stack = new char[26];
		int size = 0;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (!enter[cur - 'a']) {
				// 大压小，讨论要不要让栈顶字符，走！
				while (
						size > 0 
						&& stack[size - 1] > cur
						&& cnts[stack[size - 1] - 'a'] > 0) {
					enter[stack[size - 1] - 'a'] = false;
					size--;
				}
				stack[size++] = cur;
				enter[cur - 'a'] = true;
			}
			cnts[cur - 'a']--;
		}
		return String.valueOf(stack, 0, size);
	}

}
