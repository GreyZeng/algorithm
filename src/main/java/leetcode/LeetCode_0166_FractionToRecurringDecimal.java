package leetcode;

import java.util.HashMap;

//Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
//        If the fractional part is repeating, enclose the repeating part in parentheses.
//
//        If multiple answers are possible, return any of them.
//
//
//
//        Example 1:
//
//        Input: numerator = 1, denominator = 2
//        Output: "0.5"
//        Example 2:
//
//        Input: numerator = 2, denominator = 1
//        Output: "2"
//        Example 3:
//
//        Input: numerator = 2, denominator = 3
//        Output: "0.(6)"
//        Example 4:
//
//        Input: numerator = 4, denominator = 333
//        Output: "0.(012)"
//        Example 5:
//
//        Input: numerator = 1, denominator = 5
//        Output: "0.2"
//
//
//        Constraints:
//
//        -231 <= numerator, denominator <= 231 - 1
//        denominator != 0
public class LeetCode_0166_FractionToRecurringDecimal {

    // 余数重复出现，则开始出现循环
    // M.(ABC)
    public static String fractionToDecimal(int numerator, int denominator) {
        long ts = Math.abs((long) numerator);
        long te = Math.abs((long) denominator);
        double ttt = (double) numerator / (double) denominator;
        long M = ts / te;
        long N = ts % te;
        StringBuilder sb = new StringBuilder();
        sb.append(M);
        if (N == 0) {
            if (ttt < 0) {
                return "-" + sb.toString();
            }
            return sb.toString();
        }
        sb.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        while (N != 0) {
            long A = N * 10 / te;
            sb.append(A);
            N = N * 10 % te;
            if (map.containsKey(N)) {
                int x = map.get(N);
                int y = sb.length();
                String b = sb.substring(0, x);
                String s = sb.substring(x, y);
                if (ttt < 0) {
                    return "-" + b + "(" + s + ")";
                }
                return b + "(" + s + ")";
            }
            map.put(N, sb.length());
        }
        if (ttt < 0) {
            return "-" + sb.toString();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(-2147483648 / 1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(fractionToDecimal(-2147483648, 1));
    }

}
