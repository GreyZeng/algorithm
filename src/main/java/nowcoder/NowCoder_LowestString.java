package nowcoder;
// [编程题]拼接所有的字符串产生字典序最小的字符串
// https://www.nowcoder.com/questionTerminal/f1f6a1a1b6f6409b944f869dc8fd3381

import java.util.Arrays;
import java.util.Comparator;

public class NowCoder_LowestString {
     /**
     * 
     * @param strs string字符串一维数组 the strings
     * @return string字符串
     */
    public String minString (String[] strs) {
        Arrays.sort(strs, new MyComparator());
         StringBuilder path = new StringBuilder();
        for (String s : strs) {
            path.append(s);
        }
        return path.toString();
    }
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
        
    }
}
