/*Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.*/
package leetcode;


//必须含有1到26种字符的情况
//滑动窗口
public class LeetCode_0395_LongestSubstringWithAtLeastKRepeatingCharacters {
	// TODO 可以通过，并非最优解
	public static int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			int[] count = new int[26];
			int satify = 0;
			int collect = 0;
			int R = i;
			while (R < N) {
				if (count[str[R] - 'a'] == 0) {
					collect++;
				}
				// 构造窗口
				count[str[R] - 'a']++;
				if (count[str[R] - 'a'] == k) {
					satify++;
				}
				if (satify == collect) {
					ans = Math.max(ans, R - i + 1);
				}
				R++;
			}
			count[str[i] - 'a']--;
		}
		return ans;
	}

	public static void main(String[] args) {

		System.out.println(longestSubstring("aaabb", 3));
		System.out.println(longestSubstring("ababbc", 2));
		System.out.println(longestSubstring("ababacb", 3));
		System.out.println(longestSubstring("bbaaacbd", 3));

	}

}
