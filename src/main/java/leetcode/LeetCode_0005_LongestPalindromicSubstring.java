package leetcode;
// manacher算法

// 最长回文子串
// 
// LeetCode_0005_LongestPalindromicSubstring.java
// LintCode_0200_LongestPalindromicSubstring.java

// LeetCode_0647_PalindromicSubstrings.java

// LeetCode_0214_ShortestPalindrome.java
public class LeetCode_0005_LongestPalindromicSubstring {
    
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] strs = manacherStr(str);
        int[] pArr = new int[strs.length];
        int c = 0;
        int r = 0;
        int i = 1;
        int len = strs.length;
        int max = 1;
        while (i < len) {
            // pArr[i] 至少不需要扩的大小
            pArr[i] = i < r ? Math.min(r - i, pArr[c - (i - c)]) : 1;
            // 暴力扩
            while (i + pArr[i] < len && i - pArr[i] >= 0) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 扩散的位置能否更新回文有边界R
            // 如果可以更新，则更新R，且把C置于当前的i，因为是当前的i让回文右边界扩散的
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(pArr[i++], max);
        }

        // 定位最大回文有边界的回文中心是哪个
        int n = 0;
        for (; n < len; n++) {
            if (pArr[n] == max) {
                break;
            }
        }

        // 构造最大回文子串
        StringBuilder sb = new StringBuilder();
        for (i = n - max + 2; i < n + max; i += 2) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    public static char[] manacherStr(char[] str) {
        char[] strs = new char[str.length << 1 | 1];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = ((i & 1) == 1) ? str[i >> 1] : '#';
        }
        return strs;
    }
}
