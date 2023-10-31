/**
 * 整数转罗马数字 Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * <p>Symbol Value I 1 V 5 X 10 L 50 C 100 D 500 M 1000
 *
 * <p>For example, two is written as II in Roman numeral, just two one's added together. Twelve is
 * written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX
 * + V + II.
 *
 * <p>Roman numerals are usually written largest to smallest from left to right. However, the
 * numeral for four is not IIII. Instead, the number four is written as IV. Because the one is
 * before the five we subtract it making four. The same principle applies to the number nine, which
 * is written as IX. There are six instances where subtraction is used:
 *
 * <p>I can be placed before V (5) and X (10) to make 4 and 9. X can be placed before L (50) and C
 * (100) to make 40 and 90. C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * <p>Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range
 * from 1 to 3999.
 */
package git.snippet.leetcode;

@Deprecated
public class LeetCode_0012_IntegerToRoman {
  public static String intToRoman(int num) {
    String[][] map =
        new String[][] {
          {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
          {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
          {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
          {"", "M", "MM", "MMM"}
        };
    return map[3][num / 1000 % 10]
        + map[2][num / 100 % 10]
        + map[1][num / 10 % 10]
        + map[0][num % 10];
  }
}
