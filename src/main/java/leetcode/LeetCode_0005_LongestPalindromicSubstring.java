package leetcode;

public class LeetCode_0005_LongestPalindromicSubstring {

	public static String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return null;
        }
        char[] str = s.toCharArray();
        char[] strs = manacherString(str);
        int N = strs.length;
        int R = -1;
        int C = -1;
        int[] pArr = new int[N];
        int max = 0;
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

            // 更新max，获取最大回文半径
            max = Math.max(pArr[i], max);
        }
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (pArr[i] == max) {
                maxIndex = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxIndex - pArr[maxIndex] + 2; i <= maxIndex + pArr[maxIndex] - 2; i += 2) {
            sb.append(strs[i]);
        }
        return sb.toString();
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
