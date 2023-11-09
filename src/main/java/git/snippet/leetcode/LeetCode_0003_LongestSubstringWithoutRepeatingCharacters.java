package git.snippet.leetcode;

import java.util.Arrays;

// 求一个字符串中，最长无重复字符子串长度
// LeetCode: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// NowCoder: https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4
// LintCode: https://www.lintcode.com/problem/384/
// 笔记： https://www.cnblogs.com/greyzeng/p/4605924.html
public class LeetCode_0003_LongestSubstringWithoutRepeatingCharacters {
    // 1. 必须以i结尾的字符串最大不重复字串的长度是?,假设为x，所有位置x值中最大的值就是答案。
    // 2. i位置的x的值取决于两个因素，第一个因素是i-1向左边能扩到最左位置（即：i-1位置上的x值），第二个因素是i位置的值上一次出现的位置 （这两个因素取最大值）
    // 3. 如何记录i位置上一次出现的位置呢？ 可以用一个整型数组arr来表示，a 的 ascii码是97， arr[97] = 3,表示a上次出现的位置是3.
    // 4. 如何记录i向左扩能扩到最左的位置是哪里？ 可以用一个pre变量来记录，pre初始是-1。
    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        char[] str = s.toCharArray();
        if (str.length <= 1) {
            return str.length;
        }
        int n = str.length;
        int[] arr = new int[256];
        // 默认每个位置的字符上一次出现的位置都是-1
        Arrays.fill(arr, -1);
        int ans = 1;
        int mostLeft = -1;
        for (int i = 0; i < n; i++) {
            // i位置的字符上一次出现的位置
            int prePos = arr[str[i]];
            mostLeft = Math.max(prePos, mostLeft);
            ans = Math.max(ans, i - mostLeft);
            // i号位置的字符的位置信息记录在arr中
            arr[str[i]] = i;
        }
        return ans;
    }
}
