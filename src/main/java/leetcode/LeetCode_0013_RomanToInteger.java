/**
 * 罗马数字转整数
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 */
package leetcode;

public class LeetCode_0013_RomanToInteger {


    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (singleMatch(chars[i]) > singleMatch(chars[i - 1])) {
                res += (-singleMatch(chars[i - 1]));
            } else {
                res += (singleMatch(chars[i - 1]));
            }
        }
        res += singleMatch(chars[chars.length - 1]);
        return res;
    }

    public static int singleMatch(char s) {
        int p = 0;
        switch (s) {
            case 'I':
                p = 1;
                break;
            case 'V':
                p = 5;
                break;
            case 'X':
                p = 10;
                break;
            case 'L':
                p = 50;
                break;
            case 'C':
                p = 100;
                break;
            case 'D':
                p = 500;
                break;
            case 'M':
                p = 1000;
                break;
        }
        return p;
    }
}


