// Given a string, your task is to count how many palindromic substrings in this string.

// The substrings with different start indexes or end indexes are counted as different substrings
// even they consist of same characters.

// Example 1:

// Input: "abc"
// Output: 3
// Explanation: Three palindromic strings: "a", "b", "c".


// Example 2:

// Input: "aaa"
// Output: 6
// Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


// Note:

// The input string length won't exceed 1000.
package 练习题.leetcode.medium;

// 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
// 回文字符串 是正着读和倒过来读一样的字符串。
// 子字符串 是字符串中的由连续字符组成的一个序列。
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
// 示例 1：
// 输入：s = "abc"
// 输出：3
// 解释：三个回文子串: "a", "b", "c"
// 示例 2：
// 输入：s = "aaa"
// 输出：6
// 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
// 提示：
// 1 <= s.length <= 1000
// s 由小写英文字母组成
// Leetcode题目 : https://leetcode.cn/problems/palindromic-substrings/
// 使用manacher算法：https://www.cnblogs.com/greyzeng/p/15314213.html
public class LeetCode_0647_PalindromicSubstrings {

    public static int countSubstrings(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        char[] strs = manacherString(str);
        int N = strs.length;
        int R = -1;
        int C = -1;
        int[] pArr = new int[N];
        for (int i = 0; i < N; i++) {
            // 先求pArr[i]至少不用扩的区域
            pArr[i] = i < R ? Math.min(pArr[C - (i - C)], R - i) : 1;
            // 两边继续扩充
            while (i + pArr[i] < N && i - pArr[i] > -1) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
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
        }
        int sum = 0;
        // pArr[i] -> 最大回文半径 -> 包含 pArr[i] / 2 个子回文
        for (int i = 0; i < N; i++) {
            sum += (pArr[i] >> 1);
        }
        return sum;
    }


    private static char[] manacherString(char[] str) {
        int N = str.length;
        int M = N * 2 + 1;
        char[] newArray = new char[M];
        for (int i = 0; i < M; i++) {
            newArray[i] = (i & 1) == 1 ? str[i / 2] : '#';
        }
        return newArray;
    }


}
