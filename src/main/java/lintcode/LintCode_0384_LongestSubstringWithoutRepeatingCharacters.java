package lintcode;

import java.util.HashSet;

// https://www.lintcode.com/problem/384/
public class LintCode_0384_LongestSubstringWithoutRepeatingCharacters {
    // 时间复杂度要做到O(1)
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 1;
        int pre = -1;
        char[] str = s.toCharArray();
        int[] arr = new int[256];
        for (char c : str) {
            arr[c] = -1;
        }
        for (int i = 0; i < str.length; i++) {
            // 上一次出现的位置
            pre = Math.max(arr[str[i]], pre);
            max = Math.max(i - pre, max);
            arr[str[i]] = i;
        }
        return max;
    }
}
