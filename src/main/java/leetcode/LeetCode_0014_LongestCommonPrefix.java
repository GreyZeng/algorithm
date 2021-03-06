package leetcode;

public class LeetCode_0014_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
    	if (null == strs || strs.length == 0) {
    		return "";
    	}
    	if (strs.length == 1) {
    		return strs[0];
    	}
    	char[] first = strs[0].toCharArray();
    	int res = Integer.MAX_VALUE;
    	for(String str : strs) {
    		char[] ch = str.toCharArray();
    	  	int index = 0;  		
    		while(index < ch.length && index < first.length && ch[index] == first[index]) {
    			index++;
    		}
    		res = Math.min(res, index);
    	}
    	return strs[0].substring(0,res);
    }
    public static void main(String[] args) {
    	String[] strs = new String[] {"abc","abd", "addd"};
    	System.out.println(longestCommonPrefix(strs));
    }
}
