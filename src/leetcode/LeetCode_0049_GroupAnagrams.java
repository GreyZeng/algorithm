package leetcode;

import java.util.*;

public class LeetCode_0049_GroupAnagrams {

	
    // 两个str排序，一样
    public static List<List<String>> groupAnagrams(String[] strs) {
    	if (null == strs) {
    		return null;
    	}
    	HashMap<String,List<String>> map = new HashMap<>();
    	for(String str:strs) {
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
    	for(Map.Entry<String,List<String>> set : map.entrySet()) {
    		list.add(set.getValue());
    	}
    	return list;
    }


}
