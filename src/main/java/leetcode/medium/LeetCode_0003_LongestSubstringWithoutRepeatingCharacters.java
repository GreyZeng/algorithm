package leetcode.medium;

import java.util.Arrays;

// NowCoder: https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4
// LintCode: https://www.lintcode.com/problem/384/
// https://www.cnblogs.com/greyzeng/p/4605924.html
public class LeetCode_0003_LongestSubstringWithoutRepeatingCharacters {
    // 1. 必须以i结尾的字符串最大不重复字串的长度是?,假设为x，所有位置x值中最大的值就是答案。
    // 2. i位置的x的值取决于两个因素，第一个因素是i-1向左边能扩到最左位置（即：i-1位置上的x值），第二个因素是i位置的值上一次出现的位置 （这两个因素取最大值）
    // 3. 如何记录i位置上一次出现的位置呢？ 可以用一个整型数组arr来表示，a 的 ascii码是97， arr[97] = 3,表示a上次出现的位置是3.
    // 4. 如何记录i向左扩能扩到最左的位置是哪里？ 可以用一个pre变量来记录，pre初始是-1，
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] arr = new int[128];
        Arrays.fill(arr, -1);
        int max = 1;
        int preLeft = -1;
        for (int i = 0; i < n; i++) {
            preLeft = Math.max(arr[str[i]], preLeft);
            arr[str[i]] = i;
            int cur = i - preLeft;
            max = Math.max(max, cur);
        }
        return max;
    }
}
