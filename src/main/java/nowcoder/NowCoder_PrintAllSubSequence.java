package nowcoder;

import java.util.ArrayList;
import java.util.HashSet;
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

    public static List<String> printAllSubSequence(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        char[] strs = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process(strs, 0, ans, "");
        return ans;
    }

    // 0...index - 1都已经做好了选择,现在从index开始做选择
    // 所有收集的答案存在ans里面
    public static void process(char[] strs, int index, List<String> ans, String path) {
        if (index == strs.length) {
            ans.add(path);
            return;
        }
        // 要index位置的字符
        process(strs, index + 1, ans, path + strs[index]);
        // 不要index位置的字符
        process(strs, index + 1, ans, path);
    }

    public static List<String> printAllSubSequenceNoRepeat(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        char[] strs = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process2(strs, 0, set, "");
        List<String> ans = new ArrayList<>();
        for (String string : set) {
            ans.add(string);
        }
        return ans;
    }

    public static void process2(char[] strs, int index, HashSet<String> ans, String path) {
        if (index == strs.length) {
            ans.add(path);
            return;
        }
        // 要index位置的字符
        process2(strs, index + 1, ans, path + strs[index]);
        // 不要index位置的字符
        process2(strs, index + 1, ans, path);
    }

    public static void main(String[] args) {
        String str = "aabc";
        List<String> ans2 = printAllSubSequence(str);
        for (String ans : ans2) {
            System.out.println(ans);
        }

        System.out.println("=====================");
        List<String> ans3 = printAllSubSequenceNoRepeat(str);
        for (String a : ans3) {
            System.out.println(a);
        }

    }

}
