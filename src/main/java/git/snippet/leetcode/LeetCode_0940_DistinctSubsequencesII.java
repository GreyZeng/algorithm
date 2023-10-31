package git.snippet.leetcode;

// TODO
// 给定一个字符串str，返回str的所有子序列中有多少不同的字面值
// Leetcode题目：https://leetcode.com/problems/distinct-subsequences-ii/
// tips：
// 考虑不重复的情况：
// 所有不同字面值的子序列中，以
// a结尾的有多少..
// ..
// z结尾的有多少..
// all记录总的字面值子序列有多少
// 如果有重复值
// 那么all的更新需要 all — [重复的字符]原有的数量
// 是否考虑空集？
//
// 无重复字符，
// 有重复字符 比如 {1，2，1}
// 某个字符上一步all+新-修正值
// 修正值=上一步以这个字符结尾的字符数量
public class LeetCode_0940_DistinctSubsequencesII {
  public static int distinctSubseqII(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int m = 1000000007;
    char[] str = s.toCharArray();
    int[] count = new int[26];
    int all = 1; // 算空集
    for (char x : str) {
      int add = (all - count[x - 'a'] + m) % m;
      all = (all + add) % m;
      count[x - 'a'] = (count[x - 'a'] + add) % m;
    }
    return all - 1;
  }
}
