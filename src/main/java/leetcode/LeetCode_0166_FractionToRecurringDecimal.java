package leetcode;

import java.util.HashMap;

// 给定两个整数，分别表示分数的分子numerator 和分母 denominator，以 字符串形式返回小数 。
// 如果小数部分为循环小数，则将循环的部分括在括号内。
// 如果存在多个答案，只需返回 任意一个 。
// 对于所有给定的输入，保证 答案字符串的长度小于 104 。
// 示例 1：
// 输入：numerator = 1, denominator = 2
// 输出："0.5"
// 示例 2：
// 输入：numerator = 2, denominator = 1
// 输出："2"
// 示例 3：
// 输入：numerator = 2, denominator = 3
// 输出："0.(6)"
// 示例 4：
// 输入：numerator = 4, denominator = 333
// 输出："0.(012)"
// 示例 5：
// 输入：numerator = 1, denominator = 5
// 输出："0.2"
// Leetcode题目 : https://leetcode.com/problems/fraction-to-recurring-decimal/
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
                return "-" + sb;
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
            return "-" + sb;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(-2147483648 / 1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(fractionToDecimal(-2147483648, 1));
    }

}
