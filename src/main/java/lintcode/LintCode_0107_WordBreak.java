package lintcode;

import java.util.Set;


//描述
//给定字符串 s 和单词字典 dict，确定 s 是否可以分成一个或多个以空格分隔的子串，并且这些子串都在字典中存在。
//因为我们已经使用了更强大的数据，所以普通的DFS方法无法解决此题。
// https://www.lintcode.com/problem/107/
// 因为使用了更强大的数据，所以普通的DFS方法无法解决此题。
// s.length <= 1e5 
// dict.size <= 1e5 
public class LintCode_0107_WordBreak {
    public static boolean wordBreak(String s, Set<String> wordDict) {
        return process(s, 0, wordDict) != 0;
    }

    public static int process(final String s, int from, final Set<String> wordDict) {
        if (from == s.length()) {
            return 1;
        }
        int result = 0;
        for (int to = from; to < s.length(); to++) {
            // substring [from, to+1)
            if (wordDict.contains(s.substring(from, to + 1))) {
                result += process(s, to + 1, wordDict);
            }
        }
        return result;
    }
}
