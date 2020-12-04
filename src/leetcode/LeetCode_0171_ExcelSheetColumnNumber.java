package leetcode;

//Given a column title as appear in an Excel sheet, return its corresponding column number.
//
//For example:
//
//    A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
//Example 1:
//
//Input: "A"
//Output: 1
//Example 2:
//
//Input: "AB"
//Output: 28
//Example 3:
//
//Input: "ZY"
//Output: 701
// 
//
//Constraints:
//
//1 <= s.length <= 7
//s consists only of uppercase English letters.
//s is between "A" and "FXSHRXW".
public class LeetCode_0171_ExcelSheetColumnNumber {

	// 这道题反过来也要会写
	public static int titleToNumber(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			ans = ans * 26 + (str[i] - 'A') + 1;
		}
		
		return ans;
	}

}
