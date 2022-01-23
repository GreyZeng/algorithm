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
        // 子序列一定是连续的
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process(0,str,path,ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void process(int i, char[] str, String path, List<String> ans) {
        if (i == str.length) {
            ans.add(path);
        } else {
            process(i+1,str,path,ans);
            process(i+1,str,path+str[i],ans);
        }
    }
}
