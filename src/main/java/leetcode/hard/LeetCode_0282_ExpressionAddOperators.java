package leetcode.hard;


import java.util.LinkedList;
import java.util.List;

// TODO
//给定一个仅包含数字0-9的字符串和一个目标值，在数字之间添加 二元 运算符（不是一元）+、-或*，返回所有能够得到目标值的表达式。
//        输入: num = "123", target = 6
//        输出: ["1+2+3", "1*2*3"]
//        示例2:
//        输入: num = "232", target = 8
//        输出: ["2*3+2", "2+3*2"]
//        示例 3:
//        输入: num = "105", target = 5
//        输出: ["1*0+5","10-5"]
//        示例4:
//        输入: num = "00", target = 0
//        输出: ["0+0", "0-0", "0*0"]
// https://leetcode.cn/problems/expression-add-operators/
public class LeetCode_0282_ExpressionAddOperators {
    public static List<String> addOperators(String num, int target) {
        List<String> ret = new LinkedList<>();
        if (num.length() == 0) {
            return ret;
        }
        // 沿途的数字拷贝和+ - * 的决定，放在path里
        char[] path = new char[num.length() * 2 - 1];
        // num -> char[]
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < digits.length; i++) { // 尝试0~i前缀作为第一部分
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            dfs(ret, path, i + 1, 0, n, digits, i + 1, target); // 后续过程
            if (n == 0) {
                break;
            }
        }
        return ret;
    }

    // char[] digits 固定参数，字符类型数组，等同于num
    // int target 目标
    // char[] path 之前做的决定，已经从左往右依次填写的字符在其中，可能含有'0'~'9' 与 * - +
    // int len path[0..len-1]已经填写好，len是终止
    // int pos 字符类型数组num, 使用到了哪
    // left -> 前面固定的部分 cur -> 前一块
    // 默认 left + cur ...
    public static void dfs(List<String> res, char[] path, int len,
                           long left, long cur,
                           char[] num, int index, int aim) {
        if (index == num.length) {
            if (left + cur == aim) {
                res.add(new String(path, 0, len));
            }
            return;
        }
        long n = 0;
        int j = len + 1;
        for (int i = index; i < num.length; i++) { // pos ~ i
            // 试每一个可能的前缀，作为第一个数字！
            // num[index...i] 作为第一个数字！
            n = n * 10 + num[i] - '0';
            path[j++] = num[i];
            path[len] = '+';
            dfs(res, path, j, left + cur, n, num, i + 1, aim);
            path[len] = '-';
            dfs(res, path, j, left + cur, -n, num, i + 1, aim);
            path[len] = '*';
            dfs(res, path, j, left, cur * n, num, i + 1, aim);
            if (num[index] == '0') {
                break;
            }
        }
    }
}
