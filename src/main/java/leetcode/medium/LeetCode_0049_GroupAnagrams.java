// 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
//
//  
//
// 示例 1:
//
// 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
// 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
// 示例 2:
//
// 输入: strs = [""]
// 输出: [[""]]
// 示例 3:
//
// 输入: strs = ["a"]
// 输出: [["a"]]
//  
//
// 提示：
//
// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] 仅包含小写字母
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/group-anagrams
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package leetcode.medium;

import java.util.*;

public class LeetCode_0049_GroupAnagrams {


    // 两个str排序，一样
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (null == strs) {
            return null;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String newStr = new String(c);
            if (map.containsKey(newStr)) {
                map.get(newStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            }
        }
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> set : map.entrySet()) {
            list.add(set.getValue());
        }
        return list;
    }


}
