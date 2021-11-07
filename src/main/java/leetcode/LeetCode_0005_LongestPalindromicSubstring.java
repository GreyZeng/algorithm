package leetcode;
// manacher算法

// 最长回文子串
// 
// LeetCode_0005_LongestPalindromicSubstring.java
// LintCode_0200_LongestPalindromicSubstring.java

// LeetCode_0647_PalindromicSubstrings.java

// LeetCode_0214_ShortestPalindrome.java
// 约束
//1 <= s.length <= 1000
//s consist of only digits and English letters.
public class LeetCode_0005_LongestPalindromicSubstring {


    // Manacher算法 O(N)
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

    // 暴力解法
    public static String longestPalindrome1(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] mStr = manacherStr(str);
        int max = 1; // 最大回文长度至少是1
        int lM = 0; // 记录最长回文的左边界的上一个位置
        int rM = 0; // 记录最长回文的有边界的下一个位置
        for (int i = 1; i < mStr.length; i++) {
            int curMax = 1; // 当前的最大回文长度至少是1
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < mStr.length) {
                if (mStr[l] == mStr[r]) {
                    // 暴力扩充
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            curMax = r - l - 1;
            if (curMax > max) {
                // 当前最长回文长度已经超过了max了
                // 更新中心值
                // 更新max值
                max = curMax;
                lM = l;
                rM = r;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = lM + 2; i < rM; i += 2) {
            sb.append(mStr[i]);
        }
        return sb.toString();
    }

    public static char[] manacherStr(char[] str) {
        final char c = '#';
        char[] mStr = new char[(str.length << 1) | 1];
        mStr[0] = c;
        mStr[1] = str[0];
        int index = 1;
        for (int i = 2; i < mStr.length; i++) {
            if ((i & 1) != 1) {
                mStr[i] = c;
            } else {
                mStr[i] = str[index++];
            }
        }
        return mStr;
    }
}
