package nowcoder;

import java.util.ArrayList;
import java.util.List;

//打印一个字符串的全部子序列，包括空字符串
//		示例1
//		输入
//		"abc"
//		输出
//		["","c","b","bc","a","ac","ab","abc"]
// https://www.nowcoder.com/questionTerminal/6c91e4855afc43638521d1171c252818
public class NowCoder_PrintAllSubSequence {
    // 有重复值
    // 测评链接：https://www.nowcoder.com/questionTerminal/6c91e4855afc43638521d1171c252818
    public String[] getAllSubs(String s) {
        if (s == null) {
            return null;
        }
        List<String> res = new ArrayList<>();
        char[] str = s.toCharArray();
        String path = "";
        p(str, 0, path, res);
        final int size = res.size();
        return res.toArray(new String[size]);
    }

    private void p(char[] str, int i, String path, List<String> res) {
        if (i == str.length) {
            res.add(path);
            return;
        }
        p(str, i + 1, path, res);
        p(str, i + 1, path + str[i], res);
    }
}
