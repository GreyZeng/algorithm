package lintcode;

public class LintCode_0200_LongestPalindromicSubstring {

    /**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] strs = manacherStr(str);
        int[] pArr = new int[strs.length];
        int C = -1;
        int R = -1;
        int i = 0;
        int N = strs.length;
        int max = 0;
        while (i < N) {
            // pArr[i] 至少不需要扩的大小
            pArr[i] = i < R ? Math.min(R - i, pArr[C - (i - C)]) : 1;
            while (i + pArr[i] < N && i - pArr[i] >= 0) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(pArr[i++], max);
        }
        int n = 0;
        for (; n < N; n++) {
            if (pArr[n] == max) {
                break;
            }
        }
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
