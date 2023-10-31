package git.snippet.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_0017_LetterCombinationsOfAPhoneNumber {

  public static final char[][] MAP = {
    {},
    {},
    {'a', 'b', 'c'},
    {'d', 'e', 'f'},
    {'g', 'h', 'i'},
    {'j', 'k', 'l'},
    {'m', 'n', 'o'},
    {'p', 'q', 'r', 's'},
    {'t', 'u', 'v'},
    {'w', 'x', 'y', 'z'}
  };

  // DFS深度优先遍历
  public static List<String> letterCombinations(String digits) {
    if (null == digits) {
      return null;
    }
    if (0 == digits.length()) {
      return new ArrayList<>();
    }
    char[] str = digits.toCharArray();
    char[] path = new char[str.length];
    List<String> ans = new ArrayList<>();
    process(str, 0, path, ans);
    return ans;
  }

  // 从i往后搞定所有的匹配
  public static void process(char[] str, int i, char[] path, List<String> ans) {
    if (i == str.length) {
      ans.add(String.valueOf(path));
      return;
    }
    char[] t = MAP[str[i] - '0'];
    for (char c : t) {
      path[i] = c;
      process(str, i + 1, path, ans);
    }
  }
}
