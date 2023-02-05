package 算法.Manacher;

// manacher算法
// 最长回文子串
// LeetCode_0005_LongestPalindromicSubstring.java
// LeetCode_0647_PalindromicSubstrings.java
// LeetCode_0214_ShortestPalindrome.java
// 约束
// 1 <= s.length <= 1000
// s consist of only digits and English letters.
// https://www.cnblogs.com/greyzeng/p/15314213.html
// https://leetcode.com/problems/longest-palindromic-substring/
public class LeetCode_0005_LongestPalindromicSubstring {

    // Manacher算法 O(N)
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        char[] manacherStr = manacherStr(str);
        // 一个整型数组，长度和预处理串一样，存每个位置的最长回文半径是多少。
        int len = manacherStr.length;
        int[] pArr = new int[len];
        pArr[0] = 1;
        pArr[len - 1] = 1;
        // 回文最右边界，只要某个位置能扩到超过这个位置，就更新r这个值，初始值为0，因为一个字符串回文字符串至少是1，
        // 可以以第0个字符为中心且以0为最右边界(即：第0个字符本身作为一个回文串)
        int r = 0;
        // 就是扩到r位置的的中心点，即pArr[c] = r - c + 1，初始值为0，与r的初始值定为0一样的考虑。
        int c = 0;
        // 当前遍历到的位置，因为pArr[0]=1, pArr[len - 1] = 1;所以i可以从1开始遍历,一直到len - 2
        int i = 1;
        int max = 1;
        while (i < len - 1) {
            // pArr[i] 至少不需要扩的大小
            pArr[i] = i < r ? Math.min(r - i, pArr[c - (i - c)]) : 1;
            // 暴力扩
            while (i + pArr[i] < len && i - pArr[i] >= 0) {
                if (manacherStr[i + pArr[i]] == manacherStr[i - pArr[i]]) {
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
            sb.append(manacherStr[i]);
        }
        return sb.toString();
    }

    // 暴力解法 O(N^2)
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
