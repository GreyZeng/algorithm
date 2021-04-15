package leetcode;

// NowCoder: https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4
// LintCode: https://www.lintcode.com/problem/384/
public class LeetCode_0003_LongestSubstringWithoutRepeatingCharacters {

    // 1. 必须以i结尾的字符串最大不重复字串的长度是?,假设为x，所有位置x值中最大的值就是答案。
    // 2. i位置的x的值取决于两个因素，第一个因素是i-1向左边能扩到最左位置（即：i-1位置上的x值），第二个因素是i位置的值上一次出现的位置 （这两个因素取最大值）
    // 3. 如何记录i位置上一次出现的位置呢？ 可以用一个整型数组arr来表示，a 的 ascii码是97， arr[97] = 3,表示a上次出现的位置是3.
    // 4. 如何记录i向左扩能扩到最左的位置是哪里？ 可以用一个pre变量来记录，pre初始是-1，
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] arr = new int[128];
        for (int i = 0; i < 128; i++) {
            arr[i] = -1;
        }
        int pre = -1;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {

            pre = Math.max(arr[str[i]], pre);
            // i位置能往左扩的最左位置的长度
            cur = i - pre;
            max = Math.max(cur, max);
            // str[i] 上次出现的位置
            arr[str[i]] = i;
        }
        return max;
    }

}
