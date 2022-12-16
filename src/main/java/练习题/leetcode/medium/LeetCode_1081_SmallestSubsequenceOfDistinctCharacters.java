package 练习题.leetcode.medium;

// https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/
// 和 https://leetcode.cn/problems/remove-duplicate-letters/ 一样
// 笔记：https://www.cnblogs.com/greyzeng/p/16463982.html
public class LeetCode_1081_SmallestSubsequenceOfDistinctCharacters {

  public String smallestSubsequence(String s) {
    char[] str = s.toCharArray();
    int[] map = new int[26];
    for (char c : str) {
      map[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    int l = 0;
    int r = 0;
    while (r < str.length) {
      if (map[str[r] - 'a'] == -1 || --map[str[r] - 'a'] > 0) {
        // r在一个已经处理过的位置或者r上的词频减掉后没有到0，说明现在
        // 还没有来到需要统计的时刻
        // r放心++
        r++;
      } else {
        // r位置的词频已经减少到0了
        // 可以结算了
        int p = l;
        for (int i = l; i <= r; i++) {
          if (map[str[i] - 'a'] != -1 && str[i] < str[p]) {
            p = i;
          }
        }
        if (map[str[p] - 'a'] != -1) {
          // 结算的位置必须是有效位置
          sb.append(str[p]);
          // 结算完毕后，将这个位置的词频设置为-1，便于后续判断此位置是否已经被用过
          map[str[p] - 'a'] = -1;
        }
        for (int i = p + 1; i <= r; i++) {
          // [p+1,r]之间的位置的字符，把词频还原回来，因为这部分词频是减多了的
          if (map[str[i] - 'a'] != -1) {
            map[str[i] - 'a']++;
          }
        }
        l = p + 1;
        r = l;
      }
    }
    return sb.toString();
  }
}
